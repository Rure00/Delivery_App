package com.project.datavalidation_processor

import com.google.auto.service.AutoService
import com.project.datavalidation_annotation.DataValidation
import com.project.datavalidation_annotation.FieldNameAndTag
import com.project.datavalidation_annotation.Val
import com.project.datavalidation_annotation.ValidationResult
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Name
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class Processor: AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        val fileBuilder =
            FileSpec.builder("com.datavalidation.generated", "DataValidationExtension")
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            Val::class.java.name
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        val classElements = roundEnv.getElementsAnnotatedWith(DataValidation::class.java)

        if (!checkElementType(ElementKind.CLASS, classElements)) return false

        classElements.forEach { fileBuilder.addFunction(makeValidateFunction(it)) }

        fileBuilder.addImport(FieldNameAndTag::class.java, "")
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        kaptKotlinGeneratedDir?.let { fileBuilder.build().writeTo(File(it)) }

        return true
    }

    private fun makeValidateFunction(classElement: Element): FunSpec {
        val validateFunSpec = FunSpec.builder("validate")
            .receiver(classElement.asType().asTypeName())
            .returns(ValidationResult::class)
            .addStatement("val result = %T()", ValidationResult::class.java)

        val fieldElement = classElement.enclosedElements
        fieldElement.forEach {
            val shouldVal = it.getAnnotation(Val::class.java)


            shouldVal?.let { annotation ->
                validateFunSpec.addComment("Value Check")
                validateFunSpec.beginControlFlow("if(${it.simpleName} is KMutableClass)")
                validateFunSpec.addStatement("result.isValid = false")
                validateFunSpec.addStatement(
                    "result.invalidFieldNameAndTags.add(${
                        createFieldNameAndTag(
                            it.simpleName,
                            annotation.tag
                        )
                    })"
                )
                validateFunSpec.endControlFlow()
            }
        }

        return validateFunSpec.addStatement("return result").build()
    }

    private fun checkElementType(kind: ElementKind, elements: Set<Element>): Boolean {
        if (elements.isEmpty()) return false
        elements.forEach {
            if (it.kind != kind) {
                printMessage(
                    Diagnostic.Kind.ERROR, "Only ${kind.name} Are Supported", it
                )
                return false
            }
        }
        return true
    }

    private fun printMessage(kind: Diagnostic.Kind, message: String, element: Element) {
        processingEnv.messager.printMessage(kind, message, element)
    }

    private fun createFieldNameAndTag(fieldName: Name, tag: String): String {
        return "FieldNameAndTag(\"$fieldName\", \"$tag\")"
    }

}
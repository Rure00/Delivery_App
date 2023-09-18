package com.project.datavalidation_annotation

@Target(AnnotationTarget.CLASS)
annotation class DataValidation

@Target(AnnotationTarget.CLASS)
annotation class Val (
    val tag: String = "It should be Value"
)

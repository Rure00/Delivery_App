package com.project.datavalidation_annotation

data class ValidationResult (
    var isValid: Boolean = true,
    var invalidFieldNameAndTags: MutableList<FieldNameAndTag> = mutableListOf()
)
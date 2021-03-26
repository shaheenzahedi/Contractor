package service.generators.fields

import com.squareup.javapoet.FieldSpec

interface IFieldGenerator {
    fun repositoryField():FieldSpec.Builder
}
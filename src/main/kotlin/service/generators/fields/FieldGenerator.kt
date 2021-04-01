package service.generators.fields

import com.squareup.javapoet.FieldSpec

interface FieldGenerator {
    fun repositoryField():FieldSpec.Builder
}
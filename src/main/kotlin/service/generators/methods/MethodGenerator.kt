package service.generators.methods

import com.squareup.javapoet.MethodSpec

interface MethodGenerator {
    fun getAllMethod():MethodSpec.Builder
}
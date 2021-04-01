package service.generators.methods

import com.squareup.javapoet.MethodSpec

interface MethodGenerator {
    fun setupTestMethod(): MethodSpec.Builder
    fun initTestMethod(): MethodSpec.Builder
}
package service.generators.methods

import com.squareup.javapoet.MethodSpec

interface MethodGenerator {
    fun setupTestMethod(): MethodSpec.Builder
    fun initTestMethod(): MethodSpec.Builder
    fun createEntityMethod(): MethodSpec.Builder
    fun getEntityMethod(): MethodSpec.Builder
    fun getAllEntitiesMethod(): MethodSpec.Builder
    fun updateEntityMethod(): MethodSpec.Builder
    fun deleteEntityMethod(): MethodSpec.Builder
}
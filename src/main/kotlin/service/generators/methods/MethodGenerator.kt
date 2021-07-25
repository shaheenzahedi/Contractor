package service.generators.methods

import com.squareup.javapoet.MethodSpec
import domain.ready_to_generate.ReadyToTestModel

interface MethodGenerator {
    fun setupTestMethod(): MethodSpec
    fun initTestMethod(): MethodSpec.Builder
    fun createEntityMethod(): MethodSpec.Builder
    fun getEntityMethod(): MethodSpec.Builder
    fun getAllEntitiesMethod(): MethodSpec.Builder
    fun updateEntityMethod(): MethodSpec.Builder
    fun deleteEntityMethod(): MethodSpec.Builder
    fun generateBasicGetMethod(rtModel: List<ReadyToTestModel>): List<MethodSpec>
}
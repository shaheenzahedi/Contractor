package service.generators.methods

import com.squareup.javapoet.MethodSpec
import domain.RTTest.ReadyToTestModel
import service.generators.annotations.AnnotationGenerator

class JMethodGenerator(
    private val annotationGenerator: AnnotationGenerator
) : MethodGenerator {
    override fun setupTestMethod(): MethodSpec.Builder {
        return MethodSpec.methodBuilder("setup")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())
            .addStatement("MockitoAnnotations.initMocks(this)")
    }

    override fun generateBasicGetMethod(rtModel: List<ReadyToTestModel>): List<MethodSpec> {
        return rtModel.map {
            MethodSpec.methodBuilder(it.name)
                .addStatement("BDDAssertions.then(personResponseEntity.getValue()).isEqualTo(200)")
                .build()
        }
    }

    override fun initTestMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("initTest")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())

    override fun createEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("createEntity")
            .addAnnotations(testCaseAnnotations)

    override fun getEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("getEntity")
            .addAnnotations(testCaseAnnotations)

    override fun getAllEntitiesMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("getAllEntities")
            .addAnnotations(testCaseAnnotations)

    override fun updateEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("updateEntity")
            .addAnnotations(testCaseAnnotations)

    override fun deleteEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("deleteEntity")
            .addAnnotations(testCaseAnnotations)

    private val testCaseAnnotations = mutableListOf(
        annotationGenerator.testAnnotation.build(),
        annotationGenerator.transactionalAnnotation.build()
    )
}
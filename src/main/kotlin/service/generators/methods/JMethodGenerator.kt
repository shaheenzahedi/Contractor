package service.generators.methods

import com.squareup.javapoet.MethodSpec
import service.generators.annotations.AnnotationGenerator

class JMethodGenerator(
    private val annotationGenerator: AnnotationGenerator
) : MethodGenerator {
    override fun setupTestMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("setup")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())

    override fun initTestMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("initTest")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())

    override fun createEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("createEntity")
            .addAnnotation(annotationGenerator.testAnnotation.build())
            .addAnnotation(annotationGenerator.transactionalAnnotation.build())
}
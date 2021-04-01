package service.generators.methods

import com.squareup.javapoet.MethodSpec
import service.generators.annotations.JAnnotationGenerator

class JMethodGenerator(
    private val annotationGenerator: JAnnotationGenerator
) : MethodGenerator {
    override fun setupTestMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("setup")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())

    override fun initTestMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("initTest")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())
}
package service.generators.methods

import com.squareup.javapoet.MethodSpec
import service.generators.annotations.JAnnotationGenerator

class JMethodGenerator(
    private val annotationGenerator: JAnnotationGenerator
) : MethodGenerator {
    override fun getAllMethod(): MethodSpec.Builder {
        return MethodSpec.methodBuilder("setup")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())
    }
}
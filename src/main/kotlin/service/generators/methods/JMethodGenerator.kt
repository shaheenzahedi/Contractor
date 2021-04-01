package service.generators.methods

import com.squareup.javapoet.MethodSpec
import service.generators.annotations.JAnnotationGenerator

class JMethodGenerator(
    private val annotationGenerator: JAnnotationGenerator
) : MethodGenerator {
    override fun getAllMethod(): MethodSpec.Builder {
        return MethodSpec.methodBuilder("getAllBookmarks").addStatement("int sum = 0")
            .beginControlFlow("for (int i = 0; i <= 10; i++)")
            .addStatement("sum += i")
            .endControlFlow()
    }
}
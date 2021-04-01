package service.generators

import com.squareup.javapoet.MethodSpec
import service.generators.annotations.AnnotationGenerator
import service.generators.fields.FieldGenerator
import com.squareup.javapoet.TypeSpec as JTypeSpec

class IntegrationTestGenerator(
    private val annotationGenerator: AnnotationGenerator,
    private val fieldGenerator: FieldGenerator
) {
    fun getJavaBuilder(): JTypeSpec.Builder {
        return JTypeSpec.classBuilder("SampleIntegrationTest")
            .addAnnotation(annotationGenerator.springBootTestAnnotation.build())
            .addAnnotation(annotationGenerator.autoConfigureMockMvcAnnotation.build())
            .addAnnotation(annotationGenerator.withMockUserAnnotation.build())
            .addAnnotation(annotationGenerator.extensionsAnnotation.build())
            .addField(fieldGenerator.repositoryField().build())
            .addMethod(MethodSpec.methodBuilder("getAllBookmarks").addStatement("int sum = 0")
                .beginControlFlow("for (int i = 0; i <= 10; i++)")
                .addStatement("sum += i")
                .endControlFlow()
                .build())
    }
}
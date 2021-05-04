package service.generators

import service.generators.annotations.AnnotationGenerator
import service.generators.fields.FieldGenerator
import service.generators.methods.MethodGenerator
import com.squareup.javapoet.TypeSpec as JTypeSpec

class IntegrationTestGenerator(
    private val annotationGenerator: AnnotationGenerator,
    private val fieldGenerator: FieldGenerator,
    private val methodGenerator: MethodGenerator
) {
    fun buildJavaTest(): JTypeSpec.Builder {
        return JTypeSpec.classBuilder("SampleIntegrationTest")
            .addAnnotation(annotationGenerator.springBootTestAnnotation.build())
            .addAnnotation(annotationGenerator.autoConfigureMockMvcAnnotation.build())
            .addAnnotation(annotationGenerator.withMockUserAnnotation.build())
            .addAnnotation(annotationGenerator.extensionsAnnotation.build())
            .addField(fieldGenerator.repositoryField().build())
            .addMethod(methodGenerator.setupTestMethod().build())
            .addMethod(methodGenerator.initTestMethod().build())
            .addMethod(methodGenerator.createEntityMethod().build())
            .addMethod(methodGenerator.getAllEntitiesMethod().build())
            .addMethod(methodGenerator.getEntityMethod().build())
            .addMethod(methodGenerator.updateEntityMethod().build())
            .addMethod(methodGenerator.deleteEntityMethod().build())
    }
}
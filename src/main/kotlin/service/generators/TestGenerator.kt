package service.generators

import domain.RTTest.ReadyToTestModel
import service.generators.annotations.AnnotationGenerator
import service.generators.fields.FieldGenerator
import service.generators.javadocs.JavaDocGenerator
import service.generators.methods.MethodGenerator
import com.squareup.javapoet.TypeSpec as JTypeSpec

class TestGenerator(
    private val annotationGenerator: AnnotationGenerator,
    private val fieldGenerator: FieldGenerator,
    private val methodGenerator: MethodGenerator,
    private val javaDocGenerator: JavaDocGenerator
) {
    fun buildJavaTest(rtModel: List<ReadyToTestModel>): JTypeSpec.Builder {
        return JTypeSpec.classBuilder("SampleIntegrationTest")
            .addAnnotation(annotationGenerator.springBootTestAnnotation.build())
//            .addAnnotation(annotationGenerator.autoConfigureMockMvcAnnotation.build())
//            .addAnnotation(annotationGenerator.withMockUserAnnotation.build())
//            .addAnnotation(annotationGenerator.extensionsAnnotation.build())
            .addJavadoc(javaDocGenerator.testClassJavaDocGenerator())
            .addField(fieldGenerator.repositoryField().build())
            .addMethods(methodGenerator.generateBasicGetMethod(rtModel))
//            .addMethod(methodGenerator.setupTestMethod().build())
//            .addMethod(methodGenerator.initTestMethod().build())
//            .addMethod(methodGenerator.createEntityMethod().build())
//            .addMethod(methodGenerator.getAllEntitiesMethod().build())
//            .addMethod(methodGenerator.getEntityMethod().build())
//            .addMethod(methodGenerator.updateEntityMethod().build())
//            .addMethod(methodGenerator.deleteEntityMethod().build())
    }
}
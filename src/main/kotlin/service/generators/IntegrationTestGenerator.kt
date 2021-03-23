package service.generators

import service.generators.annotations.IAnnotationGenerator
import com.squareup.javapoet.AnnotationSpec as JAnnotationSpec
import com.squareup.javapoet.ClassName as JClassName
import com.squareup.javapoet.FieldSpec as JFieldSpec
import com.squareup.javapoet.TypeSpec as JTypeSpec
import javax.lang.model.element.Modifier as JModifier

class IntegrationTestGenerator(
    private val annotationGenerator: IAnnotationGenerator
) {
    fun getJavaITBuilder(): JTypeSpec.Builder {
        return JTypeSpec.classBuilder("SampleIntegrationTest")
            .addAnnotation(annotationGenerator.springBootTestAnnotation.build())
            .addAnnotation(annotationGenerator.autoConfigureMockMvcAnnotation.build())
            .addAnnotation(annotationGenerator.withMockUserAnnotation.build())
            .addAnnotation(annotationGenerator.extensionsAnnotation.build())
            .addField(
                JFieldSpec
                    .builder(JClassName.get("com.example", "SampleRepository"), "fooRepo")
                    .addAnnotation(annotationGenerator.autowiredAnnotation.build())
                    .addModifiers(JModifier.PRIVATE, JModifier.STATIC, JModifier.FINAL)
                    .build()
            )
    }
}
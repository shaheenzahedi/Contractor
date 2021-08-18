package service.generators.code.fields

import com.squareup.javapoet.FieldSpec
import service.generators.code.annotations.AnnotationGenerator
import service.generators.code.classes.ClassGenerator
import javax.lang.model.element.Modifier

class JFieldGenerator(
    private val annotationGenerator: AnnotationGenerator,
    private val classGenerator: ClassGenerator
) : FieldGenerator {
    override fun repositoryField(): FieldSpec.Builder = FieldSpec
        .builder(classGenerator.getResponseEntity(), "entity")
//        .addAnnotation(annotationGenerator.autowiredAnnotation.build())
        .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
}
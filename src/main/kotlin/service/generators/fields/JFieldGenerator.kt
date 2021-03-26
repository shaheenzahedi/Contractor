package service.generators.fields

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.FieldSpec
import service.generators.annotations.IAnnotationGenerator
import javax.lang.model.element.Modifier

class JFieldGenerator(
    private val annotationGenerator: IAnnotationGenerator
) : IFieldGenerator {
    override fun repositoryField(): FieldSpec.Builder {
        return FieldSpec
            .builder(ClassName.get("com.example", "SampleRepository"), "fooRepo")
            .addAnnotation(annotationGenerator.autowiredAnnotation.build())
            .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
    }
}
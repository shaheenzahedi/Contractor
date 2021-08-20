package service.generators.code.annotations

import com.squareup.javapoet.AnnotationSpec
import service.generators.code.classes.ClassGenerator

class JAnnotationGenerator(
    private val classGenerator: ClassGenerator,
    override val springBootTestAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.getSpringContextAnnotation())
        .addMember("classes", "[SampleApp::class]"),
    override val autoConfigureMockMvcAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.autoConfigureMockMvcAnnotation()),
    override val withMockUserAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.withMockUserAnnotation()),
    override val extensionsAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.extensionsAnnotation())
        .addMember("value", "ExtendWith(MockitoExtension::class)"),
    override val autowiredAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.autoWiredAnnotation()),
    override val beforeEachAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.beforeEachAnnotation()),
    override val transactionalAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.transactionalAnnotation()),
    override val testAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.testAnnotation()),
    override val beforeAllAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(classGenerator.beforeAllAnnotation())
) : AnnotationGenerator
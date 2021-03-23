package service.generators.annotations

import com.squareup.javapoet.AnnotationSpec


interface IAnnotationGenerator {
    val springBootTestAnnotation: AnnotationSpec.Builder
    val autoConfigureMockMvcAnnotation: AnnotationSpec.Builder
    val withMockUserAnnotation: AnnotationSpec.Builder
    val extensionsAnnotation: AnnotationSpec.Builder
    val autowiredAnnotation: AnnotationSpec.Builder
}
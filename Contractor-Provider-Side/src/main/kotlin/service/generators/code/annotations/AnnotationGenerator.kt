package service.generators.code.annotations

import com.squareup.javapoet.AnnotationSpec


interface AnnotationGenerator {
    val springBootTestAnnotation: AnnotationSpec.Builder
    val autoConfigureMockMvcAnnotation: AnnotationSpec.Builder
    val withMockUserAnnotation: AnnotationSpec.Builder
    val extensionsAnnotation: AnnotationSpec.Builder
    val autowiredAnnotation: AnnotationSpec.Builder
    val beforeEachAnnotation:AnnotationSpec.Builder
    val beforeAllAnnotation:AnnotationSpec.Builder
    val transactionalAnnotation:AnnotationSpec.Builder
    val testAnnotation:AnnotationSpec.Builder
}
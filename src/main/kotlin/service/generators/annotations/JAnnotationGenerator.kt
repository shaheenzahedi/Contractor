package service.generators.annotations

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName

class JAnnotationGenerator(
    override val springBootTestAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(ClassName.get("org.springframework.boot.test.context", "SpringBootTest"))
        .addMember("classes", "[SampleApp::class]"),
    override val autoConfigureMockMvcAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(ClassName.get("org.springframework.boot.test.autoconfigure.web.servlet", "AutoConfigureMockMvc")),
    override val withMockUserAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(ClassName.get("org.springframework.security.test.context.support", "WithMockUser")),
    override val extensionsAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(ClassName.get("org.junit.jupiter.api.extension", "Extensions"))
        .addMember("value", "ExtendWith(MockitoExtension::class)"),
    override val autowiredAnnotation: AnnotationSpec.Builder = AnnotationSpec
        .builder(ClassName.get("org.springframework.beans.factory.annotation", "Autowired"))
) : IAnnotationGenerator
package presentation

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier


fun main(args: Array<String>) {
//    val contractModel = JsonMapper<ContractModel>()
//        .getJson(
//            ContractModel::class.java,
//            "src/main/resources/contracts/sample-contract6.json"
//        )
    val x = TypeSpec.classBuilder("SampleIntegrationTest")
        .addAnnotation(
            AnnotationSpec
                .builder(ClassName.get("org.springframework.boot.test.context", "SpringBootTest"))
                .addMember("classes", "[MooverApp::class]")
                .build()
        )
        .addAnnotation(
            AnnotationSpec
                .builder(
                    ClassName.get("org.springframework.boot.test.autoconfigure.web.servlet", "AutoConfigureMockMvc")
                )
                .build()
        )
        .addAnnotation(
            AnnotationSpec
                .builder(ClassName.get("org.springframework.security.test.context.support", "WithMockUser"))
                .build()
        )
        .addAnnotation(
            AnnotationSpec
                .builder(ClassName.get("org.junit.jupiter.api.extension", "Extensions"))
                .addMember("value","ExtendWith(MockitoExtension::class)")
                .build()
        )
        .addField(
            FieldSpec
                .builder(ClassName.get("com.example","SampleRepository"),"fooRepo")
                .addAnnotation(
                    AnnotationSpec
                        .builder(ClassName.get("org.springframework.beans.factory.annotation", "Autowired"))
                        .build()
                )
                .addModifiers(Modifier.PRIVATE,Modifier.STATIC,Modifier.FINAL)
                .build()
        )
        .build()

    println(x.toString())
}
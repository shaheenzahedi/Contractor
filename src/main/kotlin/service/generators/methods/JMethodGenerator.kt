package service.generators.methods

import com.squareup.javapoet.MethodSpec
import domain.RTTest.ReadyToTestModel
import service.generators.annotations.AnnotationGenerator
import service.generators.classes.ClassGenerator

class JMethodGenerator(
    private val classGenerator: ClassGenerator,
    private val annotationGenerator: AnnotationGenerator
) : MethodGenerator {
    override fun setupTestMethod(): MethodSpec.Builder {
        return MethodSpec.methodBuilder("setup")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())
            .addStatement("MockitoAnnotations.initMocks(this)")
    }

    override fun generateBasicGetMethod(rtModel: List<ReadyToTestModel>): List<MethodSpec> {
        return rtModel.map {
            val methodBody = MethodSpec.methodBuilder("SampleTest")
                .addStatement("RestTemplate restTemplate = new RestTemplate()")
                .addStatement("ResponseEntity entity = restTemplate.getForEntity(\"http://localhost:8000${it.path}\", Object.class)")
            it.body?.onEach { entry ->
                methodBody.addStatement(
                    "BDDAssertions.then(((LinkedHashMap)entity.getBody()).get(\"${entry.key}\")).isEqualTo(${
                        putQuotationIfString(
                            entry.value
                        )
                    })"
                )
            }
            methodBody.build()
        }
    }

    private fun putQuotationIfString(entry: Any) = when (entry) {
        is String -> "\"$entry\""
        else -> entry
    }

    override fun initTestMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("initTest")
            .addAnnotation(annotationGenerator.beforeEachAnnotation.build())

    override fun createEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("createEntity")
            .addAnnotations(testCaseAnnotations)

    override fun getEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("getEntity")
            .addAnnotations(testCaseAnnotations)

    override fun getAllEntitiesMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("getAllEntities")
            .addAnnotations(testCaseAnnotations)

    override fun updateEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("updateEntity")
            .addAnnotations(testCaseAnnotations)

    override fun deleteEntityMethod(): MethodSpec.Builder =
        MethodSpec.methodBuilder("deleteEntity")
            .addAnnotations(testCaseAnnotations)

    private val testCaseAnnotations = mutableListOf(
        annotationGenerator.testAnnotation.build(),
        annotationGenerator.transactionalAnnotation.build()
    )
}
package service.generators.code.methods

import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.MethodSpec
import core.domain.ready_to_generate.ReadyToTestModel
import service.generators.code.annotations.AnnotationGenerator
import service.generators.code.javadocs.JavaDocGenerator
import service.generators.name.NameGenerator
import core.service.mapper.pact.PactPredicateType
import core.service.mapper.pact.PredicateModel

class JMethodGenerator(
    private val annotationGenerator: AnnotationGenerator,
    private val javaDocGenerator: JavaDocGenerator
) : MethodGenerator {

    private lateinit var nameGenerator: NameGenerator

    override fun setupTestMethod(): MethodSpec {
        return MethodSpec.methodBuilder("setup")
            .addAnnotation(annotationGenerator.beforeAllAnnotation.build())
            .addJavadoc(javaDocGenerator.setupTestJavaDocGenerator())
            .addStatement("RestTemplate restTemplate = new RestTemplate()")
            .addStatement("entity = restTemplate.getForEntity(\"http://localhost:8000/person/1\", Object.class)")
            .build()
    }

    override fun generateBasicGetMethod(rtModel: List<ReadyToTestModel>): List<MethodSpec?> {
        return rtModel.flatMap { interaction ->
            nameGenerator = NameGenerator(interaction)
            listOfNotNull(
                setupTestMethod(),
                generateBodyTest(interaction),
                generateHeaderTest(interaction),
                generateStatusTest(interaction),
            ).toMutableList().apply {
                val generateBodyRulesTest = generateBodyRulesTest(interaction)
                if (generateBodyRulesTest != null)
                    addAll(generateBodyRulesTest)
            }
        }
    }

    private fun generateBodyRulesTest(interaction: ReadyToTestModel): List<MethodSpec>? {
        val predicates = interaction.response?.bodyPredicates
        if (predicates.isNullOrEmpty()) return null
        val x = listOf(1, 2, 3).map { it.toString() }
        return predicates.map {
            val methodBody = MethodSpec
                .methodBuilder(nameGenerator.getRuleName(it))
                .addJavadoc(javaDocGenerator.rulesJavaDocGenerator(it))
                .addAnnotation(annotationGenerator.testAnnotation.build())
                .addStatement(
                    generateRuleStatments(it)
                )
            methodBody.build()
        }
    }

    private fun generateRuleStatments(model: PredicateModel): CodeBlock {
        if (model.type == PactPredicateType.MATCHES) {
            return CodeBlock.builder().apply {
                add(
                    "assert(((LinkedHashMap)entity.getBody()).get(\"${model.fieldName}\")).getClass().getSimpleName().equals(\"${model.value}\"))"
                )
            }.build()
        }
        return CodeBlock.builder().apply {
            add("assert(Pattern.matches(\"${model.value}\",((LinkedHashMap)entity.getBody()).get(\"${model.fieldName}\")))")
        }.build()
    }

    private fun generateStatusTest(model: ReadyToTestModel): MethodSpec {
        val methodBody = MethodSpec
            .methodBuilder(nameGenerator.getStatusTestName())
            .addJavadoc(javaDocGenerator.statusTestJavaDocGenerator(model))
            .addAnnotation(annotationGenerator.testAnnotation.build())
            .addStatement("assert(entity.getStatusCodeValue() == ${model.status})")
        return methodBody.build()
    }

    private fun generateHeaderTest(model: ReadyToTestModel): MethodSpec {
        val methodBody = MethodSpec.methodBuilder(nameGenerator.getHeaderTestName())
            .addJavadoc(javaDocGenerator.headerTestJavaDocGenerator(model))
            .addAnnotation(annotationGenerator.testAnnotation.build())
        model.response?.headers?.onEach {
            methodBody
                .addStatement("List<String> headers = entity.getHeaders().get(\"${it.key}\")")
                .addStatement("assert (headers != null)")
                .addStatement("assert (headers.get(0).equals(\"${it.value}\"))")
        }
        return methodBody.build()
    }

    private fun generateBodyTest(readyToTestModel: ReadyToTestModel): MethodSpec {
        val methodBody = MethodSpec
            .methodBuilder(nameGenerator.getBodyTestName())
            .addJavadoc(javaDocGenerator.bodyTestJavaDocGenerator(readyToTestModel))
            .addAnnotation(annotationGenerator.testAnnotation.build())
        readyToTestModel.response?.body?.onEach { entry ->
            methodBody.addStatement(
                "BDDAssertions.then(((LinkedHashMap)entity.getBody()).get(\"${entry.key}\")).isEqualTo(${
                    putQuotationIfString(
                        entry.value
                    )
                })"
            )
        }
        return methodBody.build()
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
package service.generators.code.javadocs

import com.squareup.javapoet.CodeBlock
import core.domain.ready_to_generate.ReadyToTestModel
import core.service.mapper.pact.PredicateModel

class JJavaDocGenerator : JavaDocGenerator {

    override fun rulesJavaDocGenerator(model: PredicateModel): CodeBlock {
        return makeCodeBlock(
            listOf(
                "Asserts if ${model.fieldName} ${model.type} is ${model.value}"
            )
        )
    }

    override fun statusTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock {
        return makeCodeBlock(
            listOf(
                "`${model.method?.name}\t${model.path}\n\n",
                "Asserts if the status equals to ${model.status}"
            )
        )
    }

    override fun testClassJavaDocGenerator(): CodeBlock {
        return makeCodeBlock(
            listOf("BDD tests generated from the contracts from consumer-side\n\n")
        )
    }

    override fun headerTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock {
        return makeCodeBlock(
            listOf(
                "`${model.method?.name}\t${model.path}\n\n",
                "Asserts that the response has the desired header\n",
                "Asserts if the response header matches to what we defined in the contract\n"
            )
        )
    }

    override fun bodyTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock {
        return makeCodeBlock(
            listOf(
                "`${model.method?.name}\t${model.path}\n\n",
                "Asserts if the Json is what we expect in the contract"
            )
        )
    }

    override fun setupTestJavaDocGenerator(): CodeBlock {
        return makeCodeBlock(listOf("Retrieves the desired Json and put it into an entity model"))
    }

    private fun makeCodeBlock(commentLines: List<String>): CodeBlock {
        return CodeBlock.builder().apply { commentLines.forEach { add(it) } }.build()
    }
}
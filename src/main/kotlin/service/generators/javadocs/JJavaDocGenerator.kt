package service.generators.javadocs

import com.squareup.javapoet.CodeBlock
import domain.RTTest.ReadyToTestModel

class JJavaDocGenerator : JavaDocGenerator {
    override fun statusTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock {
        return CodeBlock.builder()
            .add("`${model.method?.name}\t${model.path}\n\n")
            .add("Asserts if the status equals to ${model.status}")
            .build()
    }

    override fun testClassJavaDocGenerator(): CodeBlock {
        return CodeBlock.builder()
            .add("BDD tests generated from the contracts from consumer-side\n\n")
            .build()
    }

    override fun headerTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock {
        return CodeBlock.builder()
            .add("`${model.method?.name}\t${model.path}\n\n")
            .add("Asserts that the response has the desired header\n")
            .add("Asserts if the response header matches to what we defined in the contract\n")
            .build()
    }

    override fun bodyTestJavaDocGenerator(): CodeBlock {
        return CodeBlock.builder()
            .add("Asserts if the Json is what we expect in the contract")
            .build()
    }

    override fun setupTestJavaDocGenerator(): CodeBlock {
        return CodeBlock.builder()
            .add("Retrieves the desired Json and put it into an entity model")
            .build()
    }
}
package service.generators.javadocs

import com.squareup.javapoet.CodeBlock

class JJavaDocGenerator : JavaDocGenerator {
    override fun statusTestJavaDocGenerator(code: Int?): CodeBlock {
        return CodeBlock.builder()
            .add("Asserts if the status equals to $code")
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
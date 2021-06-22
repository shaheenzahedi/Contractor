package service.generators.javadocs

import com.squareup.javapoet.CodeBlock

class JJavaDocGenerator : JavaDocGenerator {
    override fun statusTestJavaDocGenerator(code: Int?): CodeBlock {
        return CodeBlock.builder()
            .add("Checks if the status equals to $code")
            .build()
    }

    override fun setupTestJavaDocGenerator(): CodeBlock {
        return CodeBlock.builder()
            .add("Retrieves the desired Json and put it into an entity model")
            .build()
    }
}
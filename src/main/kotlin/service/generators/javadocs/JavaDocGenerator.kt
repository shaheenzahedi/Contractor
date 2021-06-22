package service.generators.javadocs

import com.squareup.javapoet.CodeBlock

interface JavaDocGenerator {
    fun statusTestJavaDocGenerator(code:Int?): CodeBlock
    fun setupTestJavaDocGenerator(): CodeBlock
    fun bodyTestJavaDocGenerator(): CodeBlock
}
package service.generators.javadocs

import com.squareup.javapoet.CodeBlock
import domain.RTTest.ReadyToTestModel

interface JavaDocGenerator {
    fun statusTestJavaDocGenerator(code: ReadyToTestModel): CodeBlock
    fun setupTestJavaDocGenerator(): CodeBlock
    fun bodyTestJavaDocGenerator(): CodeBlock
    fun headerTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
    fun testClassJavaDocGenerator(): CodeBlock
}
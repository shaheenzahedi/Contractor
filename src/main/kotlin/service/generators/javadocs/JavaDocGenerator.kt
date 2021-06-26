package service.generators.javadocs

import com.squareup.javapoet.CodeBlock
import domain.RTTest.ReadyToTestModel

interface JavaDocGenerator {
    fun statusTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
    fun setupTestJavaDocGenerator(): CodeBlock
    fun headerTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
    fun testClassJavaDocGenerator(): CodeBlock
    fun bodyTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
}
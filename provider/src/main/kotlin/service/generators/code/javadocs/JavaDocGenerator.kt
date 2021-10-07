package service.generators.code.javadocs

import com.squareup.javapoet.CodeBlock
import core.domain.ready_to_generate.ReadyToTestModel
import core.service.mapper.pact.PredicateModel

interface JavaDocGenerator {
    fun statusTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
    fun setupTestJavaDocGenerator(): CodeBlock
    fun headerTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
    fun testClassJavaDocGenerator(): CodeBlock
    fun bodyTestJavaDocGenerator(model: ReadyToTestModel): CodeBlock
    fun rulesJavaDocGenerator(interaction: PredicateModel): CodeBlock
}
package service.generators.code.classes

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName

interface ClassGenerator {
    fun getRepository(): TypeName
    fun getResponseEntity(): TypeName
    fun getSpringContextAnnotation(): ClassName
    fun autoConfigureMockMvcAnnotation(): ClassName
    fun withMockUserAnnotation(): ClassName
    fun extensionsAnnotation(): ClassName
    fun autoWiredAnnotation(): ClassName
    fun beforeEachAnnotation(): ClassName
    fun transactionalAnnotation(): ClassName
    fun testAnnotation(): ClassName
    fun beforeAllAnnotation(): ClassName
}
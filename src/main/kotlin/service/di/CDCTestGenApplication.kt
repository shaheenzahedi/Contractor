package service.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import service.generators.TestGenerator
import service.generators.annotations.JAnnotationGenerator
import service.generators.classes.JClassGenerator
import service.generators.fields.JFieldGenerator
import service.generators.javadocs.JJavaDocGenerator
import service.generators.methods.JMethodGenerator
import service.io.resource.file.FileResource
import service.mapper.JsonMapper

class CDCTestGenApplication : KoinComponent {
    val testGenerator by inject<TestGenerator>()
    val jsonMapper by inject<JsonMapper>()
    val fileResource by inject<FileResource>()
    val fileResourceModule = module {
        single { FileResource() }
        single { JsonMapper(get() as FileResource) }
    }
    val integrationTestJavaModule = module {
        single { JClassGenerator() }
        single { JJavaDocGenerator() }
        single { JAnnotationGenerator(get() as JClassGenerator) }
        single { JMethodGenerator(get() as JClassGenerator, get() as JAnnotationGenerator, get() as JJavaDocGenerator) }
        single { JFieldGenerator(get() as JAnnotationGenerator, get() as JClassGenerator) }
        single { TestGenerator(get() as JAnnotationGenerator, get() as JFieldGenerator, get() as JMethodGenerator,get() as JJavaDocGenerator) }
    }
}
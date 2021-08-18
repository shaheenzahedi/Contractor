package service.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import service.generators.callback.CallbackGenerator
import service.generators.code.TestGenerator
import service.generators.code.annotations.JAnnotationGenerator
import service.generators.code.classes.JClassGenerator
import service.generators.code.fields.JFieldGenerator
import service.generators.code.javadocs.JJavaDocGenerator
import service.generators.code.methods.JMethodGenerator
import service.io.resource.file.FileResource
import service.mapper.JsonMapper

class CDCTestGenApplication : KoinComponent {
    val testGenerator by inject<TestGenerator>()
    val callbackGenerator by inject<CallbackGenerator>()
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
        single {
            JMethodGenerator(/*get() as JClassGenerator,*/ get() as JAnnotationGenerator,
                get() as JJavaDocGenerator
            )
        }
        single { JFieldGenerator(get() as JAnnotationGenerator, get() as JClassGenerator) }
        single {
            TestGenerator(
                get() as JAnnotationGenerator,
                get() as JFieldGenerator,
                get() as JMethodGenerator,
                get() as JJavaDocGenerator
            )
        }
    }
}
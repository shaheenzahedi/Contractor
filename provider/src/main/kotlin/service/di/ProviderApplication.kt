package service.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import service.generators.callback.CallbackMapper
import core.service.io.resource.file.FileResource
import core.service.mapper.JsonMapper

class ProviderApplication : KoinComponent {
//    val testGenerator by inject<TestGenerator>()
    val callbackMapper by inject<CallbackMapper>()
    val jsonMapper by inject<JsonMapper>()
//    val fileResource by inject<FileResource>()
    val fileResourceModule = module {
        single { FileResource() }
        single { JsonMapper(get() as FileResource) }
    }
    val integrationTestJavaModule = module {
        single { CallbackMapper() }
//        single { JClassGenerator() }
//        single { JJavaDocGenerator() }
//        single { JAnnotationGenerator(get() as JClassGenerator) }
//        single {
//            JMethodGenerator(/*get() as JClassGenerator,*/ get() as JAnnotationGenerator,
//                get() as JJavaDocGenerator
//            )
//        }
//        single { JFieldGenerator(get() as JAnnotationGenerator, get() as JClassGenerator) }
//        single {
//            TestGenerator(
//                get() as JAnnotationGenerator,
//                get() as JFieldGenerator,
//                get() as JMethodGenerator,
//                get() as JJavaDocGenerator
//            )
//        }
    }
}
package service.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import service.generators.IntegrationTestGenerator
import service.generators.annotations.JAnnotationGenerator
import service.generators.fields.JFieldGenerator

class CDCTestGenApplication : KoinComponent {
    val integrationTestGenerator by inject<IntegrationTestGenerator>()
    val integrationTestJavaModule = module {
        single { JAnnotationGenerator() }
        single { JFieldGenerator() }
        single { IntegrationTestGenerator(get() as JAnnotationGenerator, get() as JFieldGenerator) }
    }
}
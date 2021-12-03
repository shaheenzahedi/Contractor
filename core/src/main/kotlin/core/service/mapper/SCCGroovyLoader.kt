package core.service.mapper

import groovy.lang.GroovyClassLoader
import groovy.lang.Script
import org.springframework.cloud.contract.spec.Contract

import java.io.File

class SCCGroovyLoader(private val path: String) {
    fun getContract(): Contract {
        val loader = GroovyClassLoader(javaClass.classLoader)
        loader.loadClass("org.springframework.cloud.contract.spec.Contract")
        val clazz: Class<*> = loader.parseClass(File(path))
        return ((clazz.getDeclaredConstructor().newInstance() as Script).run()) as Contract
    }
}
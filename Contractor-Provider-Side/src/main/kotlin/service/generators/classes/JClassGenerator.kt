package service.generators.classes

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName

class JClassGenerator : ClassGenerator {
    override fun getRepository(): TypeName {
        return ClassName.get("com.example", "SampleRepository")
    }

    override fun beforeAllAnnotation(): ClassName {
        return ClassName.get("org.junit.jupiter.api", "BeforeAll")
    }

    override fun getResponseEntity(): TypeName {
        return ClassName.get("org.springframework.http", "ResponseEntity")
    }

    override fun getSpringContextAnnotation(): ClassName {
        return ClassName.get("org.springframework.boot.test.context", "SpringBootTest")
    }

    override fun autoConfigureMockMvcAnnotation(): ClassName {
        return ClassName.get("org.springframework.boot.test.autoconfigure.web.servlet", "AutoConfigureMockMvc")
    }

    override fun withMockUserAnnotation(): ClassName {
        return ClassName.get("org.springframework.security.test.context.support", "WithMockUser")
    }

    override fun extensionsAnnotation(): ClassName {
        return ClassName.get("org.junit.jupiter.api.extension", "Extensions")
    }

    override fun autoWiredAnnotation(): ClassName {
        return ClassName.get("org.springframework.beans.factory.annotation", "Autowired")
    }

    override fun beforeEachAnnotation(): ClassName {
        return ClassName.get("org.junit.jupiter.api", "BeforeEach")
    }

    override fun transactionalAnnotation(): ClassName {
        return ClassName.get("org.springframework.transaction.annotation", "Transactional")
    }

    override fun testAnnotation(): ClassName {
        return ClassName.get("org.junit.jupiter.api", "Test")
    }
}
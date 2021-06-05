package service.generators.name

import domain.RTTest.HTTPMethod
import domain.RTTest.ReadyToTestModel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NameGeneratorTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun shouldReturnSampleTestWhenPathIsNull() {
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, null, null, null, null))
        val desiredOutcome = "sampleTest"
        assert(nameGenerator.getStatusTestName() == desiredOutcome)
        assert(nameGenerator.getBodyTestName() == desiredOutcome)
        assert(nameGenerator.getHeaderTestName() == desiredOutcome)
    }

    @Test
    fun shouldReturnSampleTestWhenMethodIsNull() {
        val nameGenerator = NameGenerator(ReadyToTestModel(null, "somePath", null, null, null))
        val desiredOutcome = "sampleTest"
        assert(nameGenerator.getStatusTestName() == desiredOutcome)
        assert(nameGenerator.getBodyTestName() == desiredOutcome)
        assert(nameGenerator.getHeaderTestName() == desiredOutcome)
    }

    @Test
    fun generatedNameShouldContainHTTPMethod() {
        val method = HTTPMethod.GET
        val nameGenerator = NameGenerator(ReadyToTestModel(method, "/entity/1", null, null, null))
        assert(nameGenerator.getHeaderTestName().contains(method.name))
        assert(nameGenerator.getBodyTestName().contains(method.name))
        assert(nameGenerator.getStatusTestName().contains(method.name))
    }

    @Test
    fun getHeaderTestName() {
    }

    @Test
    fun getBodyTestName() {
    }
}
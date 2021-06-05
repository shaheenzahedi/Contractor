package service.generators.name

import domain.RTTest.HTTPMethod
import domain.RTTest.ReadyToTestModel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NameGeneratorTest {



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
    fun generatedNameShouldShouldBeAbleToRemoveBackSlashes() {
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, "/entity/1", null, null, null))
        val backSlash = "/"
        assertFalse(nameGenerator.getHeaderTestName().contains(backSlash))
        assertFalse(nameGenerator.getBodyTestName().contains(backSlash))
        assertFalse(nameGenerator.getStatusTestName().contains(backSlash))
    }

    @Test
    fun generatedNameShouldConvertIdToWithIdWhenStringEndsWithNumbers() {
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, "/entity/12", null, null, null))
        val desiredOutcome = "WithId_1"
        assert(nameGenerator.getHeaderTestName().contains(desiredOutcome))
        assert(nameGenerator.getBodyTestName().contains(desiredOutcome))
        assert(nameGenerator.getStatusTestName().contains(desiredOutcome))
    }

    @Test
    fun generatedNameShouldConvertIdToWithIdWhenStringContainsNumbers() {
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, "/entity/12/entity2/12", null, null, null))
        val desiredOutcome = "WithId_1"
        assert(nameGenerator.getHeaderTestName().contains(desiredOutcome))
        assert(nameGenerator.getBodyTestName().contains(desiredOutcome))
        assert(nameGenerator.getStatusTestName().contains(desiredOutcome))
    }


    @Test
    fun generatedNameShouldConvertRootPath() {
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, "/", null, null, null))
        val desiredOutcome = "RootPath"
        assert(nameGenerator.getHeaderTestName().contains(desiredOutcome))
        assert(nameGenerator.getBodyTestName().contains(desiredOutcome))
        assert(nameGenerator.getStatusTestName().contains(desiredOutcome))
    }
}
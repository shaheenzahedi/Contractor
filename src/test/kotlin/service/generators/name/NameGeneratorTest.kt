package service.generators.name

import domain.ready_to_generate.HTTPMethod
import domain.ready_to_generate.ReadyToTestModel
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NameGeneratorTest {
    @Test
    fun shouldContainHeaderInHeaderName(){
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, null, null, null, null))
        assert(nameGenerator.getHeaderTestName().contains("Header"))
    }
    @Test
    fun shouldContainStatusAndCodeInStatusName(){
        val status = 200
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, null, null, null, status))
        assert(nameGenerator.getStatusTestName().contains("Status"))
        assert(nameGenerator.getStatusTestName().contains("$status"))
    }
    @Test
    fun shouldContainBodyInBodyName(){
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, null, null, null, null))
        assert(nameGenerator.getBodyTestName().contains("Body"))
    }


    @Test
    fun shouldReturnSampleTestWhenPathIsNull() {
        val nameGenerator = NameGenerator(ReadyToTestModel(HTTPMethod.GET, null, null, null, null))
        val desiredOutcome = "sample"
        assert(nameGenerator.getStatusTestName().contains(desiredOutcome))
        assert(nameGenerator.getBodyTestName().contains(desiredOutcome))
        assert(nameGenerator.getHeaderTestName().contains(desiredOutcome))
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
        val backSlash = '/'
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
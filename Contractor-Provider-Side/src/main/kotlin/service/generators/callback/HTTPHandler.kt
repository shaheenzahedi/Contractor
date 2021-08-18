package service.generators.callback

import domain.ready_to_generate.ReadyToTestModel
import khttp.responses.Response

class HTTPHandler(private val model:ReadyToTestModel) {
    fun retrieveResponse():Response{
        TODO()
//        return khttp.get()
    }
}
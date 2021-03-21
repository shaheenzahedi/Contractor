package service.mapper


import java.io.IOException
import java.io.InputStreamReader
import com.google.gson.Gson
import domain.contract.ContractModel


class ContractMapper {
    fun getJson() {
        try {
            InputStreamReader(
                this.javaClass.getResourceAsStream("/contracts/sample-contract3.json")
            ).use { reader ->
                val result: ContractModel = Gson().fromJson(reader, ContractModel::class.java)
                println(result.consumer.name) // prints "bat"
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
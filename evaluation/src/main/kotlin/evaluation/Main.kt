import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

private val SAMPLES_DIR = "./contract_samples/"
private val PROVIDER_SUFFIX = "-provider.txt"
private val KILLED_MUTATIONS_PREFIX = "KILLED"
private val SURVIVED_MUTATION_PREFIX = "SURVIVED"
private val SUCCESS_TESTS_PREFIX = "SUCCESS"
private val FAILED_TESTS_PREFIX = "FAILED"

fun getAllProvidersDirectoryNames(): Stream<Path> = Files.walk(Paths.get(SAMPLES_DIR))
    .filter { Files.isRegularFile(it) }
    .filter { it.fileName.toString().endsWith(PROVIDER_SUFFIX) }

fun getAllLinesStartsWith(file: File, parameter: String): Stream<String> =
    file.readLines().filter { it.startsWith("[$parameter]") }.stream()

fun main() {
    val allProviderFiles = getAllProvidersDirectoryNames()
        .flatMap { getAllLinesStartsWith(File(it.toString()), KILLED_MUTATIONS_PREFIX) }.sorted()
    allProviderFiles.forEach { println(it) }
//    File("./aaa.txt").printWriter().use { out -> allProviderFiles.forEach { out.println(it) } }
}





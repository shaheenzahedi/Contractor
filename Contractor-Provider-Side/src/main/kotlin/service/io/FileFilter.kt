package service.io

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

class FileFilter {
    fun filter(path: String, regex: String): Stream<Path>? {
        val matcher = FileSystems.getDefault()
            .getPathMatcher(regex.replace("?", ".?").replace("*", ".*?"))
        return Files.walk(Paths.get(path)).filter(matcher::matches)
    }
}
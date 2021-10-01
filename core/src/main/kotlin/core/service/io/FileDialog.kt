package core.service.io

import java.io.File
import java.io.FileNotFoundException
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class FileDialog {
    @Throws(FileNotFoundException::class)
    fun open(title: String, isDir: Boolean, filter: FileNameExtensionFilter?): String? {
        with(JFileChooser()) {
            currentDirectory = File(".")
            dialogTitle = title
            fileSelectionMode = when (isDir) {
                true -> JFileChooser.DIRECTORIES_ONLY
                false -> JFileChooser.FILES_ONLY
            }
            fileFilter = filter
            isAcceptAllFileFilterUsed = false
            return when (JFileChooser.APPROVE_OPTION) {
                showOpenDialog(null) -> selectedFile.toString()
                else -> null
            }
        }
    }
}
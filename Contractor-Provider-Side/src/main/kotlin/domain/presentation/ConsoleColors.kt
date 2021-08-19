package domain.presentation

enum class ConsoleColors(
    private val code: String
) {
    RESET("\u001b[0m"),
    GREEN_BOLD("\u001b[1;32m"),
    BLUE_BOLD("\u001b[1;34m"),
    MAGENTA_BOLD("\u001b[1;35m"),
    YELLOW_UNDERLINED("\u001b[4;33m"),
    YELLOW("\u001b[1;33m"),
    RED("\u001B[31m");

    override fun toString(): String {
        return code
    }
}

fun colorPrint(text: String, color: ConsoleColors) {
    print(color)
    print(text)
    print(ConsoleColors.RESET)
}
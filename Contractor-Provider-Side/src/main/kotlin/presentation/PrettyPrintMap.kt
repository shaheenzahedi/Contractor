package presentation

class PrettyPrintingMap<K, V>(private val map: Map<K, V>) {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("{").append("\n")
        val iter = map.entries.iterator()
        while (iter.hasNext()) {
            val (key, value) = iter.next()
            sb.append("\t")
            sb.append(key)
            sb.append('=').append('"')
            sb.append(value)
            sb.append('"')
            if (iter.hasNext()) {
                sb.append(',').append('\n')
            }
        }
        sb.append("\n").append("}")
        return sb.toString()
    }
}
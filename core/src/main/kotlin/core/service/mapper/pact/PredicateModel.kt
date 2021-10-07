package core.service.mapper.pact

data class PredicateModel(
    val fieldName: String?,
    val type: PactPredicateType,
    val value: Any

) {
    fun getValueAsType(): ValueType? {
        if (value !is String) return null
        return try {
            ValueType.valueOf(value.uppercase())
        } catch (e: Exception) {
            null
        }
    }
}

enum class PactPredicateType {
    MATCH,
    REGEX,
    TYPE
}

enum class ValueType {
    SHORT,
    JSONOBJECT,
    BIGDECIMAL,
    BYTE,
    BOOLEAN,
    BIGINTEGER,
    DOUBLE,
    FLOAT,
    INT,
    JSONARRAY,
    JSONNULL,
    LONG,
    NUMBER,
    STRING
}
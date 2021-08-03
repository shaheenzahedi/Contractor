package service.mapper.pact

data class PactPredicateModel(
    val fieldName: String?,
    val type: PactPredicateType,
    val value: String?
)

enum class PactPredicateType {
    MATCH,
    REGEX
}

enum class ValueType {
    DOUBLE,
    STRING
}
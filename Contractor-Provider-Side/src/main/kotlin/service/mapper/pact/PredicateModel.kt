package service.mapper.pact

data class PredicateModel(
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
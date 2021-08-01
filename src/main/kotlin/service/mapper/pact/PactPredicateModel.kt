package service.mapper.pact

data class PactPredicateModel(
    private val fieldName: String?,
    private val type: PactPredicateType,
    private val value: String
)

enum class PactPredicateType {
    MATCH,
    REGEX
}
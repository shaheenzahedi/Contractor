package domain.contract

interface GeneralContract {
    val type: SupportedTypes
    val isAllNull: Boolean
}
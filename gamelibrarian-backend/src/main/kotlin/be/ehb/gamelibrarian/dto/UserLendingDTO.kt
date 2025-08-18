package be.ehb.gamelibrarian.dto

data class UserLendingDTO(
    val boardgameTitle: String,
    val status: String,
    val loanDate: String?,
    val loanDeadline: String?
)

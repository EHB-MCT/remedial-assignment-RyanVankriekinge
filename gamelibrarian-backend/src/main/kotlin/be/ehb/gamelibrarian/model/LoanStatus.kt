package be.ehb.gamelibrarian.model

import jakarta.persistence.*

@Entity
@Table(name = "loan_statuses")
data class LoanStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val statusId: Long = 0,

    @Column(nullable = false, unique = true)
    val statusCode: String,

    @Column(nullable = false)
    val statusText: String
)

package be.ehb.gamelibrarian.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "lendings")
data class Lending(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val loanId: Long = 0,

    @ManyToOne(optional = false)
    @JoinColumn(name = "copy_id", nullable = false)
    val copy: BoardgameCopy,

    @ManyToOne(optional = false)
    @JoinColumn(name = "borrower_id", nullable = false)
    val borrower: BoardgameUser,

    @Column(nullable = false)
    val loanTimestamp: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val loanDeadline: LocalDateTime,

    val returnedAt: LocalDateTime? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    val status: LoanStatus
)

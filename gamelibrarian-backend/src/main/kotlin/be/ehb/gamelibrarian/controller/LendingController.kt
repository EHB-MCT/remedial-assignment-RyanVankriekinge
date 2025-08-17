package be.ehb.gamelibrarian.controller

import be.ehb.gamelibrarian.model.Lending
import be.ehb.gamelibrarian.service.BoardgameCopyService
import be.ehb.gamelibrarian.service.BoardgameUserService
import be.ehb.gamelibrarian.service.LendingService
import be.ehb.gamelibrarian.service.LoanStatusService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/lendings")
class LendingController(
    private val lendingService: LendingService,
    private val loanStatusService: LoanStatusService,
    private val boardgameUserService: BoardgameUserService,
    private val boardgameCopyService: BoardgameCopyService
) {
    data class CreateLendingRequest(
        @field:NotNull val copyId: Long,
        @field:NotNull val borrowerId: Long,
        @field:NotNull val statusId: Long,
        @field:NotNull val loanDeadline: LocalDateTime
    )

    data class LendingResponse(
        val id: Long,
        val copyId: Long,
        val borrowerId: Long,
        val loanTimestamp: LocalDateTime,
        val loanDeadline: LocalDateTime,
        val returnedAt: LocalDateTime?,
        val statusId: Long
    ) {
        companion object {
            fun from(l: Lending) = LendingResponse(
                l.loanId,
                l.copy.copyId,
                l.borrower.boardgameUserId,
                l.loanTimestamp,
                l.loanDeadline,
                l.returnedAt,
                l.status.statusId
            )
        }
    }

    @PostMapping
    fun create(@Valid @RequestBody req: CreateLendingRequest): ResponseEntity<LendingResponse> {
        val copy = boardgameCopyService.get(req.copyId)
        val borrower = boardgameUserService.get(req.borrowerId)
        val status = loanStatusService.get(req.statusId)

        val lending = Lending(
            copy = copy,
            borrower = borrower,
            loanDeadline = req.loanDeadline,
            status = status
        )

        val saved = lendingService.create(lending)
        return ResponseEntity.status(HttpStatus.CREATED).body(LendingResponse.from(saved))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): LendingResponse =
        LendingResponse.from(lendingService.get(id))

    @GetMapping
    fun getAll(): List<LendingResponse> =
        lendingService.getAll().map { LendingResponse.from(it) }

    @PutMapping("/{loanId}/return")
    fun returnCopy(@PathVariable loanId: Long): Lending {
        return lendingService.markAsReturned(loanId)
    }
}

package be.ehb.gamelibrarian.controller

import be.ehb.gamelibrarian.model.LoanStatus
import be.ehb.gamelibrarian.service.LoanStatusService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loanStatuses")
class LoanStatusController(
    private val loanStatusService: LoanStatusService
) {
    data class CreateLoanStatusRequest(
        @field:NotBlank val statusCode: String,
        @field:NotBlank val statusText: String
    )
    data class LoanStatusResponse(val id: Long, val statusCode: String, val statusText: String) {
        companion object {
            fun from(ls: LoanStatus) = LoanStatusResponse(ls.statusId, ls.statusCode, ls.statusText)
        }
    }

    @PostMapping
    fun create(@Valid @RequestBody req: CreateLoanStatusRequest): ResponseEntity<LoanStatusResponse> {
        val ls = loanStatusService.create(req.statusCode, req.statusText)
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanStatusResponse.from(ls))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): LoanStatusResponse =
        LoanStatusResponse.from(loanStatusService.get(id))

    @GetMapping
    fun getAll(): List<LoanStatusResponse> =
        loanStatusService.getAll().map { LoanStatusResponse.from(it) }
}
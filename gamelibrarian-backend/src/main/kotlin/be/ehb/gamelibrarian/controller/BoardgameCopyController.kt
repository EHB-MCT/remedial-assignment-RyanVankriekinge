package be.ehb.gamelibrarian.controller

import be.ehb.gamelibrarian.dto.BoardgameCopyDTO
import be.ehb.gamelibrarian.model.BoardgameCopy
import be.ehb.gamelibrarian.service.BoardgameCopyService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boardgameCopies")
class BoardgameCopyController(
    private val copyService: BoardgameCopyService
) {
    data class CreateCopyRequest(
        @field:Min(1) val boardgameId: Long,
        @field:Min(1) val ownerId: Long
    )
    data class CopyResponse(val id: Long, val boardgameId: Long, val ownerId: Long) {
        companion object {
            fun from(c: BoardgameCopy) =
                CopyResponse(c.copyId, c.boardgame.boardgameId, c.owner.boardgameUserId)
        }
    }

    @PostMapping
    fun create(@Valid @RequestBody req: CreateCopyRequest): ResponseEntity<CopyResponse> {
        val copy = copyService.create(req.boardgameId, req.ownerId)
        return ResponseEntity.status(HttpStatus.CREATED).body(CopyResponse.from(copy))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): CopyResponse =
        CopyResponse.from(copyService.get(id))

    @GetMapping
    fun getAll(): List<CopyResponse> =
        copyService.getAll().map { CopyResponse.from(it) }

    @GetMapping("/boardgames/{boardgameId}/copies-info")
    fun getCopiesInfo(@PathVariable boardgameId: Long): List<BoardgameCopyDTO> {
        return copyService.getCopiesInfoForBoardgame(boardgameId)
    }
}
package be.ehb.gamelibrarian.controller

import be.ehb.gamelibrarian.model.Boardgame
import be.ehb.gamelibrarian.service.BoardgameService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boardgames")
class BoardgameController(
    private val gameService: BoardgameService
) {
    data class CreateBoardgameRequest(
        @field:NotBlank val title: String,
        @field:NotBlank val publisher: String,
        @field:Min(1800) val year: Int,
        @field:Min(1) val minPlayers: Int,
        @field:Min(1) val maxPlayers: Int,
        val bggId: String? = null,
        val bggURL: String? = null,
        val imageURL: String? = null
    )
    data class BoardgameResponse(
        val id: Long,
        val title: String,
        val publisher: String,
        val year: Int,
        val minPlayers: Int,
        val maxPlayers: Int,
        val bggId: String?,
        val bggURL: String?,
        val imageURL: String?
    ) {
        companion object {
            fun from(bg: Boardgame) = BoardgameResponse(
                bg.boardgameId,
                bg.title,
                bg.publisher,
                bg.year,
                bg.minPlayers,
                bg.maxPlayers,
                bg.bggId,
                bg.bggURL,
                bg.imageURL
            )
        }
    }

    @PostMapping
    fun create(@Valid @RequestBody req: CreateBoardgameRequest): ResponseEntity<BoardgameResponse> {
        val g = gameService.create(
            req.title,
            req.publisher,
            req.year,
            req.minPlayers,
            req.maxPlayers,
            req.bggId,
            req.bggURL,
            req.imageURL
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(BoardgameResponse.from(g))
    }
}

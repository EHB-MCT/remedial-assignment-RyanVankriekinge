package be.ehb.gamelibrarian.controller

import be.ehb.gamelibrarian.model.BoardgameUser
import be.ehb.gamelibrarian.service.BoardgameUserService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boardgameUsers")
class BoardgameUserController(
    private val userService: BoardgameUserService
) {
    data class CreateUserRequest(
        @field:NotBlank val name: String,
        @field:Email val email: String
    )
    data class UserResponse(val id: Long, val name: String, val email: String) {
        companion object {
            fun from(u: BoardgameUser) = UserResponse(u.boardgameUserId, u.name, u.email)
        }
    }

    @PostMapping
    fun create(@Valid @RequestBody req: CreateUserRequest): ResponseEntity<UserResponse> {
        val u = userService.create(req.name, req.email)
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(u))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): UserResponse =
        UserResponse.from(userService.get(id))
}

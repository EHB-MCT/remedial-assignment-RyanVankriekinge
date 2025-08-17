package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.BoardgameUser
import be.ehb.gamelibrarian.repository.BoardgameUserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardgameUserService(
    private val users: BoardgameUserRepository
) {
    @Transactional
    fun create(name: String, email: String): BoardgameUser {
        require(name.isNotBlank()) { "Name cannot be blank" }
        if (users.existsByEmail(email)) {
            throw IllegalArgumentException("Email already exists")
        }
        return users.save(BoardgameUser(name = name, email = email))
    }

    fun get(id: Long): BoardgameUser =
        users.findById(id).orElseThrow { NoSuchElementException("User $id not found") }
}

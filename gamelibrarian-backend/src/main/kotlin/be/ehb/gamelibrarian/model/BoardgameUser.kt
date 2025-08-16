package be.ehb.gamelibrarian.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "boardgame_users")
data class BoardgameUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardgameUserId: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String
)
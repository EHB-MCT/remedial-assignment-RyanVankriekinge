package be.ehb.gamelibrarian.model

import jakarta.persistence.*

@Entity
@Table(name = "boardgames")
data class Boardgame(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardgameId: Long = 0,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val publisher: String,

    @Column(nullable = false)
    val year: Int,

    @Column(nullable = false)
    val minPlayers: Int,

    @Column(nullable = false)
    val maxPlayers: Int,

    val bggId: String? = null,
    val bggURL: String? = null,
    val imageURL: String? = null
)
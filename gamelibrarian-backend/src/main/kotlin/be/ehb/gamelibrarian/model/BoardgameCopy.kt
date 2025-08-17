package be.ehb.gamelibrarian.model

import jakarta.persistence.*

@Entity
@Table(name = "boardgame_copies")
data class BoardgameCopy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val copyId: Long = 0,

    @ManyToOne(optional = false)
    @JoinColumn(name = "boardgame_id", nullable = false)
    val boardgame: Boardgame,

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    val owner: BoardgameUser
)
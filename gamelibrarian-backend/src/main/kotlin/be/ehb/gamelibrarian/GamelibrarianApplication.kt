package be.ehb.gamelibrarian

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GamelibrarianApplication

fun main(args: Array<String>) {
	runApplication<GamelibrarianApplication>(*args)
}

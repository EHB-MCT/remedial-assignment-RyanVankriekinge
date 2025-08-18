package be.ehb.gamelibrarian

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class GamelibrarianApplication {
	@Bean
	fun corsConfigurer(): WebMvcConfigurer {
		return object : WebMvcConfigurer {
			override fun addCorsMappings(registry: CorsRegistry) {
				registry.addMapping("/api/**").allowedOrigins("http://127.0.0.1:5500")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<GamelibrarianApplication>(*args)
}

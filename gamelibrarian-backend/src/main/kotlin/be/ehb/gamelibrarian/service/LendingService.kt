package be.ehb.gamelibrarian.service

import be.ehb.gamelibrarian.model.Lending
import be.ehb.gamelibrarian.repository.LendingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LendingService(
    private val lendings: LendingRepository
) {
    @Transactional
    fun create(lending: Lending): Lending =
        lendings.save(lending)

    fun get(id: Long): Lending =
        lendings.findById(id).orElseThrow { NoSuchElementException("Lending $id not found") }

    fun getAll(): List<Lending> = lendings.findAll()
}

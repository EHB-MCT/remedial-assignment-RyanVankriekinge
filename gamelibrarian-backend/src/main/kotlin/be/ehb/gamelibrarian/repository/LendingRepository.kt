package be.ehb.gamelibrarian.repository

import be.ehb.gamelibrarian.model.Lending
import org.springframework.data.jpa.repository.JpaRepository

interface LendingRepository : JpaRepository<Lending, Long>

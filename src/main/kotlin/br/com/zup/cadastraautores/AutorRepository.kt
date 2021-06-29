package br.com.zup.cadastraautores

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository: JpaRepository<Autor, Long> {
        override fun findById(id: Long): Optional<Autor>
}
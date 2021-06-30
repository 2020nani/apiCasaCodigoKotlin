package br.com.zup.listaAutores

import br.com.zup.cadastraautores.Autor
import br.com.zup.cadastraautores.AutorRepository
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.Sort
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.exceptions.HttpException
import java.util.*
import javax.transaction.Transactional


@Controller("autores")
class ListaAutoresController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun listaAutores(
        @QueryValue(defaultValue = "") email: String,
        @QueryValue(defaultValue = "0") page: String,
        @QueryValue(defaultValue = "5") size: String
    ): HttpResponse<Any> {
        if (email.isBlank()) {
            val autores = autorRepository.findAll(
                Pageable.from(page.toInt(), size.toInt()))
            val autoresDto = autores.map { autor -> AutoresDto(autor) }

            return HttpResponse.ok(autoresDto)
        }
        autorRepository.findByEmail(email).let {
            if (it.isEmpty) {
                return HttpResponse.unprocessableEntity()
            }
            return HttpResponse.ok(AutoresDto(it.get()))
        }


    }

    @Get("/{id}")
    @Transactional
    fun listaAutorPorId(@PathVariable("id") id: Long): HttpResponse<Any> {
        val error: String = "Nao ha autor com este id"
        val autor: Optional<Autor> = autorRepository.findById(id)
        if (autor.isEmpty) {
            return HttpResponse.notFound(error)
        }
        val autorDto = AutoresDto(autor.get())
        return HttpResponse.ok(autorDto)
    }
}




package br.com.zup.listaAutores

import br.com.zup.cadastraautores.Autor
import br.com.zup.cadastraautores.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.exceptions.HttpException
import java.util.*


@Controller("autores")
class ListaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun listaAutores(): HttpResponse<List<AutoresDto>> {

        val autores = autorRepository.findAll()
        val autoresDto = autores.map { autor -> AutoresDto(autor) }

        return HttpResponse.ok(autoresDto)

    }

    @Get("/{id}")
    fun listaAutorPorId(@PathVariable("id") id: Long): HttpResponse<Any>{
        val error: String = "Nao ha autor com este id"
        val autor: Optional<Autor> = autorRepository.findById(id)
        if (autor.isEmpty){
            return HttpResponse.notFound(error)
        }
        val autorDto = AutoresDto(autor.get())
        return HttpResponse.ok(autorDto)
    }
}




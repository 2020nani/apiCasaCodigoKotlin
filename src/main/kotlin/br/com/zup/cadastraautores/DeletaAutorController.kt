package br.com.zup.cadastraautores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.transaction.Transactional

@Controller(value = "/autores")
class DeletaAutorController (val autorRepository: AutorRepository){

    @Delete(value = "/{id}")
    @Transactional
    fun deletaAutor(@PathVariable("id") id: Long): HttpResponse<Any>{
         autorRepository.findById(id).let { autor ->
             if(autor.isEmpty) {return HttpResponse.unprocessableEntity()}
             autorRepository.deleteById(id)
             return HttpResponse.ok("Deletado com sucesso")
         }
    }
}
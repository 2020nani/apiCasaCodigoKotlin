package br.com.zup.cadastraautores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "autores")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put(value = "/{id}")
    @Transactional
    fun atualizaAutor(@PathVariable("id") id: Long,
                      @Body @Valid novoAutorForm: NovoAutorForm): HttpResponse<Any>  {
        val autor: Optional<Autor> = autorRepository.findById(id)
        if(autor.isEmpty){
            return HttpResponse.unprocessableEntity()
        }
        val autorAtualizado = autor.get().atualiza(novoAutorForm)

        autorRepository.update(autorAtualizado)
        return HttpResponse.ok(autorAtualizado)

    }
}
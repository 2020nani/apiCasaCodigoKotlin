package br.com.zup.cadastraautores



import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastraAutores(@Body @Valid novoautorform: NovoAutorForm): HttpResponse<Any> {

        val autor: Autor = novoautorform.converte()
        autorRepository.save(autor)
         val Uri = UriBuilder.of("autores/{id}")
             .expand(mutableMapOf(Pair("id", autor.id)))
        return created(autor, Uri)
    }
}
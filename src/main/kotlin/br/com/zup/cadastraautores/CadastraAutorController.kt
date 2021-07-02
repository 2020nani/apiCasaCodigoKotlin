package br.com.zup.cadastraautores


import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.badRequest
import io.micronaut.http.HttpResponse.created
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastraAutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient
    ) {

    @Post
    @Transactional
    fun cadastraAutores(@Body @Valid novoautorform: NovoAutorForm): HttpResponse<Any> {

        val enderecoform = enderecoClient.buscaEndereco(novoautorform.cep).body()

        if(enderecoform == null || enderecoform.bairro.equals("x")) return badRequest("Erro ao buscar endereco")


        val autor: Autor = novoautorform.converte(enderecoform)
        autorRepository.save(autor)
        val Uri = UriBuilder.of("autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return created(autor, Uri)

    }
}
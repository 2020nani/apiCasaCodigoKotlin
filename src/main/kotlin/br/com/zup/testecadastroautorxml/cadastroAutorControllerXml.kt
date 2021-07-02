package br.com.zup.testecadastroautorxml

import br.com.zup.cadastraautores.*
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autoresxml")
class cadastroAutorControllerXml (
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClientXML
) {

    @Post
    @Transactional
    fun cadastraAutores(@Body @Valid novoautorform: NovoAutorForm): HttpResponse<Any> {

        val httpResponse = enderecoClient.buscaEnderecoXml(novoautorform.cep)

        var logradouro: String = ""
        var bairro: String = ""
        var localidade: String = ""
        var uf: String = ""
        var ddd: String = ""

        var build = XmlMapper.builder().build()

        if (!httpResponse.body().contains("erro")){
            logradouro = build.readValue(httpResponse.body(), JsonNode::class.java).get("logradouro").asText()
            bairro = build.readValue(httpResponse.body(), JsonNode::class.java).get("bairro").asText()
            localidade = build.readValue(httpResponse.body(), JsonNode::class.java).get("localidade").asText()
            uf = build.readValue(httpResponse.body(), JsonNode::class.java).get("uf").asText()
            ddd = build.readValue(httpResponse.body(), JsonNode::class.java).get("ddd").asText()
        }

        val enderecoForm = EnderecoForm(logradouro,bairro,localidade, uf, ddd)

       // if(enderecoform.bairro.equals("x")) return HttpResponse.badRequest("Erro ao buscar endereco")


        val autor: Autor = novoautorform.converte(enderecoForm)
        autorRepository.save(autor)
        val Uri = UriBuilder.of("autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(autor, Uri)

    }
}
package br.com.zup.cadastraautores

import io.micronaut.http.HttpResponse
import io.micronaut.retry.annotation.Fallback

@Fallback
class EnderecoClientFallback: EnderecoClient {
    override fun buscaEndereco(cep: String): HttpResponse<EnderecoForm> {
        val enderecoForm = EnderecoForm("x","x","x","x","x")

        return HttpResponse.ok(enderecoForm)
    }
}
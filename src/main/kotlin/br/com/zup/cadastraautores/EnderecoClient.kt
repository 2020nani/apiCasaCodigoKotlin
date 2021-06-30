package br.com.zup.cadastraautores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    //post utiliza produces
    @Get("/{cep}/json/")
    @Consumes(MediaType.APPLICATION_XML,MediaType.APPLICATION_ATOM_XML)
    fun buscaEndereco(cep: String): HttpResponse<EnderecoForm>
}
package br.com.zup.cadastraautores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.Recoverable
import io.micronaut.retry.annotation.Retryable
import kotlin.reflect.KClass

@Client(value = "https://viacep.com.br/ws")
@Retryable(attempts = "1")
interface EnderecoClient {

    //post utiliza produces
    @Get("/{cep}/xml", produces = ["application/xhtml+xml"])
    @Consumes(MediaType.APPLICATION_XML,MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_XHTML)
    fun buscaEndereco(cep: String): HttpResponse<EnderecoForm>
}

/*Execution failed for task ':kaptKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptExecution
> java.lang.reflect.InvocationTargetException (no error message)*/
//kapt.use.worker.api=false
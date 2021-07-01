package br.com.zup.cadastraautores

import br.com.zup.listaAutores.AutoresDto
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastraAutorControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    internal fun `deve-cadastrar-autor-banco-de-dados`() {

        val novoAutorForm = NovoAutorForm("hernani", "her@hotmail.com", "de boa", "18608460", "1274")

        val enderecoForm = EnderecoForm("1","v j","botucatu","Sp","'4")

      //  val endereco = Endereco("1","v j","botucatu","Sp","'4","1274")

        Mockito.`when`(enderecoClient.buscaEndereco(novoAutorForm.cep)).thenReturn(HttpResponse.ok(enderecoForm))

        val request = HttpRequest.POST("/autores", novoAutorForm)

        val response = client
            .toBlocking()
            .exchange(request, Any::class.java)
        println(response.header("Location"))
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertEquals("autores/1",response.header("Location"))

    }

    @MockBean(EnderecoClient::class)
    fun enderecoClientMock(): EnderecoClient? {
        return Mockito.mock(EnderecoClient::class.java)
    }


}
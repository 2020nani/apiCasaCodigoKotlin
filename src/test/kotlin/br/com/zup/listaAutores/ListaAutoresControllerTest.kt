package br.com.zup.listaAutores

import br.com.zup.cadastraautores.*
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class ListaAutoresControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup(){
        val enderecoForm = EnderecoForm("1","v j","botucatu","Sp","'4")
        val autorform = NovoAutorForm("Hernani","teste@hotmail.com","de boa","18608460","1274")
        autor = autorform.converte(enderecoForm)
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteById(autor.id)
    }

    @Test
    internal fun `deve retornar ator por email`() {
        val response = client
            .toBlocking()
            .exchange("/autores?email=${autor.email}", AutoresDto::class.java)
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertNotNull(autor.endereco!!.id)
        assertEquals(autor.nome, response.body()!!.nome)
    }


}
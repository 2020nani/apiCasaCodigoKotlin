package br.com.zup.cadastraautores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastraAutorController {

    @Post
    fun cadastraAutores(@Body @Valid novoautorform: NovoAutorForm){

        val autor: Autor = novoautorform.converte()
        println(autor)
    }
}
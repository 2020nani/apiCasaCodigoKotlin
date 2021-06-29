package br.com.zup.cadastraautores

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

import javax.validation.constraints.Size

@Introspected
data class NovoAutorForm(
    @field:NotBlank(message = "Campo nome tem que ser preenchido") val nome: String,
    @field:NotBlank(message = "Campo nome tem que ser preenchido")
    @field:Email(message = "Digite um email valido") val email: String,
    @field:NotBlank(message = "Campo descricao tem que ser preenchido")
    @field:Size(max = 400, message = "Nao pode ser maios que 400 caracteres") val descricao: String
) {

    fun converte(): Autor {
         return Autor(nome, email, descricao)
    }

}

package br.com.zup.cadastraautores

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

import javax.validation.constraints.Size

@Introspected
data class NovoAutorForm(
    @field:NotBlank(message = "Campo nome tem que ser preenchido") val nome: String,
    @field:NotBlank(message = "Campo nome tem que ser preenchido")
    @field:Email(message = "Digite um email valido")  val email: String,
    @field:NotBlank(message = "Campo descricao tem que ser preenchido")
    @field:Size(max = 400, message = "Nao pode ser maior que 400 caracteres") val descricao: String,
    @field:NotBlank() val cep: String,
    @field:NotBlank() val numero: String
) {

    fun converte(enderecoform: EnderecoForm): Autor {

        val endereco: Endereco = enderecoform.converte(numero)

        return Autor(nome, email, descricao, endereco)
    }



}

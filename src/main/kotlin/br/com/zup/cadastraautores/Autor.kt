package br.com.zup.cadastraautores

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Autor(
    @field:NotBlank(message = "Campo nome tem que ser preenchido") val nome: String,
    @field:NotBlank(message = "Campo nome tem que ser preenchido")
    @field:Email(message = "Digite um email valido") val email: String,
    @field:NotBlank(message = "Campo descricao tem que ser preenchido")
    @field:Size(max = 400, message = "Nao pode ser maios que 400 caracteres") val descricao: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @CreationTimestamp
    val criadoEm:LocalDate?= null
}

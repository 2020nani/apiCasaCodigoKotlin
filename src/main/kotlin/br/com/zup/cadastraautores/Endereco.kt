package br.com.zup.cadastraautores

import javax.persistence.*


@Entity
class Endereco(
    val rua: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val ddd: String,
    val numero: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

package br.com.zup.cadastraautores

data class EnderecoForm (
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ddd: String
        ) {
    fun converte(numero: String): Endereco {
       return Endereco(logradouro, bairro, localidade, uf, ddd, numero)
    }

}

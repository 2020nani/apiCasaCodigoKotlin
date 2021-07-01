package br.com.zup.listaAutores

import br.com.zup.cadastraautores.Autor

class AutoresDto(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao

}

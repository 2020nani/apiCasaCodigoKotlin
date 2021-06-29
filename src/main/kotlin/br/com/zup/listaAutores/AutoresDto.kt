package br.com.zup.listaAutores

import br.com.zup.cadastraautores.Autor

class AutoresDto(autor: Autor) {

    val nomeAutor = autor.nome
    val emailAutor = autor.email
    val descricaoAutor = autor.descricao

}

package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;

public record CadastrarAbrigoDto(String nome,TipoPet tipoPet, String raca, Integer idade, String cor, Float peso, String telefone, String email) {

}
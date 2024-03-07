package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CalculadoraProbabilidadeAdocaoTest {
    //Teste idade 4 anos e 4kg === ALTA
    @Test
    void cenario01() {
        Abrigo abrigo = new Abrigo(
                new CadastroAbrigoDto(
                        "Abrigo Feliz",
                        "94999999999",
                        "abrigofeliz@email.com"
                )
        );
        Pet pet = new Pet(
                new CadastroPetDto(
                        TipoPet.GATO,
                        "asdfasdf",
                        "Siames",
                        4,
                        "Cizna",
                        4.0f
                ),abrigo
        );

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);
    }

    @Test
    void cenario02() {
        //Teste idade 15 anos e 4 Kg === Media
        Abrigo abrigo = new Abrigo(
                new CadastroAbrigoDto(
                        "Abrigo Feliz",
                        "94999999999",
                        "abrigofeliz@email.com"
                )
        );
        Pet pet = new Pet(
                new CadastroPetDto(
                        TipoPet.GATO,
                        "asdfasdf",
                        "Siames",
                        15,
                        "Cizna",
                        4.0f
                ),abrigo
        );

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);
    }


}
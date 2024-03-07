package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CalculadoraProbabilidadeAdocaoTest {
    @Test
    @DisplayName("Probabilidade Alta para gatos jovens e com peso baixo")
    void probabilidadeAltaCenario1() {
        //TRIPLE A -> ARRANGE -> ACT -> ASSERT
        //ARRANGE
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

        //ACT
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);
    }

    @Test
    @DisplayName("Probabiliade média para gatos idosos e com peso baixo")
    void probabilidadeMediaCenario1() {
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
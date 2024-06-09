package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validador;

    @Mock
    private AdocaoRepository repository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void naoPermitir() {
        BDDMockito.given(repository.existsByTutorIdAndStatus(
                dto.idTutor(),
                StatusAdocao.AGUARDANDO_AVALIACAO)
        ).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    void permitir() {
        BDDMockito.given(repository.existsByTutorIdAndStatus(
                dto.idTutor(),
                StatusAdocao.AGUARDANDO_AVALIACAO)
        ).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validador.validar(dto));
    }
}
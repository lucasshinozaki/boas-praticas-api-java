package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.hibernate.Remove;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @Mock
    private Pet pet;
    @Mock
    private SolicitacaoAdocaoDto dto;
    @Mock
    private PetRepository repository;
    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Test
    @DisplayName("Permitir Socilicitacao de adocao do pet sem lancar Excepetion")
    void solicitacaoCenario1() {

        BDDMockito.given(repository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));
    }

    @Test
    @DisplayName("Nao permitir Socilicitacao de adocao do pet lancar Excepetion")
    void solicitacaoCenario2() {

        BDDMockito.given(repository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto));
    }
}
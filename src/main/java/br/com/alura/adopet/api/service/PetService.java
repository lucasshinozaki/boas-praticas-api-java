package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.ListarPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    public List<ListarPetDto> buscarPetDisponiveis() {
        return repository
                .findAllByAdotadoFalse()
                .stream()
                .map(ListarPetDto::new)
                .toList();
    }

    public void cadastrarPet(Abrigo abrigo, @Valid CadastrarAbrigoDto dto) {
        repository.save(new Pet(dto, abrigo));
    }
}

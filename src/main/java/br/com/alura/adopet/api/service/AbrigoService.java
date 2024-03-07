package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.*;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;
    @Autowired
    private PetRepository petRepository;

    public void cadastrar(CadastrarAbrigoDto dto) {

    }

    public List<AbrigoDto> listar() {
        return abrigoRepository
                .findAll()
                .stream()
                .map(AbrigoDto::new)
                .toList();

    }

    public void cadastrarPet(CadastrarAbrigoDto dto) {
        boolean jaCadastrado = abrigoRepository
                .existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email());
        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo");
        }

        abrigoRepository.save(new Abrigo(dto));
    }

    public List<PetDto> listarPetsDoAbrigo(String idOuNome) {
        Abrigo abrigo = carregarAbrigo(idOuNome);

        return petRepository
                .findByAbrigo(abrigo)
                .stream()
                .map(PetDto::new)
                .toList();
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optionalAbrigo;
        try {
            Long id = Long.parseLong(idOuNome);
            optionalAbrigo = abrigoRepository.findById(idOuNome);
        }catch (NumberFormatException e) {
            optionalAbrigo = Optional.ofNullable(abrigoRepository.findByNome(idOuNome));
        }
        return optionalAbrigo.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }

}

package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.dto.AtorDTO;
import br.com.cinepulse.backend.entity.Ator;
import br.com.cinepulse.backend.entity.Filme;
import br.com.cinepulse.backend.exception.AtorException;
import br.com.cinepulse.backend.exception.FilmeException;
import br.com.cinepulse.backend.repository.AtorRepository;
import br.com.cinepulse.backend.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {
    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AtorRepository atorRepository;

    public List<AtorDTO> listarTodos() {
        return atorRepository.findAll().stream().map(this::toDTO).toList();
    }

    public AtorDTO listarPorId(Long id) {
        Ator ator = atorRepository.findById(id).orElseThrow(() -> new AtorException("Ator não encontrado."));
        return toDTO(ator);
    }

    public AtorDTO inserir(AtorDTO dto) {
        return toDTO(atorRepository.save(toEntity(dto)));
    }

    public AtorDTO atualizar(Long id, AtorDTO dto) {
        Ator ator = atorRepository.findById(id).orElseThrow(() -> new AtorException("Ator não encontrado."));
        ator.setNome(dto.getNome());
        ator.setNacionalidade(dto.getNacionalidade());
        ator.setDataNascimento(dto.getDataNascimento());
        if (dto.getIdFilme() != null) {
            Filme filme = filmeRepository.findById(dto.getIdFilme()).orElseThrow(() -> new FilmeException("Filme não encontrado."));
            ator.setFilme(filme);
        }
        return toDTO(atorRepository.save(ator));
    }

    public void deletar(Long id) {
        Ator ator = atorRepository.findById(id).orElseThrow(() -> new AtorException("Ator não encontrado."));
        atorRepository.delete(ator);
    }


    private AtorDTO toDTO(Ator ator) {
        Long id = ator.getFilme() != null ? ator.getFilme().getId() : null;
        return new AtorDTO(ator.getNome(), ator.getNacionalidade(), ator.getDataNascimento(), id);
    }

    private Ator toEntity(AtorDTO dto) {
        Ator ator = new Ator();
        ator.setNome(dto.getNome());
        ator.setNacionalidade(dto.getNacionalidade());
        ator.setDataNascimento(dto.getDataNascimento());
        if (dto.getIdFilme() != null) {
            Filme filme = filmeRepository.findById(dto.getIdFilme()).orElseThrow(() -> new FilmeException("Filme não encontrado."));
        }
        return ator;
    }
}

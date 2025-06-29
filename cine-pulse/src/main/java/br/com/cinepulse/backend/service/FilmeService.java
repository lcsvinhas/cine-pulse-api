package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.dto.AtorDTO;
import br.com.cinepulse.backend.dto.FilmeDTO;
import br.com.cinepulse.backend.dto.FilmeFotoDTO;
import br.com.cinepulse.backend.entity.Filme;
import br.com.cinepulse.backend.exception.FilmeException;
import br.com.cinepulse.backend.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository repository;

    @Autowired
    private FotoService fotoService;

    public List<FilmeDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public FilmeDTO listarPorId(Long id) {
        Filme filme = repository.findById(id).orElseThrow(() -> new FilmeException("Filme não encontrado."));
        return toDTO(filme);
    }

    public FilmeDTO inserir(FilmeDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public FilmeDTO atualizar(Long id, FilmeDTO dto) {
        Filme filme = repository.findById(id).orElseThrow(() -> new FilmeException("Filme não encontrado."));
        filme.setTitulo(dto.getTitulo());
        filme.setSinopse(dto.getSinopse());
        filme.setGenero(dto.getGenero());
        filme.setDiretor(dto.getDiretor());
        filme.setAno(dto.getAno());
        return toDTO(repository.save(filme));
    }

    public void deletar(Long id) {
        Filme filme = repository.findById(id).orElseThrow(() -> new FilmeException("Filme não encontrado."));
        repository.delete(filme);
    }

    public FilmeFotoDTO adicionarImagemUri(Filme filme) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/filmes/{id}/foto").buildAndExpand(filme.getId()).toUri();
        FilmeFotoDTO dto = new FilmeFotoDTO();

        dto.setTitulo(filme.getTitulo());
        dto.setUrl(uri.toString());

        return dto;
    }

    public FilmeFotoDTO buscar(Long id) {
        Filme filme = repository.findById(id).orElseThrow(() -> new FilmeException("Filme não encontrado."));
        return adicionarImagemUri(filme);
    }

    @Transactional
    public FilmeFotoDTO inserir(Filme filme, MultipartFile file) throws IOException {
        filme = repository.save(filme);
        fotoService.inserir(filme, file);
        return adicionarImagemUri(filme);
    }


    private FilmeDTO toDTO(Filme filme) {
        List<AtorDTO> elenco = null;
        if (filme.getElenco() != null) {
            elenco = filme.getElenco().stream().map(e -> {
                AtorDTO dto = new AtorDTO();
                dto.setNome(e.getNome());
                dto.setNacionalidade(e.getNacionalidade());
                dto.setDataNascimento(e.getDataNascimento());
                dto.setIdFilme(filme.getId());
                return dto;
            }).toList();
        }
        return new FilmeDTO(filme.getId(), filme.getTitulo(), filme.getSinopse(), filme.getGenero(), filme.getDiretor(), filme.getAno(), elenco);
    }

    private Filme toEntity(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setId(filmeDTO.getId());
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setSinopse(filmeDTO.getSinopse());
        filme.setGenero(filmeDTO.getGenero());
        filme.setDiretor(filmeDTO.getDiretor());
        filme.setAno(filmeDTO.getAno());
        return filme;
    }
}

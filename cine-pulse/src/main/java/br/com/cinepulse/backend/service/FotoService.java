package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.entity.Filme;
import br.com.cinepulse.backend.entity.Foto;
import br.com.cinepulse.backend.exception.FotoException;
import br.com.cinepulse.backend.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FotoService {
    @Autowired
    private FotoRepository repository;

    public Foto inserir(Filme filme, MultipartFile file) throws IOException {
        return repository.save(new Foto(null, file.getBytes(), file.getContentType(), file.getName(), filme));
    }

    public Foto buscarPorIdFilme(Long id) {
        Filme filme = new Filme();
        filme.setId(id);
        return repository.findByFilme(filme).orElseThrow(() -> new FotoException("Filme não encontrado."));
    }
}

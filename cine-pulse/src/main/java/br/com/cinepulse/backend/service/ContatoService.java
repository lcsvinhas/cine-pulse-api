package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.entity.Contato;
import br.com.cinepulse.backend.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository repository;

    public Contato inserir(Contato contato) {
        return repository.save(contato);
    }
}

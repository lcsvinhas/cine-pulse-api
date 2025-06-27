package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.entity.Perfil;
import br.com.cinepulse.backend.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository repository;

    public Perfil buscar(Long id) {
        Optional<Perfil> perfil = repository.findById(id);
        return perfil.get();
    }
}

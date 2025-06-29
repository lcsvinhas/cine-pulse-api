package br.com.cinepulse.backend.repository;

import br.com.cinepulse.backend.entity.Filme;
import br.com.cinepulse.backend.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    Optional<Foto> findByFilme(Filme filme);
}

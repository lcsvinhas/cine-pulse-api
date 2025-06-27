package br.com.cinepulse.backend.repository;

import br.com.cinepulse.backend.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}

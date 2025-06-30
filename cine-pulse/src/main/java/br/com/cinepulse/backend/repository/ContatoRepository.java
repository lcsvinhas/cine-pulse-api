package br.com.cinepulse.backend.repository;

import br.com.cinepulse.backend.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}

package br.com.cinepulse.backend.repository;

import br.com.cinepulse.backend.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}

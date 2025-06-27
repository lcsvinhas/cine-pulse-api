package br.com.cinepulse.backend.repository;

import br.com.cinepulse.backend.entity.UsuarioPerfil;
import br.com.cinepulse.backend.entity.pk.UsuarioPerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {
}

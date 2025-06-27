package br.com.cinepulse.backend.entity;

import br.com.cinepulse.backend.entity.pk.UsuarioPerfilPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UsuarioPerfil {

    @EmbeddedId
    private UsuarioPerfilPK id = new UsuarioPerfilPK();

    private LocalDate dataCriacao;

    public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao) {
        id.setUsuario(usuario);
        id.setPerfil(perfil);
        this.dataCriacao = dataCriacao;
    }

    public void setUsuario(Usuario usuario) {
        id.setUsuario(usuario);
    }

    public Perfil getPerfil() {
        return id.getPerfil();
    }

    public void setPerfil(Perfil perfil) {
        id.setPerfil(perfil);
    }
}

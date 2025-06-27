package br.com.cinepulse.backend.entity.pk;

import br.com.cinepulse.backend.entity.Perfil;
import br.com.cinepulse.backend.entity.Usuario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UsuarioPerfilPK {

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPerfilPK that = (UsuarioPerfilPK) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(perfil, that.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, perfil);
    }
}

package br.com.cinepulse.backend.dto;

import br.com.cinepulse.backend.entity.Usuario;
import br.com.cinepulse.backend.entity.UsuarioPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    private String senha;

    private Set<UsuarioPerfil> perfis = new HashSet<>();

    public UsuarioRequestDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}

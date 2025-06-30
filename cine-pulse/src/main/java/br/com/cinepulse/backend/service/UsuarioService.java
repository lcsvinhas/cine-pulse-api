package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.dto.UsuarioRequestDTO;
import br.com.cinepulse.backend.dto.UsuarioResponseDTO;
import br.com.cinepulse.backend.entity.Usuario;
import br.com.cinepulse.backend.entity.UsuarioPerfil;
import br.com.cinepulse.backend.exception.UsuarioException;
import br.com.cinepulse.backend.repository.PerfilRepository;
import br.com.cinepulse.backend.repository.UsuarioPerfilRepository;
import br.com.cinepulse.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> listar() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosDTO.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
        }
        return usuariosDTO;
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UsuarioException("Usuário não encontrado"));
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO usuario) {
        Optional<Usuario> u = repository.findByEmail(usuario.getEmail());

        if (u.isPresent()) {
            throw new UsuarioException("Email já cadastrado.");
        }
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioEntity.setSenha(usuario.getSenha());

        for (UsuarioPerfil up : usuario.getPerfis()) {
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
            up.setUsuario(usuarioEntity);
            up.setDataCriacao(LocalDate.now());
        }

        usuarioEntity = repository.save(usuarioEntity);
        usuarioPerfilRepository.saveAll(usuario.getPerfis());

        return new UsuarioResponseDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail());
    }
}

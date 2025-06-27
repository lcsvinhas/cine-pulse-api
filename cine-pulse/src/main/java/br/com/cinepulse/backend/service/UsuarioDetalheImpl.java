package br.com.cinepulse.backend.service;

import br.com.cinepulse.backend.entity.Usuario;
import br.com.cinepulse.backend.exception.UsuarioException;
import br.com.cinepulse.backend.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioDetalheImpl implements UserDetailsService {

    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(username).orElseThrow(() -> new UsuarioException("Email não encontrado."));
        return usuario;
    }
}

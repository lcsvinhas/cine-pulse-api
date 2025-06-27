package br.com.cinepulse.backend.controller;

import br.com.cinepulse.backend.dto.FilmeDTO;
import br.com.cinepulse.backend.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeService service;

    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FilmeDTO> inserir(@Valid @RequestBody FilmeDTO filmeDTO) {
        return ResponseEntity.ok(service.inserir(filmeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FilmeDTO filmeDTO) {
        return ResponseEntity.ok(service.atualizar(id, filmeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

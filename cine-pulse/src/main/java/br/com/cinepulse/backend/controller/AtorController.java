package br.com.cinepulse.backend.controller;

import br.com.cinepulse.backend.dto.AtorDTO;
import br.com.cinepulse.backend.service.AtorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {
    @Autowired
    private AtorService service;

    @GetMapping
    public ResponseEntity<List<AtorDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AtorDTO> inserir(@Valid @RequestBody AtorDTO AtorDTO) {
        return ResponseEntity.ok(service.inserir(AtorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtorDTO> atualizar(@Valid @RequestBody AtorDTO AtorDTO, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizar(id, AtorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

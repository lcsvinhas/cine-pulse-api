package br.com.cinepulse.backend.controller;

import br.com.cinepulse.backend.dto.FilmeDTO;
import br.com.cinepulse.backend.dto.FilmeFotoDTO;
import br.com.cinepulse.backend.entity.Filme;
import br.com.cinepulse.backend.entity.Foto;
import br.com.cinepulse.backend.service.FilmeService;
import br.com.cinepulse.backend.service.FotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeService service;

    @Autowired
    private FotoService fotoService;

    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) throws IOException {
        Foto foto = fotoService.buscarPorIdFilme(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", foto.getTipo());
        headers.add("Content-Length", String.valueOf(foto.getDados().length));
        return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FilmeDTO> inserir(@Valid @RequestBody FilmeDTO filmeDTO) {
        return ResponseEntity.ok(service.inserir(filmeDTO));
    }

    @PostMapping("/foto")
    public ResponseEntity<FilmeFotoDTO> inserir(@RequestPart MultipartFile file, @RequestPart Filme filme) throws IOException {
        return ResponseEntity.ok(service.inserir(filme, file));
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

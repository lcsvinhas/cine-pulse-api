package br.com.cinepulse.backend.controller;

import br.com.cinepulse.backend.entity.Contato;
import br.com.cinepulse.backend.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contatos")
public class ContatoController {
    @Autowired
    private ContatoService service;

    @PostMapping
    public ResponseEntity<Contato> inserir(@RequestBody Contato contato) {
        return ResponseEntity.ok(service.inserir(contato));
    }
}

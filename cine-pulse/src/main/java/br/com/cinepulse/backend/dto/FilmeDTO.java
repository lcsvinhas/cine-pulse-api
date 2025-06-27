package br.com.cinepulse.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {
    @NotBlank(message = "campo 'titulo' não está preenchido.")
    private String titulo;

    @NotBlank(message = "campo 'sinopse' não está preenchido.")
    private String sinopse;

    @NotBlank(message = "campo 'genero' não está preenchido.")
    private String genero;

    @NotBlank(message = "campo 'diretor' não está preenchido.")
    private String diretor;

    @NotNull(message = "campo 'sinopse' não está preenchido.")
    private Integer ano;

    private List<AtorDTO> elenco;
}

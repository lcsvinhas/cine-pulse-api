package br.com.cinepulse.backend.dto;

import br.com.cinepulse.backend.entity.Filme;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtorDTO {
    @NotBlank(message = "campo 'nome' não está preenchido.")
    private String nome;

    @NotBlank(message = "campo 'nacionalidade' não está preenchido.")
    private String nacionalidade;

    @NotNull(message = "campo 'dataNascimento' não está preenchido.")
    private LocalDate dataNascimento;

    private Long idFilme;
}

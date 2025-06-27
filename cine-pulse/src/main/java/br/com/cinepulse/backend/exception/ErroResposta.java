package br.com.cinepulse.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErroResposta {
    private Integer status;
    private String tituloResposta;
    private LocalDateTime dataHora;
    private List<String> erros;
}

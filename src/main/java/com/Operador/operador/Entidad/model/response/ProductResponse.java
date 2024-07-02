package com.Operador.operador.Entidad.model.response;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Integer id;
    private String title;
    private String backdropPath;
    private String posterPath;
    private BigDecimal budget;
    private String originalLanguage;
    private String originalTitle;
    private BigDecimal popularity;
    private String releaseDate;
    private BigDecimal revenue;
    private Integer runtime;
    private String tagline;
    private BigDecimal voteAverage;
    private BigDecimal voteCount;
    private String overview;
}

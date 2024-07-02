package com.Operador.operador.Entidad.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaccionResponse {
    private int statusCode;
    private String message;
    private Object data;
}

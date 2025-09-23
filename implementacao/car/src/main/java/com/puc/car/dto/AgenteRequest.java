package com.puc.car.dto;

import com.puc.car.models.enums.TipoAgente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AgenteRequest extends UsuarioRequest {
    
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    
    @NotNull(message = "Tipo de agente é obrigatório")
    private TipoAgente tipoAgente;
}
package com.puc.car.models;

import com.puc.car.models.enums.EstadoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @NotNull(message = "Estado do pedido é obrigatório")
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido = EstadoPedido.ANDAMENTO;
    
    @NotNull(message = "Automóvel é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automovel_id")
    private Automovel automovel;
    
    @NotNull(message = "Valor total é obrigatório")
    @Positive(message = "Valor total deve ser positivo")
    private Double valorTotal;
}
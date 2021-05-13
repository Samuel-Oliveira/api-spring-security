package br.com.swconsultoria.apispringsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "venda_pagamento")
public class VendaPagamento {

    @Id
    @SequenceGenerator(name = "venda_pagamento_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_pagamento_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_pagamento")
    private TipoPagamento pagamento;

    @Column(name = "valor")
    private BigDecimal valor;

}

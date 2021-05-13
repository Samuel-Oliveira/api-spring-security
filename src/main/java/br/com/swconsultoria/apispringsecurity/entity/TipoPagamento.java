package br.com.swconsultoria.apispringsecurity.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipo_pagamento")
public class TipoPagamento {

    @Id
    @SequenceGenerator(name = "tipo_pagamento_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_pagamento_sequence")
    private Long id;

    @Column(name = "nome")
    private String nome;

}

package br.com.swconsultoria.apispringsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @SequenceGenerator(name = "caixa_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caixa_sequence")
    private Long id;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura = LocalDate.now();

    @Column(name = "valor_abertura")
    private BigDecimal valorAbertura = BigDecimal.ZERO;

    @Column(name = "valor_fechamento")
    private BigDecimal valorFechamento = BigDecimal.ZERO;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Column(name = "estado")
    private boolean estado = false;
}

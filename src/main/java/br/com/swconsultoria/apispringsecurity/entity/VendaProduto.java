package br.com.swconsultoria.apispringsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "venda_produto")
public class VendaProduto {

    @Id
    @SequenceGenerator(name = "venda_produto_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_produto_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "quantidade")
    private BigDecimal quantidade;

    @Column(name = "total")
    private BigDecimal total;

}

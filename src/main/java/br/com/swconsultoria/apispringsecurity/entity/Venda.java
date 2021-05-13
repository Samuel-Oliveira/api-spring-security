package br.com.swconsultoria.apispringsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @SequenceGenerator(name = "venda_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_sequence")
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_caixa")
    private Caixa caixa;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private List<VendaPagamento> pagamentos = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private List<VendaProduto> produtos = new LinkedList<>();
}

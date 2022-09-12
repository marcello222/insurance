package br.com.insurance.challenge.insurance.entity;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@Table(name = "PARCELA")
public class Parcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PREMIO")
    private BigDecimal premio;

    @Column(name = "FORMA_PAGAMENTO")
    private String formaPagamento;

    @Column(name = "DATA_PAGAMENTO")
    private Date dataPagamento;

    @Column(name = "DATA_PAGO")
    private Date dataPago;

    @Column(name = "JUROS")
    private BigDecimal juros;

    @Column(name = "SITUACAO")
    private String situacao;

    @Column(name = "DATA_CRIACAO_REGISTRO")
    private Date dataCriacaoRegistro;

    @Column(name = "DATA_ALTERACAO_REGISTRO")
    private Date dataAlteracaoRegistro;

    @Column(name = "USUARIO_ALTERACAO_REGISTRO")
    private Integer usuarioAlteracaoRegistro;

    @Column(name = "USUARIO_CRIACAO_REGISTRO")
    private Integer usuarioCriacaoRegistro;
}



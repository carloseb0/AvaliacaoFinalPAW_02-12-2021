/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "compraproduto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compraproduto.findAll", query = "SELECT c FROM Compraproduto c"),
    @NamedQuery(name = "Compraproduto.findByCmpCodigo", query = "SELECT c FROM Compraproduto c WHERE c.cmpCodigo = :cmpCodigo"),
    @NamedQuery(name = "Compraproduto.findByCprCodigo", query = "SELECT c FROM Compraproduto c WHERE c.cprCodigo = :cprCodigo"),
    @NamedQuery(name = "Compraproduto.findByCprValorunitario", query = "SELECT c FROM Compraproduto c WHERE c.cprValorunitario = :cprValorunitario"),
    @NamedQuery(name = "Compraproduto.findByCprQuantidade", query = "SELECT c FROM Compraproduto c WHERE c.cprQuantidade = :cprQuantidade"),
    @NamedQuery(name = "Compraproduto.findByCprValortotal", query = "SELECT c FROM Compraproduto c WHERE c.cprValortotal = :cprValortotal")})
public class Compraproduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cpr_codigo")
    private Integer cprCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cpr_valorunitario")
    private BigDecimal cprValorunitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cpr_quantidade")
    private int cprQuantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cpr_valortotal")
    private BigDecimal cprValortotal;
    @JoinColumn(name = "cmp_codigo", referencedColumnName = "cmp_codigo")
    @ManyToOne(optional = false)
    private Compra cmpCodigo;
    @JoinColumn(name = "pec_codigo", referencedColumnName = "pec_codigo")
    @ManyToOne(optional = false)
    private Pecas pecCodigo;

    public Compraproduto() {
    }

    public Compraproduto(Integer cprCodigo) {
        this.cprCodigo = cprCodigo;
    }

    public Compraproduto(Integer cprCodigo, BigDecimal cprValorunitario, int cprQuantidade, BigDecimal cprValortotal) {
        this.cprCodigo = cprCodigo;
        this.cprValorunitario = cprValorunitario;
        this.cprQuantidade = cprQuantidade;
        this.cprValortotal = cprValortotal;
    }

    public Integer getCprCodigo() {
        return cprCodigo;
    }

    public void setCprCodigo(Integer cprCodigo) {
        this.cprCodigo = cprCodigo;
    }

    public BigDecimal getCprValorunitario() {
        return cprValorunitario;
    }

    public void setCprValorunitario(BigDecimal cprValorunitario) {
        this.cprValorunitario = cprValorunitario;
    }

    public int getCprQuantidade() {
        return cprQuantidade;
    }

    public void setCprQuantidade(int cprQuantidade) {
        this.cprQuantidade = cprQuantidade;
    }

    public BigDecimal getCprValortotal() {
        return cprValortotal;
    }

    public void setCprValortotal(BigDecimal cprValortotal) {
        this.cprValortotal = cprValortotal;
    }

    public Compra getCmpCodigo() {
        return cmpCodigo;
    }

    public void setCmpCodigo(Compra cmpCodigo) {
        this.cmpCodigo = cmpCodigo;
    }

    public Pecas getPecCodigo() {
        return pecCodigo;
    }

    public void setPecCodigo(Pecas pecCodigo) {
        this.pecCodigo = pecCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cprCodigo != null ? cprCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compraproduto)) {
            return false;
        }
        Compraproduto other = (Compraproduto) object;
        if ((this.cprCodigo == null && other.cprCodigo != null) || (this.cprCodigo != null && !this.cprCodigo.equals(other.cprCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cprCodigo.toString();
    }
    
}

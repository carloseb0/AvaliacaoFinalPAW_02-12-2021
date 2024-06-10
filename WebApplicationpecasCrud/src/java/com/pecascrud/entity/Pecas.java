/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "pecas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pecas.findAll", query = "SELECT p FROM Pecas p"),
    @NamedQuery(name = "Pecas.findByPecCodigo", query = "SELECT p FROM Pecas p WHERE p.pecCodigo = :pecCodigo"),
    @NamedQuery(name = "Pecas.findByPecNome", query = "SELECT p FROM Pecas p WHERE p.pecNome = :pecNome"),
    @NamedQuery(name = "Pecas.findByPecValorunitario", query = "SELECT p FROM Pecas p WHERE p.pecValorunitario = :pecValorunitario"),
    @NamedQuery(name = "Pecas.findByPecAtivo", query = "SELECT p FROM Pecas p WHERE p.pecAtivo = :pecAtivo")})
public class Pecas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pec_codigo")
    private Integer pecCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pec_nome")
    private String pecNome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "pec_valorunitario")
    private BigDecimal pecValorunitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pec_ativo")
    private boolean pecAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pecCodigo")
    private Collection<Compraproduto> compraprodutoCollection;
    @JoinColumn(name = "cat_codigo", referencedColumnName = "cat_codigo")
    @ManyToOne(optional = false)
    private Categoria catCodigo;
    @JoinColumn(name = "for_codigo", referencedColumnName = "for_codigo")
    @ManyToOne(optional = false)
    private Fornecedor forCodigo;
    @JoinColumn(name = "usr_codigo", referencedColumnName = "usr_codigo")
    @ManyToOne(optional = false)
    private Usuario usrCodigo;

    public Pecas() {
    }

    public Pecas(Integer pecCodigo) {
        this.pecCodigo = pecCodigo;
    }

    public Pecas(Integer pecCodigo, String pecNome, BigDecimal pecValorunitario, boolean pecAtivo) {
        this.pecCodigo = pecCodigo;
        this.pecNome = pecNome;
        this.pecValorunitario = pecValorunitario;
        this.pecAtivo = pecAtivo;
    }

    public Integer getPecCodigo() {
        return pecCodigo;
    }

    public void setPecCodigo(Integer pecCodigo) {
        this.pecCodigo = pecCodigo;
    }

    public String getPecNome() {
        return pecNome;
    }

    public void setPecNome(String pecNome) {
        this.pecNome = pecNome;
    }

    public BigDecimal getPecValorunitario() {
        return pecValorunitario;
    }

    public void setPecValorunitario(BigDecimal pecValorunitario) {
        this.pecValorunitario = pecValorunitario;
    }

    public boolean getPecAtivo() {
        return pecAtivo;
    }

    public void setPecAtivo(boolean pecAtivo) {
        this.pecAtivo = pecAtivo;
    }

    @XmlTransient
    public Collection<Compraproduto> getCompraprodutoCollection() {
        return compraprodutoCollection;
    }

    public void setCompraprodutoCollection(Collection<Compraproduto> compraprodutoCollection) {
        this.compraprodutoCollection = compraprodutoCollection;
    }

    public Categoria getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(Categoria catCodigo) {
        this.catCodigo = catCodigo;
    }

    public Fornecedor getForCodigo() {
        return forCodigo;
    }

    public void setForCodigo(Fornecedor forCodigo) {
        this.forCodigo = forCodigo;
    }

    public Usuario getUsrCodigo() {
        return usrCodigo;
    }

    public void setUsrCodigo(Usuario usrCodigo) {
        this.usrCodigo = usrCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pecCodigo != null ? pecCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pecas)) {
            return false;
        }
        Pecas other = (Pecas) object;
        if ((this.pecCodigo == null && other.pecCodigo != null) || (this.pecCodigo != null && !this.pecCodigo.equals(other.pecCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pecNome;
    }
    
}

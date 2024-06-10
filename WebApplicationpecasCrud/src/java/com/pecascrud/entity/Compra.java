/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByCmpCodigo", query = "SELECT c FROM Compra c WHERE c.cmpCodigo = :cmpCodigo"),
    @NamedQuery(name = "Compra.findByCmpDatahora", query = "SELECT c FROM Compra c WHERE c.cmpDatahora = :cmpDatahora"),
    @NamedQuery(name = "Compra.findByCmpValortotalcompra", query = "SELECT c FROM Compra c WHERE c.cmpValortotalcompra = :cmpValortotalcompra")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cmp_codigo")
    private Integer cmpCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cmp_datahora")
    @Temporal(TemporalType.DATE)
    private Date cmpDatahora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cmp_valortotalcompra")
    private BigDecimal cmpValortotalcompra;
    @JoinColumn(name = "usr_codigo", referencedColumnName = "usr_codigo")
    @ManyToOne(optional = false)
    private Usuario usrCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmpCodigo")
    private Collection<Compraproduto> compraprodutoCollection;

    public Compra() {
    }

    public Compra(Integer cmpCodigo) {
        this.cmpCodigo = cmpCodigo;
    }

    public Compra(Integer cmpCodigo, Date cmpDatahora) {
        this.cmpCodigo = cmpCodigo;
        this.cmpDatahora = cmpDatahora;
    }

    public Integer getCmpCodigo() {
        return cmpCodigo;
    }

    public void setCmpCodigo(Integer cmpCodigo) {
        this.cmpCodigo = cmpCodigo;
    }

    public Date getCmpDatahora() {
        return cmpDatahora;
    }

    public void setCmpDatahora(Date cmpDatahora) {
        this.cmpDatahora = cmpDatahora;
    }

    public BigDecimal getCmpValortotalcompra() {
        return cmpValortotalcompra;
    }

    public void setCmpValortotalcompra(BigDecimal cmpValortotalcompra) {
        this.cmpValortotalcompra = cmpValortotalcompra;
    }

    public Usuario getUsrCodigo() {
        return usrCodigo;
    }

    public void setUsrCodigo(Usuario usrCodigo) {
        this.usrCodigo = usrCodigo;
    }

    @XmlTransient
    public Collection<Compraproduto> getCompraprodutoCollection() {
        return compraprodutoCollection;
    }

    public void setCompraprodutoCollection(Collection<Compraproduto> compraprodutoCollection) {
        this.compraprodutoCollection = compraprodutoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmpCodigo != null ? cmpCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.cmpCodigo == null && other.cmpCodigo != null) || (this.cmpCodigo != null && !this.cmpCodigo.equals(other.cmpCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cmpCodigo.toString();
    }
    
}

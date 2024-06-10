/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByForCodigo", query = "SELECT f FROM Fornecedor f WHERE f.forCodigo = :forCodigo"),
    @NamedQuery(name = "Fornecedor.findByForNome", query = "SELECT f FROM Fornecedor f WHERE f.forNome = :forNome"),
    @NamedQuery(name = "Fornecedor.findByForCnpj", query = "SELECT f FROM Fornecedor f WHERE f.forCnpj = :forCnpj"),
    @NamedQuery(name = "Fornecedor.findByForCidade", query = "SELECT f FROM Fornecedor f WHERE f.forCidade = :forCidade")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "for_codigo")
    private Integer forCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "for_nome")
    private String forNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "for_cnpj")
    private String forCnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "for_cidade")
    private String forCidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forCodigo")
    private Collection<Pecas> pecasCollection;

    public Fornecedor() {
    }

    public Fornecedor(Integer forCodigo) {
        this.forCodigo = forCodigo;
    }

    public Fornecedor(Integer forCodigo, String forNome, String forCnpj, String forCidade) {
        this.forCodigo = forCodigo;
        this.forNome = forNome;
        this.forCnpj = forCnpj;
        this.forCidade = forCidade;
    }

    public Integer getForCodigo() {
        return forCodigo;
    }

    public void setForCodigo(Integer forCodigo) {
        this.forCodigo = forCodigo;
    }

    public String getForNome() {
        return forNome;
    }

    public void setForNome(String forNome) {
        this.forNome = forNome;
    }

    public String getForCnpj() {
        return forCnpj;
    }

    public void setForCnpj(String forCnpj) {
        this.forCnpj = forCnpj;
    }

    public String getForCidade() {
        return forCidade;
    }

    public void setForCidade(String forCidade) {
        this.forCidade = forCidade;
    }

    @XmlTransient
    public Collection<Pecas> getPecasCollection() {
        return pecasCollection;
    }

    public void setPecasCollection(Collection<Pecas> pecasCollection) {
        this.pecasCollection = pecasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (forCodigo != null ? forCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.forCodigo == null && other.forCodigo != null) || (this.forCodigo != null && !this.forCodigo.equals(other.forCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return forNome;
    }
    
}

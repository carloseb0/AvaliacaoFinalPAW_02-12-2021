/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsrCodigo", query = "SELECT u FROM Usuario u WHERE u.usrCodigo = :usrCodigo"),
    @NamedQuery(name = "Usuario.findByUsrDatacadastro", query = "SELECT u FROM Usuario u WHERE u.usrDatacadastro = :usrDatacadastro"),
    @NamedQuery(name = "Usuario.findByUsrNome", query = "SELECT u FROM Usuario u WHERE u.usrNome = :usrNome"),
    @NamedQuery(name = "Usuario.findByUsrLogin", query = "SELECT u FROM Usuario u WHERE u.usrLogin = :usrLogin"),
    @NamedQuery(name = "Usuario.findByUsrSenha", query = "SELECT u FROM Usuario u WHERE u.usrSenha = :usrSenha"),
    @NamedQuery(name = "Usuario.findByUsrBloqueado", query = "SELECT u FROM Usuario u WHERE u.usrBloqueado = :usrBloqueado"),
    @NamedQuery(name = "Usuario.findByUsrEmail", query = "SELECT u FROM Usuario u WHERE u.usrEmail = :usrEmail")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_codigo")
    private Integer usrCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_datacadastro")
    @Temporal(TemporalType.DATE)
    private Date usrDatacadastro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usr_nome")
    private String usrNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usr_login")
    private String usrLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usr_senha")
    private String usrSenha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_bloqueado")
    private boolean usrBloqueado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usr_email")
    private String usrEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrCodigo")
    private Collection<Compra> compraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrCodigo")
    private Collection<Pecas> pecasCollection;

    public Usuario() {
    }

    public Usuario(Integer usrCodigo) {
        this.usrCodigo = usrCodigo;
    }

    public Usuario(Integer usrCodigo, Date usrDatacadastro, String usrNome, String usrLogin, String usrSenha, boolean usrBloqueado, String usrEmail) {
        this.usrCodigo = usrCodigo;
        this.usrDatacadastro = usrDatacadastro;
        this.usrNome = usrNome;
        this.usrLogin = usrLogin;
        this.usrSenha = usrSenha;
        this.usrBloqueado = usrBloqueado;
        this.usrEmail = usrEmail;
    }

    public Integer getUsrCodigo() {
        return usrCodigo;
    }

    public void setUsrCodigo(Integer usrCodigo) {
        this.usrCodigo = usrCodigo;
    }

    public Date getUsrDatacadastro() {
        return usrDatacadastro;
    }

    public void setUsrDatacadastro(Date usrDatacadastro) {
        this.usrDatacadastro = usrDatacadastro;
    }

    public String getUsrNome() {
        return usrNome;
    }

    public void setUsrNome(String usrNome) {
        this.usrNome = usrNome;
    }

    public String getUsrLogin() {
        return usrLogin;
    }

    public void setUsrLogin(String usrLogin) {
        this.usrLogin = usrLogin;
    }

    public String getUsrSenha() {
        return usrSenha;
    }

    public void setUsrSenha(String usrSenha) {
        this.usrSenha = usrSenha;
    }

    public boolean getUsrBloqueado() {
        return usrBloqueado;
    }

    public void setUsrBloqueado(boolean usrBloqueado) {
        this.usrBloqueado = usrBloqueado;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
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
        hash += (usrCodigo != null ? usrCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usrCodigo == null && other.usrCodigo != null) || (this.usrCodigo != null && !this.usrCodigo.equals(other.usrCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usrNome;
    }
    
}

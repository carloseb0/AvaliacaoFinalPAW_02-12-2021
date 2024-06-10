/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.util;

import com.pecascrud.entity.Usuario;
import javax.faces.context.FacesContext;

/**
 *
 * @author jeang
 */
public class UtilSession {

    //sessão
    //captura a sessão do contexto criado pelo JavaServer Faces
    private final FacesContext context = FacesContext.getCurrentInstance();
    private final javax.servlet.http.HttpSession session
            = (javax.servlet.http.HttpSession) context.getExternalContext().getSession(false);

    /**
     * método que retorna o código do usuário logado
     *
     * @return
     */
    public Integer getUsuarioCodigoLogado() {
        return (Integer) session.getAttribute("logado");
    }

    /**
     * método que monta um objeto usuario e insere o código do usuario logado.
     *
     * @return
     */
    public Usuario getUsuarioLogado() {
        Usuario user = new Usuario();
        user.setUsrCodigo(getUsuarioCodigoLogado());
        return user;
    }

}

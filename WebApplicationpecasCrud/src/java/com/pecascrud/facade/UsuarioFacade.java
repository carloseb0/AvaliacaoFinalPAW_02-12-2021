/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.facade;

import com.pecascrud.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "WebApplicationPecasCrudPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
        
       /**
     * Consulta que retorna um usuario
     *
     * @param usrLogin
     * @return usuario
     */
    public Usuario findUsuarioByLogin(String usrLogin) {
        Usuario usuario = null;
        try {
            Query query = getEntityManager().createNamedQuery("Usuario.findByUsrLogin");
            query.setParameter("usrLogin", usrLogin);
            //define a quantidade 1 como resultado de registros..
            query.setMaxResults(1);

            if (!query.getResultList().isEmpty()) {
                usuario = (Usuario) query.getSingleResult();
            } else {
                System.out.println("Nenhum resultado localizado para findByUsrLogin.");
            }
        } catch (Exception e) {
            System.out.println("Erros: " + e);
        }
        return usuario;
    }
    
}

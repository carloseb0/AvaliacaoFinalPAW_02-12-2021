/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.facade;

import com.pecascrud.entity.Pecas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carlos
 */
@Stateless
public class PecasFacade extends AbstractFacade<Pecas> {

    @PersistenceContext(unitName = "WebApplicationPecasCrudPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PecasFacade() {
        super(Pecas.class);
    }
    
}

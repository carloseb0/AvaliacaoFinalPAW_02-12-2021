/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.facade;

import com.pecascrud.entity.Compra;
import com.pecascrud.entity.Compraproduto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
public class CompraprodutoFacade extends AbstractFacade<Compraproduto> {

    @PersistenceContext(unitName = "WebApplicationPecasCrudPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraprodutoFacade() {
        super(Compraproduto.class);
    }
    
        /**
     * Método utilizado para buscar uma lista de compraProduto por produto
     *
     * @param compra
     * @return
     */
    public List<Compraproduto> findByCompraproduto(Compra compra) {
        List<Compraproduto> lista = null;
        try {
            Query query = getEntityManager().createNamedQuery("Compraproduto.findByCmpCodigo");
            query.setParameter("cmpCodigo", compra);
            lista = (List<Compraproduto>) query.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return lista;
    }
    
}

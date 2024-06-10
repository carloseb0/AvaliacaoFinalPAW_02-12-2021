package com.pecascrud.controller;

import com.pecascrud.entity.Compra;
import com.pecascrud.controller.util.JsfUtil;
import com.pecascrud.controller.util.JsfUtil.PersistAction;
import com.pecascrud.facade.CompraFacade;
import com.pecascrud.util.Util;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("compraController")
@SessionScoped
public class CompraController implements Serializable {

    @EJB
    private com.pecascrud.facade.CompraFacade ejbFacade;
    private List<Compra> items = null;
    private Compra selected;
    private final Util util = new Util();
    private Integer compraCodigo;
    private String usuarioNome;

    public CompraController() {
    }

    public Compra getSelected() {
        return selected;
    }

    public void setSelected(Compra selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CompraFacade getFacade() {
        return ejbFacade;
    }

    public Compra prepareCreate() {
        selected = new Compra();
        initializeEmbeddableKey();
        return selected;
    }

    public Compra prepareCreateGeral() {
        selected = new Compra();
        initializeEmbeddableKey();
        //ressetando código das variáveis
        setCompraCodigo(null);
        setUsuarioNome(null);
        return selected;
    }

    public void create() {
        selected.setCmpDatahora(util.buscarDataHoraAtual());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CompraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            //passando o código para nova variável...
            setCompraCodigo(selected.getCmpCodigo());
            setUsuarioNome(selected.getUsrCodigo().getUsrNome());
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CompraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CompraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Compra> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (null != persistAction) {
                    switch (persistAction) {
                        case CREATE:
                            getFacade().create(selected);
                            break;
                        case UPDATE:
                            getFacade().edit(selected);
                            break;
                        case DELETE:
                            getFacade().remove(selected);
                            break;
                        default:
                            break;
                    }
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Compra getCompra(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Compra> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Compra> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Integer getCompraCodigo() {
        return compraCodigo;
    }

    public void setCompraCodigo(Integer compraCodigo) {
        this.compraCodigo = compraCodigo;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    @FacesConverter(forClass = Compra.class)
    public static class CompraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CompraController controller = (CompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "compraController");
            return controller.getCompra(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Compra) {
                Compra o = (Compra) object;
                return getStringKey(o.getCmpCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Compra.class.getName()});
                return null;
            }
        }

    }
}

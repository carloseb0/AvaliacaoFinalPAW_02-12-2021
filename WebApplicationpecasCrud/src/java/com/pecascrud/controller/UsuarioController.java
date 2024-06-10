package com.pecascrud.controller;

import com.pecascrud.entity.Usuario;
import com.pecascrud.controller.util.JsfUtil;
import com.pecascrud.controller.util.JsfUtil.PersistAction;
import com.pecascrud.facade.UsuarioFacade;
import com.pecascrud.util.Util;
import com.pecascrud.util.UtilSession;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private com.pecascrud.facade.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;
    private final Util util = new Util();
    private final UtilSession utilSession = new UtilSession();
    
      //captura a sessão do contexto criado pelo JavaServerFaces
    private final FacesContext context = FacesContext.getCurrentInstance();
    private final HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

    /**
     * Método utilizado para inicializar métodos ao instanciar a classe...
     */
    @PostConstruct
    public void init() {
        prepareCreate();
    }

    /**
     * Método utilizado para validar usuario e senha no login de acesso
     *
     * @return String
     */
    public String autenticarUsuario() {
        //busca o usuario na base de dados...        
        Usuario usuario = ejbFacade.findUsuarioByLogin(selected.getUsrLogin());
        //se encontrou usuario...
        if (usuario != null) {
            //se a senha existe...
            if (usuario.getUsrSenha() != null) {
                //se a senha informada é igual a senha cadastrada...
                if (usuario.getUsrSenha().equals(selected.getUsrSenha())) {
                    //adicionando na sessão o atributo logado.
                    session.setAttribute("logado", usuario.getUsrCodigo());
                    return "/admin/operacional/Compra-Produto.xhtml";
                } else {
                    JsfUtil.addErrorMessage("Usuário ou senha incorreta!");
                    //não muda de página...
                    return null;
                }
            } else {
                JsfUtil.addErrorMessage(usuario.getUsrLogin() + ": senha não cadastrada.");
                return null;
            }
        } else {
            JsfUtil.addErrorMessage("Usuario não cadastrado.");
            return null;
        }
    }

    public String logout() {
        session.invalidate();
        return "/login";
    }

    public UsuarioController() {
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setUsrDatacadastro(util.buscarDataHoraAtual());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getUsrCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}

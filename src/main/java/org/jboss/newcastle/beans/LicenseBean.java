package org.jboss.newcastle.beans;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.newcastle.model.License;

/**
 * Provides basic CRUD functionality for software licenses.
 *
 */
@Named
@Stateful
@ConversationScoped
public class LicenseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
 
    public void setId(String id) {
        this.id = new Integer(id);
    }

    private License license;
    
    public License getLicense() {
        return this.license;
    }
    
    private License search = new License();

    @Inject
    private Conversation conversation;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public String create() {
        this.conversation.begin();
        return "edit?faces-redirect=true";
    }

    public void retrieve() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }
        
        if (this.conversation.isTransient()) {
            this.conversation.begin();
        }
        
        if (this.id == null) {
            this.license = this.search;
        } else {
            this.license = this.entityManager.find(License.class, getId());
        }
    }

    public String update() {
        if (!this.conversation.isTransient()) {
            this.conversation.end();
        }
        
        try {
            if (this.id == null) {
                this.entityManager.persist(this.license);
                return "view?faces-redirect=true";
            } else {
                this.entityManager.merge(this.license);
                return "view?faces-redirect=true&id=" + this.license.getId();
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }
    
    public String delete() {
        if (!this.conversation.isTransient()) {
            this.conversation.end();
        }        
        try {
            this.entityManager.remove(this.entityManager.find(License.class, getId()));
            this.entityManager.flush();
            return "view?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public Collection<License> findAllLicenses() {
        TypedQuery<License> query = entityManager.createNamedQuery("License.findAll", License.class);
        return (Collection<License>)query.getResultList();
    }
    
    public Converter getConverter() {
        
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                return LicenseBean.this.entityManager.find(License.class, new Integer(value));
            }
            
            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                if (value==null) {
                    return "";
                }
                return value.toString();
            }
        };
    }
}

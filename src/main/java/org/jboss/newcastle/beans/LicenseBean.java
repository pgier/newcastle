package org.jboss.newcastle.beans;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    private EntityManager em;
    
    public String create() {
        this.conversation.begin();
        return "create?faces-redirect=true";
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
            this.license = this.em.find(License.class, getId());
        }
    }

    public String update() {
        this.conversation.end();
        
        try {
            if (this.id == null) {
                this.em.persist(this.license);
                return "search?faces-redirect=true";
            } else {
                this.em.merge(this.license);
                return "search?faces-redirect=true&id=" + this.license.getId();
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }
    
    public String delete() {
        this.conversation.end();
        
        try {
            this.em.remove(this.em.find(License.class, getId()));
            this.em.flush();
            return "search?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public Collection<License> findAllLicenses() {
        Query query = em.createQuery("SELECT l FROM License l");
        return (Collection<License>)query.getResultList();
    }
}

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
import javax.persistence.TypedQuery;

import org.jboss.newcastle.model.BuildConfiguration;

@Named
@Stateful
@ConversationScoped
public class BuildConfigurationBean implements Serializable {

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

    private BuildConfiguration buildConfiguration;

    public BuildConfiguration getBuildConfiguration() {
        return buildConfiguration;
    }

    private BuildConfiguration search = new BuildConfiguration();

    @Inject
    private Conversation conversation;

    @PersistenceContext
    private EntityManager entityManager;

    public String edit() {
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
            this.buildConfiguration = this.search;
        } else {
            this.buildConfiguration = this.entityManager.find(BuildConfiguration.class, getId());
        }
    }

    public String update() {
        if (!this.conversation.isTransient()) {
            this.conversation.end();
        }

        try {
            if (this.id == null) {
                this.entityManager.persist(this.buildConfiguration);
                return "view?faces-redirect=true";
            } else {
                this.entityManager.merge(this.buildConfiguration);
                return "view?faces-redirect=true&id=" + this.buildConfiguration.getId();
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
            this.entityManager.remove(this.entityManager.find(BuildConfiguration.class, getId()));
            this.entityManager.flush();
            return "view?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public Collection<BuildConfiguration> findAllBuildConfigurations() {
        TypedQuery<BuildConfiguration> query = entityManager.createNamedQuery("BuildConfiguration.findAll", BuildConfiguration.class);
        return (Collection<BuildConfiguration>) query.getResultList();
    }
}

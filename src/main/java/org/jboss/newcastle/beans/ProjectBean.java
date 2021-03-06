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
import org.jboss.newcastle.model.Project;

@Named
@Stateful
@ConversationScoped
public class ProjectBean implements Serializable {

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

    private Project project;

    public Project getProject() {
        return project;
    }

    private Project search = new Project();

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
            this.project = this.search;
        } else {
            this.project = this.entityManager.find(Project.class, getId());
        }
    }

    public String update() {
        if (!this.conversation.isTransient()) {
            this.conversation.end();
        }

        try {
            if (this.id == null) {
                this.entityManager.persist(this.project);
                return "view?faces-redirect=true";
            } else {
                this.entityManager.merge(this.project);
                return "view?faces-redirect=true&id=" + this.project.getId();
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
            this.entityManager.remove(this.entityManager.find(Project.class, getId()));
            this.entityManager.flush();
            return "view?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public Collection<Project> findAllProjects() {
        TypedQuery<Project> query = entityManager.createNamedQuery("Project.findAll", Project.class);
        return (Collection<Project>) query.getResultList();
    }
 
    public Converter getConverter() {
        
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                return ProjectBean.this.entityManager.find(Project.class, new Integer(value));
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

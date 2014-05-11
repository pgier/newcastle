package org.jboss.newcastle.beans;

import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.newcastle.model.Project;

@Named
@RequestScoped
public class ProjectBean {

    @PersistenceContext
    private EntityManager em;
    
    private Project project;
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = 
            fc.getExternalContext().getRequestParameterMap();
        String idText = params.get("projectId");
        if (idText!=null) {
            project = findProject(Integer.decode(idText));
        }  
    }

    public Project findProject(Integer id) {
        project = (Project)em.find(Project.class, id);
        return project;
    }

    public Project getProject() {
        return project;
    }

    public Collection<Project> findAllProjects() {
        Query query = em.createQuery("SELECT p FROM Project p");
        return (Collection<Project>)query.getResultList();
    }
}

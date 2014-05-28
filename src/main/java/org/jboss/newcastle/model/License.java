package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the license database table.
 * 
 */
@Entity
@NamedQuery(name="License.findAll", query="SELECT l FROM License l")
public class License implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="full_name")
	private String fullName;

	@Column(name="full_text")
	private String fullText;

	@Column(name="ref_url")
	private String refUrl;

	@Column(name="short_name")
	private String shortName;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="license")
	private List<Project> projects;

	public License() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullText() {
		return this.fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public String getRefUrl() {
		return this.refUrl;
	}

	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setLicense(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setLicense(null);

		return project;
	}
	
	public boolean equals(Object obj) {
	    Integer compareId = new Integer(-1);
	    if (obj instanceof Integer) {
	        compareId = (Integer)obj;
	    }
	    if (obj instanceof License) {
	        compareId = ((License)obj).getId();
	    }
	    return this.id.equals(compareId);

	}

}
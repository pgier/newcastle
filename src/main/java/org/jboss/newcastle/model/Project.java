package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	private String name;

	//bi-directional many-to-one association to License
	@ManyToOne
	@JoinColumn(name="current_license_id")
	private License license;

	//bi-directional many-to-one association to BuildResult
	@OneToMany(mappedBy="project")
	private List<BuildResult> buildResults;

	public Project() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public License getLicense() {
		return this.license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public List<BuildResult> getBuildResults() {
		return this.buildResults;
	}

	public void setBuildResults(List<BuildResult> buildResults) {
		this.buildResults = buildResults;
	}

	public BuildResult addBuildResult(BuildResult buildResult) {
		getBuildResults().add(buildResult);
		buildResult.setProject(this);

		return buildResult;
	}

	public BuildResult removeBuildResult(BuildResult buildResult) {
		getBuildResults().remove(buildResult);
		buildResult.setProject(null);

		return buildResult;
	}

}
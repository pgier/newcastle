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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	@Column(name="issue_tracker_url")
	private String issueTrackerUrl;

	private String name;

	@Column(name="project_url")
	private String projectUrl;

	@Column(name="scm_url")
	private String scmUrl;

	//bi-directional many-to-one association to BuildConfiguration
	@OneToMany(mappedBy="project")
	private List<BuildConfiguration> buildConfigurations;

	//bi-directional many-to-one association to BuildResult
	@OneToMany(mappedBy="project")
	private List<BuildResult> buildResults;

	//bi-directional many-to-one association to License
	@ManyToOne
	@JoinColumn(name="current_license_id")
	private License license;

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

	public String getIssueTrackerUrl() {
		return this.issueTrackerUrl;
	}

	public void setIssueTrackerUrl(String issueTrackerUrl) {
		this.issueTrackerUrl = issueTrackerUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectUrl() {
		return this.projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public String getScmUrl() {
		return this.scmUrl;
	}

	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public List<BuildConfiguration> getBuildConfigurations() {
		return this.buildConfigurations;
	}

	public void setBuildConfigurations(List<BuildConfiguration> buildConfigurations) {
		this.buildConfigurations = buildConfigurations;
	}

	public BuildConfiguration addBuildConfiguration(BuildConfiguration buildConfiguration) {
		getBuildConfigurations().add(buildConfiguration);
		buildConfiguration.setProject(this);

		return buildConfiguration;
	}

	public BuildConfiguration removeBuildConfiguration(BuildConfiguration buildConfiguration) {
		getBuildConfigurations().remove(buildConfiguration);
		buildConfiguration.setProject(null);

		return buildConfiguration;
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

	public License getLicense() {
		return this.license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

    public boolean equals(Object obj) {
        Integer compareId = new Integer(-1);
        if (obj instanceof Integer) {
            compareId = (Integer)obj;
        }
        if (obj instanceof Project) {
            compareId = ((Project)obj).getId();
        }
        return this.id.equals(compareId);

    }

}
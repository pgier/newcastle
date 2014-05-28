package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the build_result database table.
 * 
 */
@Entity
@Table(name="build_result")
@NamedQuery(name="BuildResult.findAll", query="SELECT b FROM BuildResult b")
public class BuildResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="build_environment_description")
	private String buildEnvironmentDescription;

	@Column(name="build_log")
	private String buildLog;

	@Column(name="build_script")
	private String buildScript;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="project_name")
	private String projectName;

	@Column(name="source_url")
	private String sourceUrl;

	@Column(name="start_time")
	private Timestamp startTime;

	private String username;

	//bi-directional many-to-one association to Artifact
	@OneToMany(mappedBy="buildResult")
	private List<Artifact> artifacts;

	//bi-directional many-to-many association to BuildCollection
	@ManyToMany(mappedBy="buildResults")
	private List<BuildCollection> buildCollections;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public BuildResult() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuildEnvironmentDescription() {
		return this.buildEnvironmentDescription;
	}

	public void setBuildEnvironmentDescription(String buildEnvironmentDescription) {
		this.buildEnvironmentDescription = buildEnvironmentDescription;
	}

	public String getBuildLog() {
		return this.buildLog;
	}

	public void setBuildLog(String buildLog) {
		this.buildLog = buildLog;
	}

	public String getBuildScript() {
		return this.buildScript;
	}

	public void setBuildScript(String buildScript) {
		this.buildScript = buildScript;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Artifact> getArtifacts() {
		return this.artifacts;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public Artifact addArtifact(Artifact artifact) {
		getArtifacts().add(artifact);
		artifact.setBuildResult(this);

		return artifact;
	}

	public Artifact removeArtifact(Artifact artifact) {
		getArtifacts().remove(artifact);
		artifact.setBuildResult(null);

		return artifact;
	}

	public List<BuildCollection> getBuildCollections() {
		return this.buildCollections;
	}

	public void setBuildCollections(List<BuildCollection> buildCollections) {
		this.buildCollections = buildCollections;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
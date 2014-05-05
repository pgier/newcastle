package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the build_configuration database table.
 * 
 */
@Entity
@Table(name="build_configuration")
@NamedQuery(name="BuildConfiguration.findAll", query="SELECT b FROM BuildConfiguration b")
public class BuildConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="build_script")
	private String buildScript;

	@Column(name="source_url")
	private String sourceUrl;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to BuildTrigger
	@OneToMany(mappedBy="buildConfiguration1")
	private List<BuildTrigger> buildTriggers1;

	//bi-directional many-to-one association to BuildTrigger
	@OneToMany(mappedBy="buildConfiguration2")
	private List<BuildTrigger> buildTriggers2;

	public BuildConfiguration() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuildScript() {
		return this.buildScript;
	}

	public void setBuildScript(String buildScript) {
		this.buildScript = buildScript;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<BuildTrigger> getBuildTriggers1() {
		return this.buildTriggers1;
	}

	public void setBuildTriggers1(List<BuildTrigger> buildTriggers1) {
		this.buildTriggers1 = buildTriggers1;
	}

	public BuildTrigger addBuildTriggers1(BuildTrigger buildTriggers1) {
		getBuildTriggers1().add(buildTriggers1);
		buildTriggers1.setBuildConfiguration1(this);

		return buildTriggers1;
	}

	public BuildTrigger removeBuildTriggers1(BuildTrigger buildTriggers1) {
		getBuildTriggers1().remove(buildTriggers1);
		buildTriggers1.setBuildConfiguration1(null);

		return buildTriggers1;
	}

	public List<BuildTrigger> getBuildTriggers2() {
		return this.buildTriggers2;
	}

	public void setBuildTriggers2(List<BuildTrigger> buildTriggers2) {
		this.buildTriggers2 = buildTriggers2;
	}

	public BuildTrigger addBuildTriggers2(BuildTrigger buildTriggers2) {
		getBuildTriggers2().add(buildTriggers2);
		buildTriggers2.setBuildConfiguration2(this);

		return buildTriggers2;
	}

	public BuildTrigger removeBuildTriggers2(BuildTrigger buildTriggers2) {
		getBuildTriggers2().remove(buildTriggers2);
		buildTriggers2.setBuildConfiguration2(null);

		return buildTriggers2;
	}

}
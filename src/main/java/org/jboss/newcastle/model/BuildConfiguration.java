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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="build_script")
	private String buildScript;

	private String name;

	@Column(name="source_id")
	private String sourceId;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to SystemImage
	@ManyToOne
	@JoinColumn(name="system_image_id")
	private SystemImage systemImage;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public SystemImage getSystemImage() {
		return this.systemImage;
	}

	public void setSystemImage(SystemImage systemImage) {
		this.systemImage = systemImage;
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
package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the build_trigger database table.
 * 
 */
@Entity
@Table(name="build_trigger")
@NamedQuery(name="BuildTrigger.findAll", query="SELECT b FROM BuildTrigger b")
public class BuildTrigger implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	//bi-directional many-to-one association to BuildConfiguration
	@ManyToOne
	@JoinColumn(name="build_configuration_id")
	private BuildConfiguration buildConfiguration1;

	//bi-directional many-to-one association to BuildConfiguration
	@ManyToOne
	@JoinColumn(name="triggered_build_configuration_id")
	private BuildConfiguration buildConfiguration2;

	public BuildTrigger() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BuildConfiguration getBuildConfiguration1() {
		return this.buildConfiguration1;
	}

	public void setBuildConfiguration1(BuildConfiguration buildConfiguration1) {
		this.buildConfiguration1 = buildConfiguration1;
	}

	public BuildConfiguration getBuildConfiguration2() {
		return this.buildConfiguration2;
	}

	public void setBuildConfiguration2(BuildConfiguration buildConfiguration2) {
		this.buildConfiguration2 = buildConfiguration2;
	}

}
package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the artifact database table.
 * 
 */
@Entity
@NamedQuery(name="Artifact.findAll", query="SELECT a FROM Artifact a")
public class Artifact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String checksum;

	private String filename;

	//bi-directional many-to-one association to BuildResult
	@ManyToOne
	@JoinColumn(name="build_result_id")
	private BuildResult buildResult;

	public Artifact() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public BuildResult getBuildResult() {
		return this.buildResult;
	}

	public void setBuildResult(BuildResult buildResult) {
		this.buildResult = buildResult;
	}

}
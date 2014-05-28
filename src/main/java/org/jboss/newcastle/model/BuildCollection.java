package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the build_collection database table.
 * 
 */
@Entity
@Table(name="build_collection")
@NamedQuery(name="BuildCollection.findAll", query="SELECT b FROM BuildCollection b")
public class BuildCollection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	private String name;

	//bi-directional many-to-many association to BuildResult
	@ManyToMany
	@JoinTable(
		name="build_collection_build_result"
		, joinColumns={
			@JoinColumn(name="build_collection_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="build_result_id")
			}
		)
	private List<BuildResult> buildResults;

	public BuildCollection() {
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

	public List<BuildResult> getBuildResults() {
		return this.buildResults;
	}

	public void setBuildResults(List<BuildResult> buildResults) {
		this.buildResults = buildResults;
	}

}
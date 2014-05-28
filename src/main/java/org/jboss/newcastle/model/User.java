package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String username;

	//bi-directional many-to-one association to BuildResult
	@OneToMany(mappedBy="user")
	private List<BuildResult> buildResults;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<BuildResult> getBuildResults() {
		return this.buildResults;
	}

	public void setBuildResults(List<BuildResult> buildResults) {
		this.buildResults = buildResults;
	}

	public BuildResult addBuildResult(BuildResult buildResult) {
		getBuildResults().add(buildResult);
		buildResult.setUser(this);

		return buildResult;
	}

	public BuildResult removeBuildResult(BuildResult buildResult) {
		getBuildResults().remove(buildResult);
		buildResult.setUser(null);

		return buildResult;
	}

}
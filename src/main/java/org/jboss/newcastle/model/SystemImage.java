package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the system_image database table.
 * 
 */
@Entity
@Table(name="system_image")
@NamedQuery(name="SystemImage.findAll", query="SELECT s FROM SystemImage s")
public class SystemImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	@Column(name="image_blob")
	private byte[] imageBlob;

	@Column(name="image_url")
	private String imageUrl;

	private String name;

	//bi-directional many-to-one association to BuildConfiguration
	@OneToMany(mappedBy="systemImage")
	private List<BuildConfiguration> buildConfigurations;

	public SystemImage() {
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

	public byte[] getImageBlob() {
		return this.imageBlob;
	}

	public void setImageBlob(byte[] imageBlob) {
		this.imageBlob = imageBlob;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BuildConfiguration> getBuildConfigurations() {
		return this.buildConfigurations;
	}

	public void setBuildConfigurations(List<BuildConfiguration> buildConfigurations) {
		this.buildConfigurations = buildConfigurations;
	}

	public BuildConfiguration addBuildConfiguration(BuildConfiguration buildConfiguration) {
		getBuildConfigurations().add(buildConfiguration);
		buildConfiguration.setSystemImage(this);

		return buildConfiguration;
	}

	public BuildConfiguration removeBuildConfiguration(BuildConfiguration buildConfiguration) {
		getBuildConfigurations().remove(buildConfiguration);
		buildConfiguration.setSystemImage(null);

		return buildConfiguration;
	}

}
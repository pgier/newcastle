package org.jboss.newcastle.model;

import java.io.Serializable;
import javax.persistence.*;


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
	private Integer id;

	private String description;

	@Column(name="image_blob")
	private byte[] imageBlob;

	@Column(name="image_url")
	private String imageUrl;

	private String name;

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

}
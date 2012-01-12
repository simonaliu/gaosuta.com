package org.varkrs.sociality.local.jpa.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private Login login;
	
	private String nickName;
	
	private String photoUrl;

	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Collection<Record> records;
	
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Collection<PhotoAlbum> photoAlbums;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "workPhotoAlbumId", referencedColumnName = "id")
	private PhotoAlbum workPhotoAlbum;
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private Collection<ViewAuthority> viewAuthorities;

	public User() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String name) {
		this.nickName = name;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setRecords(Collection<Record> records) {
		this.records = records;
	}

	public Collection<Record> getRecords() {
		return records;
	}

	public void setPhotoAlbums(Collection<PhotoAlbum> photoAlbums) {
		this.photoAlbums = photoAlbums;
	}

	public Collection<PhotoAlbum> getPhotoAlbums() {
		return photoAlbums;
	}
	
	public void setWorkPhotoAlbum(PhotoAlbum workPhotoAlbum) {
		this.workPhotoAlbum = workPhotoAlbum;
	}

	public PhotoAlbum getWorkPhotoAlbum() {
		return workPhotoAlbum;
	}

	public void setViewAuthorities(Collection<ViewAuthority> viewAuthorities) {
		this.viewAuthorities = viewAuthorities;
	}

	public Collection<ViewAuthority> getViewAuthorities() {
		return viewAuthorities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", nickName=" + nickName
				+ "]";
	}

}

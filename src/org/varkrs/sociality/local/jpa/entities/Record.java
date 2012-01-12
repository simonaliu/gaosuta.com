package org.varkrs.sociality.local.jpa.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;


@SuppressWarnings("serial")
@Entity
public class Record implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@Lob
	private String text;
	
	private String headPhotoUrl;
	
	private long generateTime;
	
	private long lastModified;
	
	@JsonIgnore
	@ManyToOne(optional=false)
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user;

	public Record() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImageUrl(String imageUrl) {
		this.headPhotoUrl = imageUrl;
	}

	public String getImageUrl() {
		return headPhotoUrl;
	}

	public long getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(long generateTime) {
		this.generateTime = generateTime;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public long getLastModified() {
		return lastModified;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
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
		Record other = (Record) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", title=" + title + ", content=" + text
				+ ", date=" + generateTime + "]";
	}
	
}

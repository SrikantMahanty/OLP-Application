package com.learnSphere.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
    private int commentId;
	private String email;
	private String comment;
	@ManyToOne
	@JoinColumn(name = "lesson_Id",referencedColumnName = "lessonId")
	private Lesson lesson;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(int commentId, String email, String comment, Lesson lesson, Date creationTimestamp) {
		super();
		this.commentId = commentId;
		this.email = email;
		this.comment = comment;
		this.lesson = lesson;
		this.creationTimestamp = creationTimestamp;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", email=" + email + ", comment=" + comment + ", lesson=" + lesson
				+ ", creationTimestamp=" + creationTimestamp + "]";
	}
	
	 @PrePersist
	protected void onCreate() {
	    creationTimestamp = new Date();
    }

    public String getFormattedCreationTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(creationTimestamp);
    }
	
	
	
}

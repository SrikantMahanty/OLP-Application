package com.learnSphere.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lesson{
	@Id
	private int lessonId;
	private String lessonName;
	private String topics;
	private String link;
	@ManyToOne
	@JoinColumn(name="course_id", referencedColumnName = "courseId")
	private Course course;
	
	@OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
	private List<Comments> comments;

	public Lesson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lesson(int lessonId, String lessonName, String topics, String link, Course course, List<Comments> comments) {
		super();
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.topics = topics;
		this.link = link;
		this.course = course;
		this.comments = comments;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", lessonName=" + lessonName + ", topics=" + topics + ", link=" + link
				+ ", course=" + course + ", comments=" + comments + "]";
	}

	

}

package com.learnSphere.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	@Id
	private int courseId;
	private String courseName;
	private int coursePrice;
	private String courseDescription;
	private String courseImage;
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Lesson> lessons;
	
	@ManyToMany(mappedBy = "courses" ,fetch = FetchType.LAZY)
	private Set<Users> Users;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int courseId, String courseName, int coursePrice, String courseDescription, String courseImage,
			List<Lesson> lessons, Set<com.learnSphere.entity.Users> users) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		this.courseDescription = courseDescription;
		this.courseImage = courseImage;
		this.lessons = lessons;
		Users = users;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Set<Users> getUsers() {
		return Users;
	}

	public void setUsers(Set<Users> users) {
		Users = users;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", coursePrice=" + coursePrice
				+ ", courseDescription=" + courseDescription + ", courseImage=" + courseImage + ", lessons=" + lessons
				+ ", Users=" + Users + "]";
	}

	

	

	
	
	
	
	

}

package com.learnSphere.services;

import java.util.List;
import java.util.Optional;

import com.learnSphere.entity.Course;
import com.learnSphere.entity.Lesson;

public interface TrainerService {
	public String addCourse(Course course);

	public String addLesson(Lesson lesson);

	public Course getCourse(int courseId);

	public List<Course> courseList();

	Optional<Course> findCourseById(int id);

	public boolean getById(int courseId);

	public boolean getByIdLesson(int lessonId);


	// Method to delete a course by its ID
	public boolean deleteCourse(int courseId);

}

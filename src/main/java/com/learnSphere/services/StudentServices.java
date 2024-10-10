package com.learnSphere.services;

import java.util.List;
import java.util.Optional;

import com.learnSphere.entity.Comments;
import com.learnSphere.entity.Course;
import com.learnSphere.entity.Lesson;
import com.learnSphere.entity.Users;

public interface StudentServices {
	Lesson getLesson(int lessonId);

	Users getUserById(int id);

	

	Course getCourseBtIdCourse(int courseId);

	Lesson getLessonById(int lessonId);

	Lesson findLessonById(int lessonId);

	void saveCooment(Comments comment);

	List<Course> courseList();

	Optional<Course> findCourseById(int id);

	Optional<Users> findById(int userId);

	void saveCourse(Users user);



	



	

	

}

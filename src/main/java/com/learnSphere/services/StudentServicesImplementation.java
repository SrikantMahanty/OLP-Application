package com.learnSphere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entity.Comments;
import com.learnSphere.entity.Course;
import com.learnSphere.entity.Lesson;
import com.learnSphere.entity.Users;
import com.learnSphere.repository.CommentRepository;
import com.learnSphere.repository.CourseRepository;
import com.learnSphere.repository.UserRepository;
import com.learnSphere.repository.lessonRepository;

@Service
public class StudentServicesImplementation implements StudentServices {
    
	@Autowired
	private lessonRepository lessonRepo;
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Lesson getLesson(int lessonId) {	
		return lessonRepo.findById(lessonId).get();
	}

	@Override
	public Users getUserById(int id) {
		
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public Course getCourseBtIdCourse(int courseId) {
		
		return courseRepository.findById(courseId).orElse(null);
	}

	@Override
	public Lesson getLessonById(int lessonId) {
		
		return lessonRepo.findById(lessonId).orElse(null);
	}

	@Override
	public Lesson findLessonById(int lessonId) {
		
		return lessonRepo.findById(lessonId).orElse(null);
	}

	@Override
	public void saveCooment(Comments comment) {
		
		commentRepository.save(comment);
	}

	@Override
	public List<Course> courseList() {
		
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> findCourseById(int id) {
		
		return courseRepository.findById(id);
	}

	@Override
	public Optional<Users> findById(int userId) {
		
		return userRepo.findById(userId);
	}

	@Override
	public void saveCourse(Users user) {
		userRepo.save(user);
		
	}

	


	

	

}

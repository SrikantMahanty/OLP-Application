package com.learnSphere.controller;

import java.util.List;
import java.util.Optional;

import com.learnSphere.entity.Comments;
import com.learnSphere.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learnSphere.entity.Course;
import com.learnSphere.entity.Users;
import com.learnSphere.services.StudentServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	private StudentServices service;

	@GetMapping("/showPurchaseCourse")
	public String showPurchaseCourse(Model model, HttpSession session) {
		List<Course> courseList = service.courseList();
		model.addAttribute("courseList", courseList);
		Integer userId = (Integer) session.getAttribute("userId");
		model.addAttribute("userId", userId);
		return "purchaseCourse";
	}

	@PostMapping("/buy-course/{id}")
	public String getUserById(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes res) {
		Optional<Course> course = service.findCourseById(id);
		Integer userId = (Integer) session.getAttribute("userId");
		Users user = service.findById(userId).orElse(null);

		if (user != null && user.getCourses().contains(course.get())) {
			res.addFlashAttribute("message", "⚠ You already purchased! Choose another course.");
			return "redirect:/showPurchaseCourse";
		} else {
			if (course.isPresent()) {
				user.getCourses().add(course.get());
				service.saveCourse(user); // Save the updated user with the purchased course
				return "redirect:/payment-success"; // Redirect to a success page or confirmation
			} else {
				return "redirect:/payment-failure"; // Handle errors if the course is not found
			}
		}
	}

	@GetMapping("/user/{id}")
	public String myCourses(@PathVariable("id") int id, Model model) {
		Users user = service.getUserById(id);
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("courseSet", user.getCourses());
			return "myCourses";
		} else {
			return "redirect:/studentHome"; // Redirect to a students list page or error page
		}
	}

	@GetMapping("/user/course/{courseId}")
	public String myLessons(@PathVariable int courseId, Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		Users user = service.findById(userId).orElse(null);
		Course course = service.getCourseBtIdCourse(courseId);

		if (course != null) {
			model.addAttribute("user", user);
			model.addAttribute("course", course);
			model.addAttribute("lesson", course.getLessons());
			return "myLessons";
		} else {
			return "redirect:/myLessons";
		}
	}

	@GetMapping("/user/course/lessons/{lessonId}")
	public String myLectures(@PathVariable int lessonId, Model model, HttpSession session) {
		Lesson lesson = service.getLessonById(lessonId);
		Integer userId = (Integer) session.getAttribute("userId");
		Users user = service.findById(userId).orElse(null);

		model.addAttribute("user", user);
		model.addAttribute("lesson", lesson);
		model.addAttribute("comments", lesson.getComments());
		model.addAttribute("course", lesson.getCourse());
		model.addAttribute("newComment", new Comments());
		return "lecture";
	}

	@PostMapping("/user/course/lessons/{lessonId}/comments")
	public String postComment(@PathVariable int lessonId, @ModelAttribute Comments newComment) {
		Lesson lesson = service.findLessonById(lessonId);
		if (lesson != null) {
			Comments comment = new Comments();
			comment.setEmail(newComment.getEmail());
			comment.setComment(newComment.getComment());
			comment.setLesson(lesson);
			//service.saveComment(comment);
		}
		return "redirect:/user/course/lessons/" + lessonId;
	}
}

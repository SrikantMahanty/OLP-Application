package com.learnSphere.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learnSphere.entity.Course;
import com.learnSphere.entity.Lesson;
import com.learnSphere.services.TrainerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TrainerController {

	@Autowired
	private TrainerService tService;

	@PostMapping("/addCourse")
     public String addCourse(@RequestParam("courseId") int courseId,@RequestParam("courseName") String courseName,@RequestParam("coursePrice")int coursePrice,@RequestParam("courseDescription") String courseDescription,@RequestParam("courseImage") String courseImage, RedirectAttributes res) {
		boolean isCourseExist=tService.getById(courseId);
		if(isCourseExist) {
			res.addFlashAttribute("error", "Course id is already exist!");
			return "redirect:/createCourse";
		}
		else {
			Course course=new Course();
			course.setCourseId(courseId);
			course.setCourseName(courseName);
			course.setCoursePrice(coursePrice);
			course.setCourseDescription(courseDescription);
			course.setCourseImage(courseImage);
			tService.addCourse(course);
			res.addFlashAttribute("alert_message", "✅ New Course added successful!");
			return "redirect:/trainerHome";
		}


	}


	@PostMapping("/lesson")
	public String lesson(@RequestParam("courseId")int courseId,@RequestParam("lessonId")int lessonId,@RequestParam("lessonName") String  lessonName,@RequestParam("topics") String topics,@RequestParam("link") String link,RedirectAttributes res) {
		Course course=tService.getCourse(courseId);
		Lesson lesson=new Lesson();
		boolean isLessonExist=tService.getByIdLesson(lessonId);
		if(isLessonExist) {
			res.addFlashAttribute("error", "Course id is already exist!");
			return "redirect:/addLesson";
		}
		else {
			lesson.setLessonId(lessonId);
		lesson.setLessonName(lessonName);
		lesson.setTopics(topics);
		lesson.setLink(link);
		lesson.setCourse(course);
		tService.addLesson(lesson);
		course.getLessons().add(lesson);
		res.addFlashAttribute("alert_message", "✅ New Lesson added successful!");
		return"redirect:/trainerHome";
		}


	}


	@GetMapping("/showCourses")
	public String showCourses(Model model) {
		List<Course> courseList=tService.courseList();
		model.addAttribute("courseList",courseList);
		return"courses";
	}

	// Method to delete a course by its ID
	@GetMapping("/deleteCourse/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int courseId, RedirectAttributes res) {
		boolean isDeleted = tService.deleteCourse(courseId);
		if (isDeleted) {
			res.addFlashAttribute("alert_message", "✅ Course deleted successfully!");
		} else {
			res.addFlashAttribute("error", "❌ Course not found or couldn't be deleted.");
		}
		return "redirect:/showCourses";
	}





}

		package com.learnSphere.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learnSphere.entity.Contact;
import com.learnSphere.entity.Users;
import com.learnSphere.services.ContactServices;
import com.learnSphere.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class serviceController {
@Autowired	
private UserService us;

@Autowired
private ContactServices contactServices;


//	public serviceController(UserService us) {
//		super();
//		this.us = us;
//	}

	@PostMapping("/addUser")
	public String addUser(@RequestParam("fName") String fName,
			@RequestParam("lName") String lName, @RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("gender") String gender,
			@RequestParam("role") String role,@RequestParam("dob") String dob,@RequestParam("phone") long phone,RedirectAttributes res) {
		boolean emailExists=us.checkEmail(email);
		if(emailExists==false) {
			Users u=new Users();
			u.setFirstName(fName);
			u.setLastName(lName);
			u.setEmail(email);
			u.setPassword(password);
			u.setGender(gender);
			u.setRole(role);
			u.setDob(dob);
			u.setMobile(phone);
			us.addUser(u);
			
			res.addFlashAttribute("success", "successfully register!");
			System.out.println("User registered successfully!");
			return "redirect:/register_pop_up";
		}
		else {
			res.addFlashAttribute("error", "Email Already exists");
			System.out.println("User already exists!");
			return "redirect:/register";
			
		}
	    
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password") String password,RedirectAttributes res,Model model,HttpSession session) {
		boolean val= us.vaildate(email, password);
		Users user=us.getUser(email);
		if (val==true) {
			if(us.getUserRole(email).equals("Trainer")) {
				model.addAttribute("success",user.getFirstName()+"!");
				return "trainerHome";
			}
			else {
				model.addAttribute("success",user.getFirstName()+"!");
				model.addAttribute("user", user);
				int userId = user.getId(); // Get the user's ID
				session.setAttribute("userId", userId);
				return "studentHome";
			}
		}
		else {
			res.addFlashAttribute("error", "**Incorrect email address or password. Please try again!.");
			System.out.println("Incorrect credentials, try again");
			return "redirect:/login";
		}
		
		
	}
	
	//contact
	@PostMapping("/contactUs")
	public String contact(@RequestParam("name")String name,@RequestParam("email")String email,@RequestParam("message")String message) {
		Contact contact=new Contact();
		contact.setName(name);
		contact.setEmail(email);
		contact.setMessage(message);
		contactServices.sentMessage(contact);
		return "redirect:/contact_pop_up";
		
	}

}

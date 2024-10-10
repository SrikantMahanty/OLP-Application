package com.learnSphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entity.Comments;
import com.learnSphere.repository.CommentRepository;

@Service
public class CommentServicesImplementation implements CommentServices{
    
	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public String addComments(Comments comment) {
		commentRepo.save(comment);
		return "Comment added!";
	}

	@Override
	public List<Comments> commentList() {
		return commentRepo.findAll();
	}

}

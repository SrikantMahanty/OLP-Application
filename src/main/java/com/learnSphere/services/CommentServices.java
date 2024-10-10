package com.learnSphere.services;
import java.util.List;

import com.learnSphere.entity.Comments;
public interface CommentServices {
	String addComments(Comments comment);
	List<Comments> commentList();

}

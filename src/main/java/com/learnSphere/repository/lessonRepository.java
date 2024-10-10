package com.learnSphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnSphere.entity.Lesson;

@Repository
public interface lessonRepository extends JpaRepository<Lesson, Integer> {

}

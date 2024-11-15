package com.loctran.service.entity.question;

import com.loctran.service.entity.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}

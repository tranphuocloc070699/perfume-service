package com.loctran.service.entity.question;

import com.loctran.service.entity.answer.Answer;
import com.loctran.service.entity.answer.AnswerRepository;
import com.loctran.service.entity.question.dto.QuestionDto;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.entity.year.Year;
import com.loctran.service.entity.year.dto.CreateYearDto;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;


    public List<Question> getAll(){

        return questionRepository.findAll();
    }

    public Question findById (Long id){
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question","id",id.toString()));
    }


    public Question create( Long userId,QuestionDto dto){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId.toString()));
        Question question = Question.builder().title(dto.getTitle()).description(dto.getDescription()).user(user).build();


        return questionRepository.save(question);
    }

    public Question update( Long id,QuestionDto dto){
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question","id",id.toString()));
        List<Answer> answers = answerRepository.findAllByQuestionId(id);
        if(!answers.isEmpty()) throw new ForbiddenException("Không thể chỉnh sửa câu hỏi");

        question.setTitle(dto.getTitle());
        question.setDescription(dto.getDescription());
        return questionRepository.save(question);
    }

    public Question delete(Long id){
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question","id",id.toString()));
        List<Answer> answers = answerRepository.findAllByQuestionId(id);
        if(!answers.isEmpty()) throw new ForbiddenException("Không thể xóa câu hỏi");
        questionRepository.delete(question);
        return question;
    }
}

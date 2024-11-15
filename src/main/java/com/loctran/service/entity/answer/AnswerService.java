package com.loctran.service.entity.answer;

import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.comment.CommentRepository;
import com.loctran.service.entity.question.Question;
import com.loctran.service.entity.question.QuestionRepository;
import com.loctran.service.entity.question.dto.QuestionDto;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    public List<Answer> getAll(Long questionId){
        return answerRepository.findAllByQuestionId(questionId);
    }

    public Answer findById (Long id){
        return answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer","id",id.toString()));
    }

    public Answer create(Long userId,Long questionId, AnswerDto dto){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId.toString()));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question","Id",userId.toString()));
        Answer answer = Answer.builder().thumbnails(dto.getThumbnails()).question(question).user(user).build();
        return answerRepository.save(answer);
    }

    public Answer update(Long id,AnswerDto dto){
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer","id",id.toString()));

        if(!answer.getVotes().isEmpty()) throw new ForbiddenException("Không thể chỉnh sửa câu trả lời");

        List<Comment> comments = commentRepository.findAllByAnswerId(answer.getId());

        if(!comments.isEmpty()) throw new ForbiddenException("Không thể chỉnh sửa câu trả lời");

        answer.setThumbnails(dto.getThumbnails());

        return answerRepository.save(answer);
    }

    public Answer delete(Long id){
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer","id",id.toString()));

        if(!answer.getVotes().isEmpty()) throw new ForbiddenException("Không thể  câu trả lời");

        List<Comment> comments = commentRepository.findAllByAnswerId(answer.getId());

        if(!comments.isEmpty()) throw new ForbiddenException("Không thể xóa câu trả lời");

        answerRepository.delete(answer);

        return answer;
    }

    public Answer toggleVote(Long answerId,Long userId){
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer","id",answerId.toString()));

        if(answer.getVotes().contains(userId)){
            answer.getVotes().remove(userId);
        }else{
            answer.getVotes().add(userId);
        }
        return answerRepository.save(answer);
    }


}

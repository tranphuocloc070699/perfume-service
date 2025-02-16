package com.loctran.service.entity.answer;

import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.comment.CommentService;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaService;
import com.loctran.service.entity.question.Question;
import com.loctran.service.entity.question.QuestionService;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserService;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final QuestionService questionService;
    private final UserService userService;
    private final CommentService commentService;
    private final MediaService mediaService;

    public List<Answer> getAll(Long questionId){
        return answerRepository.findAllByQuestionId(questionId);
    }

    public Answer findById (Long id){
        return answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
            ResponseMessage.DATA_NOT_FOUND));
    }

    public Answer create(Long userId,Long questionId, AnswerDto dto){
        User user = userService.findById(userId);
        Question question = questionService.findById(questionId);
        List<Media> mediaList = new ArrayList<>();

        dto.getMediaIds().forEach(mediaId ->{
            mediaList.add(mediaService.findById(mediaId));
        });

        Answer answer = Answer.builder().mediaList(mediaList).question(question).user(user).build();

        return answerRepository.save(answer);
    }

    public Answer updateById(Long id,AnswerDto dto){
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
        if(!answer.getVotes().isEmpty()) throw new ForbiddenException(ResponseMessage.UPDATE_DATA_FORBIDDEN);
        List<Comment> comments = commentService.findAllByAnswerId(answer.getId());
        if(!comments.isEmpty()) throw new ForbiddenException(ResponseMessage.UPDATE_DATA_FORBIDDEN);

        List<Media> mediaList = new ArrayList<>();

        dto.getMediaIds().forEach(mediaId ->{
            mediaList.add(mediaService.findById(mediaId));
        });
        answer.setMediaList(mediaList);
        return answerRepository.save(answer);
    }

    public Answer deleteById(Long id){
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
        if(!answer.getVotes().isEmpty()) throw new ForbiddenException(ResponseMessage.DELETE_DATA_FORBIDDEN);

        List<Comment> comments = commentService.findAllByAnswerId(answer.getId());
        if(!comments.isEmpty()) throw new ForbiddenException(ResponseMessage.DELETE_DATA_FORBIDDEN);

        answerRepository.delete(answer);

        return answer;
    }

    public Answer toggleVote(Long answerId,Long userId){
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));

        if(answer.getVotes().contains(userId)){
            answer.getVotes().remove(userId);
        }else{
            answer.getVotes().add(userId);
        }
        return answerRepository.save(answer);
    }


}

package com.loctran.service.entity.question;


import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.comment.dto.CreateCommentDto;
import com.loctran.service.entity.comment.dto.UpdateCommentDto;
import com.loctran.service.entity.question.dto.QuestionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final CommonService commonService;


    @GetMapping("")
    public ResponseEntity<ResponseDto> getAll() {

        List<Question> questions = questionService.getAll();
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(questions);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
        Question question =  questionService.findById(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> create(HttpServletRequest request,@RequestBody QuestionDto dto) {
        Long userId = commonService.getUserId(request);
        Question question = questionService.create(userId,dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Tạo câu  thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id")Long questionId,@RequestBody QuestionDto dto) {

        Question question = questionService.update(questionId,dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Chỉnh sửa câu hỏi thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long questionId) {
        Question question = questionService.delete(questionId);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Xóa câu  thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }
}

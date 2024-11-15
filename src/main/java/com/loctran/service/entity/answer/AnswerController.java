package com.loctran.service.entity.answer;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.question.Question;
import com.loctran.service.entity.question.dto.QuestionDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answer")
@RequiredArgsConstructor
public class AnswerController {
private final AnswerService answerService;
private final CommonService commonService;


    @GetMapping("/question/{id}")
    public ResponseEntity<ResponseDto> getAll(@PathVariable("id") Long id) {
        List<Answer> answers = answerService.getAll(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(answers);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
        Answer answer =  answerService.findById(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(answer);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("question/{id}")
    public ResponseEntity<ResponseDto> create(HttpServletRequest request,@PathVariable("id") Long questionId,@RequestBody AnswerDto dto) {
        Long userId = commonService.getUserId(request);
       Answer answer =  answerService.create(userId,questionId,dto);

        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Tạo câu trả  thành công");
        responseDto.setStatus(200);
        responseDto.setData(answer);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id")Long answerId,@RequestBody AnswerDto dto) {

       Answer answer = answerService.update(answerId,dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Chỉnh sửa câu trả lời thành công");
        responseDto.setStatus(200);
        responseDto.setData(answer);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("{id}/toggle")
    public ResponseEntity<ResponseDto> toggleVote(HttpServletRequest request,@PathVariable("id")Long answerId) {
        Long userId = commonService.getUserId(request);
        Answer answer = answerService.toggleVote(answerId,userId);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Toggle vote thành công");
        responseDto.setStatus(200);
        responseDto.setData(answer);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long answerId) {
        Answer answer = answerService.delete(answerId);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Xóa câu  thành công");
        responseDto.setStatus(200);
        responseDto.setData(answer);
        return ResponseEntity.ok(responseDto);
    }



}

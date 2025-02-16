package com.loctran.service.entity.question;


import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.question.dto.QuestionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Question", description = "APIs for managing questions")
@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final CommonService commonService;


    /**
     * Retrieves all questions.
     *
     * @return A ResponseEntity containing a list of questions and status message.
     */
    @Operation(summary = "Get all questions", description = "Retrieves all questions")
    @GetMapping("")
    public ResponseEntity<ResponseDto> getAll() {
        List<Question> questions = questionService.getAll();
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(questions);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Retrieves a specific question by its ID.
     *
     * @param id The ID of the question.
     * @return A ResponseEntity containing the requested question and status message.
     */
    @Operation(summary = "Get a question by ID", description = "Retrieves a specific question by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
        Question question = questionService.findById(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Creates a new question.
     *
     * @param request The HTTP request containing the user's authentication data.
     * @param dto The data transfer object containing question details.
     * @return A ResponseEntity containing the created question and status message.
     */
    @Operation(summary = "Create a question", description = "Creates a new question")
    @PostMapping("")
    public ResponseEntity<ResponseDto> create(HttpServletRequest request, @RequestBody QuestionDto dto) {
        Long userId = commonService.getUserId(request);
        Question question = questionService.create(userId, dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Tạo câu hỏi thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Updates an existing question.
     *
     * @param questionId The ID of the question to update.
     * @param dto The data transfer object containing updated question details.
     * @return A ResponseEntity containing the updated question and status message.
     */
    @Operation(summary = "Update a question", description = "Updates an existing question")
    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long questionId, @RequestBody QuestionDto dto) {
        Question question = questionService.update(questionId, dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Chỉnh sửa câu hỏi thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Deletes a question by its ID.
     *
     * @param questionId The ID of the question to delete.
     * @return A ResponseEntity containing the deleted question and status message.
     */
    @Operation(summary = "Delete a question", description = "Deletes a question by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long questionId) {
        Question question = questionService.delete(questionId);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Xóa câu hỏi thành công");
        responseDto.setStatus(200);
        responseDto.setData(question);
        return ResponseEntity.ok(responseDto);
    }
}

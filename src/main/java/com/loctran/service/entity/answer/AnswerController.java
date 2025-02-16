package com.loctran.service.entity.answer;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing answers related to questions.
 * Provides endpoints for retrieving, creating, updating, voting, and deleting answers.
 */
@Tag(name = "Answer", description = "APIs for managing answers")
@RestController
@RequestMapping("answer")
@RequiredArgsConstructor
public class AnswerController {
  private final AnswerService answerService;
  private final CommonService commonService;

  /**
   * Retrieves all answers associated with a specific question.
   *
   * @param id The ID of the question.
   * @return A ResponseEntity containing a list of answers and status message.
   */
  @Operation(summary = "Get all answers for a question", description = "Retrieves all answers associated with a specific question")
  @GetMapping("/question/{id}")
  public ResponseEntity<ResponseDto> getAll(@PathVariable("id") Long id) {
    List<Answer> answers = answerService.getAll(id);
    return ResponseEntity.ok(ResponseDto.getDataSuccess(answers));
  }

  /**
   * Retrieves a specific answer by its ID.
   *
   * @param id The ID of the answer.
   * @return A ResponseEntity containing the requested answer and status message.
   */
  @Operation(summary = "Get an answer by ID", description = "Retrieves a specific answer by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
    Answer answer = answerService.findById(id);

    return ResponseEntity.ok(ResponseDto.getDataSuccess(answer));
  }

  /**
   * Creates a new answer for a specific question.
   *
   * @param request The HTTP request containing the user's authentication data.
   * @param questionId The ID of the question to which the answer belongs.
   * @param dto The data transfer object containing answer details.
   * @return A ResponseEntity containing the created answer and status message.
   */
  @Operation(summary = "Create an answer", description = "Creates a new answer for a specific question")
  @PostMapping("/question/{id}")
  public ResponseEntity<ResponseDto> create(HttpServletRequest request, @PathVariable("id") Long questionId, @RequestBody AnswerDto dto) {
    Long userId = commonService.getUserId(request);
    Answer answer = answerService.create(userId, questionId, dto);

    return ResponseEntity.ok(ResponseDto.createDataSuccess(answer));
  }

  /**
   * Updates an existing answer.
   *
   * @param answerId The ID of the answer to update.
   * @param dto The data transfer object containing updated answer details.
   * @return A ResponseEntity containing the updated answer and status message.
   */
  @Operation(summary = "Update an answer", description = "Updates an existing answer")
  @PutMapping("{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long answerId, @RequestBody AnswerDto dto) {
    Answer answer = answerService.updateById(answerId, dto);

    return ResponseEntity.ok(ResponseDto.updateDataSuccess(answer));
  }

  /**
   * Toggles the vote status of an answer for a user.
   *
   * @param request The HTTP request containing the user's authentication data.
   * @param answerId The ID of the answer to toggle the vote.
   * @return A ResponseEntity containing the updated answer and status message.
   */
  @Operation(summary = "Toggle vote for an answer", description = "Toggles the vote status of an answer for a user")
  @PutMapping("{id}/toggle")
  public ResponseEntity<ResponseDto> toggleVote(HttpServletRequest request, @PathVariable("id") Long answerId) {
    Long userId = commonService.getUserId(request);
    Answer answer = answerService.toggleVote(answerId, userId);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(answer));
  }

  /**
   * Deletes an answer by its ID.
   *
   * @param answerId The ID of the answer to delete.
   * @return A ResponseEntity containing the deleted answer and status message.
   */
  @Operation(summary = "Delete an answer", description = "Deletes an answer by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long answerId) {
    Answer answer = answerService.deleteById(answerId);
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(answer));
  }
}

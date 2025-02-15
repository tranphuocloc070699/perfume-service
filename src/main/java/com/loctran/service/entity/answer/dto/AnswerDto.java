package com.loctran.service.entity.answer.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private Long id;
    private Set<String> thumbnails;

}

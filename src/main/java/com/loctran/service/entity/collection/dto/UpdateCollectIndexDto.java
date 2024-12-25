package com.loctran.service.entity.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCollectIndexDto {
    private Long collectionId;
    private Integer index;
}

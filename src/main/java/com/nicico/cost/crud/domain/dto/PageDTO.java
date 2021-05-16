package com.nicico.cost.crud.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private Long totalElement;
    private Integer pageSize;
    private T object;
}

package com.example.DoubleV.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {
    List<T> items;
    int page;
    int sizePage;
    int totalPages;
    long sizeItems;
    Pageable nextPage;
    Pageable previousPage;
}

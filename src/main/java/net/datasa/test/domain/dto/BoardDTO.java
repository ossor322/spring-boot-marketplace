package net.datasa.test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 판매글 DTO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    Integer id;
    String writer;
    String category;
    String title;
    String contents;
    Integer price;
    Boolean soldout;
    String buyer;
    LocalDateTime inputDate;
}

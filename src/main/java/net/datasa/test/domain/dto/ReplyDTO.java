package net.datasa.test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리플 DTO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    Integer id;
    Integer boardNum;
    String writer;
    String replyText;
}

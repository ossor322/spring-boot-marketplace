package net.datasa.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.BoardDTO;
import net.datasa.test.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 거래 게시판 Ajax 요청 처리 콘트롤러
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("ajax/board")
public class BoardRestController {
    private final BoardService boardService;

    @GetMapping("list")
    public List<BoardDTO> list(
            @RequestParam("category") String category,
            @RequestParam("searchWord") String searchWord) {
        return boardService.list(category, searchWord);
    }
}

package net.datasa.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.BoardDTO;
import net.datasa.test.security.AuthenticatedUser;
import net.datasa.test.service.BoardService;
import net.datasa.test.service.ReplyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 거래 게시판 관련 콘트롤러
 */

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("write")
    public String write() {
        return "board/writeForm";
    }

    @PostMapping("write")
    public String write(@AuthenticationPrincipal AuthenticatedUser user, @ModelAttribute BoardDTO boardDTO) {
        boardService.write(user.getUsername(), boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("boardList", boardService.list());
        return "board/list";
    }

    @GetMapping("read")
    public String read(@RequestParam("boardNum") Integer boardNum, Model model) {
        model.addAttribute("board", boardService.findBoardDTOByBoardNum(boardNum));
        model.addAttribute("replyList", replyService.findReplyListByBoardNum(boardNum));
        return "board/read";
    }

    @GetMapping("delete")
    public String delete(@AuthenticationPrincipal AuthenticatedUser user
            , @RequestParam("boardNum") Integer boardNum) {
        boardService.delete(user.getUsername(), boardNum);

        return "redirect:/board/list";
    }

    @GetMapping("buy")
    public String buy(@AuthenticationPrincipal AuthenticatedUser user
            , @RequestParam("boardNum") Integer boardNum) {
        try {
            boardService.buy(user.getUsername(), boardNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/board/read?boardNum=" + boardNum;
    }
}

package net.datasa.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.ReplyDTO;
import net.datasa.test.security.AuthenticatedUser;
import net.datasa.test.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("reply")
@Controller
public class ReplyController {
    private final ReplyService replyService;

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<List<ReplyDTO>> list(@RequestParam("boardNum") Integer boardNum) {
        return ResponseEntity.ok().body(replyService.findReplyListByBoardNum(boardNum));
    }

    @PostMapping("write")
    public ResponseEntity<?> write(@AuthenticationPrincipal AuthenticatedUser user
            , @ModelAttribute ReplyDTO replyDTO) {
        try {
            replyService.write(user.getUsername(), replyDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal AuthenticatedUser user
            , @RequestParam("boardNum") Integer boardNum
            , @RequestParam("replyId") Integer replyId) {
        try {
            replyService.delete(user.getUsername(), boardNum, replyId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}

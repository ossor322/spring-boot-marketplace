package net.datasa.test.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.ReplyDTO;
import net.datasa.test.domain.entity.ReplyEntity;
import net.datasa.test.repository.ReplyRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public List<ReplyDTO> findReplyListByBoardNum(Integer boardNum) {
        return replyRepository.findByBoard_Id(boardNum)
                .stream()
                .map(e -> ReplyDTO.builder()
                        .id(e.getId())
                        .boardNum(e.getBoard().getId())
                        .writer(e.getMember().getMemberId())
                        .replyText(e.getReplyText())
                        .build())
                .toList();
    }

    public void write(String username, ReplyDTO replyDTO) {
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setMember(memberService.findById(username));
        replyEntity.setBoard(boardService.findBoardByBoardNum(replyDTO.getBoardNum()));
        replyEntity.setReplyText(replyDTO.getReplyText());

        replyRepository.save(replyEntity);
    }

    public void delete(String username, Integer boardNum, Integer replyId) {
        ReplyEntity reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new EntityNotFoundException("replyId: " + replyId));
        if (!reply.getMember().getMemberId().equals(username)) {
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        }

        replyRepository.deleteById(replyId);
    }
}

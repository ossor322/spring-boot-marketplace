package net.datasa.test.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.BoardDTO;
import net.datasa.test.domain.entity.BoardEntity;
import net.datasa.test.domain.entity.MemberEntity;
import net.datasa.test.repository.BoardRepository;
import net.datasa.test.repository.ReplyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final ReplyRepository replyRepository;

    public List<BoardDTO> list() {
        return boardRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(BoardService::entityToDTO)
                .toList();
    }

    public List<BoardDTO> list(String category, String searchWord) {
        return boardRepository.findByCategoryAndTitleOrContents(category, searchWord)
                .stream()
                .map(BoardService::entityToDTO)
                .toList();
    }

    public void write(String memberId, BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setMember(memberService.findById(memberId));
        boardEntity.setCategory(boardDTO.getCategory());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContents(boardDTO.getContents());
        boardEntity.setPrice(boardDTO.getPrice());
        boardEntity.setSoldout(false);
        boardRepository.save(boardEntity);
    }

    private static BoardDTO entityToDTO(BoardEntity boardEntity) {
        return BoardDTO.builder()
                .id(boardEntity.getId())
                .writer(boardEntity.getMember().getMemberId())
                .category(boardEntity.getCategory())
                .title(boardEntity.getTitle())
                .contents(boardEntity.getContents())
                .price(boardEntity.getPrice())
                .soldout(boardEntity.getSoldout())
                .buyer(boardEntity.getSoldout() ? boardEntity.getBuyer().getMemberId() : "")
                .inputDate(boardEntity.getInputDate())
                .build();
    }

    public BoardDTO findBoardDTOByBoardNum(Integer boardNum) {
        return entityToDTO(boardRepository.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("boardNum:" + boardNum)));
    }

    public BoardEntity findBoardByBoardNum(Integer boardNum) {
        return boardRepository.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("boardNum:" + boardNum));
    }

    public void delete(String username, Integer boardNum) {
        BoardEntity board = boardRepository.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("boardNum:" + boardNum));

        if (!board.getMember().getMemberId().equals(username)) {
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        }
        replyRepository.deleteByBoard_Id(board.getId());
        boardRepository.deleteById(boardNum);
    }

    public void buy(String username, Integer boardNum) {
        BoardEntity boardEntity = boardRepository.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("boardNum:" + boardNum));

        MemberEntity buyer = memberService.findById(username);
        boardEntity.setSoldout(true);
        boardEntity.setBuyer(buyer);
    }
}


package net.datasa.test.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.test.domain.dto.MemberDTO;
import net.datasa.test.domain.entity.MemberEntity;
import net.datasa.test.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * 회원정보 서비스
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    // WebSecurityConfig에서 생성한 암호화 인코더
    private final BCryptPasswordEncoder passwordEncoder;

    public void join(MemberDTO memberDTO) {
        if (memberRepository.existsById(memberDTO.getMemberId())) {
            throw new EntityExistsException(MessageFormat.format("memberId: {0}가 이미 존재합니다.", memberDTO.getMemberId()));
        }
        memberRepository.save(dtoToEntity(memberDTO));
    }

    public MemberEntity dtoToEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPw(passwordEncoder.encode(memberDTO.getMemberPw()));
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setPhone(memberDTO.getPhone());
        return memberEntity;
    }

    public MemberEntity findById(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new EntityNotFoundException(MessageFormat.format("memberId: {0}", memberId))
                );
    }
}

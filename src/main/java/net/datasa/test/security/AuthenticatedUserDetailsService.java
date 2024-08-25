package net.datasa.test.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.entity.MemberEntity;
import net.datasa.test.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 사용자 인증 처리
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticatedUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("로그인 시도 : {}", id);
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id: " + id));

        return AuthenticatedUser.builder()
                .id(member.getMemberId())
                .password(member.getMemberPw())
                .build();
    }
}

package com.look.house.auth;

import com.look.house.domain.Member;
import com.look.house.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUser(username).orElse(null);
        if(member !=null){
            return new PrincipalDetails(member);
        }
        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
    }
}

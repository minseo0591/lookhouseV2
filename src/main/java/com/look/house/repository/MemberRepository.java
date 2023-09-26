package com.look.house.repository;

import com.look.house.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberRepository {

    Optional<Member> findByUser(String email);


    void memberSave(Member member);
}

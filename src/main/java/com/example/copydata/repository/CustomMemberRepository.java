package com.example.copydata.repository;

import com.example.copydata.domain.MemberId;
import com.example.copydata.domain.CustomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMemberRepository extends JpaRepository<CustomMember, MemberId> {
}

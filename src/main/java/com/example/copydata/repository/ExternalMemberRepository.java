package com.example.copydata.repository;

import com.example.copydata.domain.ExternalMember;
import com.example.copydata.domain.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalMemberRepository extends JpaRepository<ExternalMember, MemberId> {
}

package com.example.copydata.domain;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tn_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(of = "memberId")
public class CustomMember {

    @EmbeddedId
    private MemberId memberId;

    private String name;

    public CustomMember(LocalDateTime registDate, BigDecimal id, String name) {
        this.memberId = new MemberId(registDate, id);
        this.name = name;
    }
}

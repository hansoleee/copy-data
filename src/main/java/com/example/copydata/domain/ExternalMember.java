package com.example.copydata.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TN_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(of = "memberId")
public class ExternalMember {

    @EmbeddedId
    private MemberId memberId;

    @Column(name = "NAME")
    private String name;

    public ExternalMember(LocalDateTime registDate, BigDecimal id, String name) {
        this.memberId = new MemberId(registDate, id);
        this.name = name;
    }

    public CustomMember toCustom() {
        return new CustomMember(this.memberId.getRegistDate(), this.memberId.getId(), this.name);
    }
}

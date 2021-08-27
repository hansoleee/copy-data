package com.example.copydata.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"registDate", "id"})
public class MemberId implements Serializable {

    private LocalDateTime registDate;

    @Column(name = "member_id")
    private BigDecimal id;

    public MemberId(LocalDateTime registDate, BigDecimal id) {
        this.registDate = registDate;
        this.id = id;
    }
}

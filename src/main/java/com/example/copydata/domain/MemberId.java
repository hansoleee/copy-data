package com.example.copydata.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(of = {"registDate", "id"})
public class MemberId implements Serializable {

    @Column(name = "REGIST_DATE")
    private LocalDateTime registDate;

    @Column(name = "MEMBER_ID")
    private BigDecimal id;

    public MemberId(LocalDateTime registDate, BigDecimal id) {
        this.registDate = registDate;
        this.id = id;
    }
}

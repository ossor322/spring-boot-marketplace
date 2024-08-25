package net.datasa.test.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "market_member")
public class MemberEntity {
    @Id
    @Column(name = "member_id", nullable = false, length = 20)
    private String memberId;

    @Column(name = "member_pw", nullable = false, length = 100)
    private String memberPw;

    @Column(name = "member_name", nullable = false, length = 20)
    private String memberName;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
}
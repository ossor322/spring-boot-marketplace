package net.datasa.test.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "market_board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(nullable = false)
    private String category;  // 상품 분류 (컴퓨터, 카메라, 자동차)

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "contents", nullable = false, length = 2000)
    private String contents;

    @ColumnDefault("0")
    @Column(name = "price")
    private Integer price;

    @ColumnDefault("0")
    @Column(name = "soldout")
    private Boolean soldout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private MemberEntity buyer;

    @CreatedDate
    @Column(name = "input_date")
    private LocalDateTime inputDate;
}
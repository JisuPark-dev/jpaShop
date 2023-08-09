package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 이걸 사용하는 순간, 나는 order테이블에 있는 member의 거울일 뿐임을 선언하는 것임.
    private List<Order> orders = new ArrayList<>();



}

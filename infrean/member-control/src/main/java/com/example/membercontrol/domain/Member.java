package com.example.membercontrol.domain;

import javax.persistence.*;

@Entity
public class Member {

    // DB에서 자동으로 id 생성해주는 전략 - IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

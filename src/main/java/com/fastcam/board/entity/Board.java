package com.fastcam.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
// @Table(name="board")  // mysql 내 테이블 이름을 현재 클래스 이름과 달리해서 생성이 가능합니다
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int num;
    @Column( nullable = false )
    private String userid;
    private String email;
    private String pass;
    @Column( length = 100 )
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    private String image;
    private String savefilename;
    private int readcount;
    @CreationTimestamp
    private Timestamp writedate;

}

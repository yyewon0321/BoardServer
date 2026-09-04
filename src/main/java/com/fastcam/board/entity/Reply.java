package com.fastcam.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
public class Reply {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int replynum;
    @Column( nullable = false )
    private int boardnum;
    @Column( nullable = false )
    private String userid;
    @Column( nullable = false , length = 500 )
    private String content;
    @CreationTimestamp
    private Timestamp writedate;

}

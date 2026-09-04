package com.fastcam.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Entity
@Data
@DynamicInsert   // null인 필드는 insert 문에서 제외해 줍니다.
public class Member {

    @Id
    private String userid;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    @CreationTimestamp
    private Timestamp indate;
    @ColumnDefault("'LOCAL'")
    private String provider;
    private String snsid;
    @ColumnDefault("'F'")
    private String sns_user;
}

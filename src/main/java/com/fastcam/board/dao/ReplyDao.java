package com.fastcam.board.dao;

import com.fastcam.board.entity.Reply;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReplyDao {

    @Autowired
    private EntityManager em;


    public List<Reply> getReply(int num) {
        String sql = "select r from Reply r where r.boardnum = :bn order by r.replynum desc";
        List<Reply> list = em.createQuery(sql, Reply.class)
                .setParameter("bn", num)
                .getResultList();
        return list;
    }

    public void addReply(Reply reply) {
        em.persist(reply);
    }

    public void deleteReply(int replynum) {
        Reply reply = em.find( Reply.class, replynum);
        em.remove( reply );
    }
}

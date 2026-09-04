package com.fastcam.board.service;

import com.fastcam.board.dao.ReplyDao;
import com.fastcam.board.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReplyService {

    @Autowired
    ReplyDao rdao;


    public List<Reply> getReply(int num) {
        List<Reply> list = rdao.getReply(num);
        return list;
    }

    public void addReply(Reply reply) {
        rdao.addReply( reply );
    }

    public void deleteReply(int replynum) {
        rdao.deleteReply( replynum );
    }
}

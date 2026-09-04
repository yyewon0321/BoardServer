package com.fastcam.board.service;

import com.fastcam.board.dao.MemberDao;
import com.fastcam.board.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberDao mdao;

    public Member getMember(String userid) {
        return mdao.getMemeber(userid);
    }

    public void insertMember(Member member) {
        mdao.insertMember( member );
    }

    public void deleteMember(String userid) {
        mdao.deleteMember( userid );
    }

    public void updateMember(Member member) {
        mdao.updateMember( member );
    }
}

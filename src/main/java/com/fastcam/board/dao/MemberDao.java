package com.fastcam.board.dao;

import com.fastcam.board.entity.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    // JPA가 제공하는  엔티티를 이용해서 CRUD를 할수 있게 해주는 클래스
    @Autowired
    private EntityManager em;

    public Member getMemeber(String userid) {
        // em.find() 기본키필드를 대상으로 검색하때 사용하는 메서드
        Member member = em.find( Member.class, userid);
        return member;
    }

    public void insertMember(Member member) {
        em.persist( member);
    }

    public void deleteMember(String userid) {
        Member member = em.find( Member.class, userid);
        em.remove( member );
    }

    public void updateMember(Member member) {
        Member updateMember = em.find( Member.class, member.getUserid() );
        // find로 조회되어서 저장된entity는 테이블의 레코드와 연결됩니다

        // 엔티티 객체를 수정하면 레코드도 같이 수정됩니다
        updateMember.setPwd( member.getPwd());
        updateMember.setName(member.getName());
        updateMember.setEmail(member.getEmail());
        updateMember.setPhone(member.getPhone());

    }
}

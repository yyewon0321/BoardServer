package com.fastcam.board.dao;

import com.fastcam.board.entity.Board;
import com.fastcam.board.util.Paging;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private EntityManager em;

    public int getAllCount() {

        String sql = "select count(b) from Board b";
        // em.createQuery( sql ) 의 결과 -> 객체
        // 객체에서 원하는 결과를 꺼내려면  .getSingleResult() 를 이용합니다
        long count = (Long)em.createQuery( sql ).getSingleResult();
        return (int)count;
    }

    public List<Board> getBoardList(Paging paging) {

        String sql = "select b from Board b order by b.num desc";
        List<Board> list = em.createQuery(sql, Board.class)
                .setFirstResult( paging.getStartNum()-1 )
                .setMaxResults( paging.getDisplayRow() )
                .getResultList();
        return list;
    }

    public void insertBoard(Board board) {
        em.persist( board );
    }

    public void addReadCount(int num) {

        Board board = em.find( Board.class, num);
        int rc = board.getReadcount() + 1;
        board.setReadcount( rc );

    }

    public Board getBoard(int num) {
        Board board = em.find(Board.class, num);
        return board;
    }

    public void deleteBoard(int num) {
        Board board = em.find( Board.class, num);
        em.remove( board );
    }


    public void updateBoard(Board board) {

        Board oldBoard = em.find( Board.class, board.getNum() );

        oldBoard.setTitle(board.getTitle());
        oldBoard.setContent(board.getContent());
        oldBoard.setImage(board.getImage());
        oldBoard.setSavefilename(board.getSavefilename());

    }
}

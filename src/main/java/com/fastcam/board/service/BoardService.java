package com.fastcam.board.service;

import com.fastcam.board.dao.BoardDao;
import com.fastcam.board.entity.Board;
import com.fastcam.board.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BoardService {

    @Autowired
    BoardDao bdao;


    public HashMap<String, Object> getBoardList(int page) {

        HashMap<String, Object> result = new HashMap<>();

        Paging paging = new Paging();
        paging.setPage( page );
        int count = bdao.getAllCount();
        paging.setTotalCount( count );
        paging.calPaing();

        List<Board> list = bdao.getBoardList( paging );
        System.out.println( "size : " + list.size() );
        result.put("boardList", list);
        result.put("paging", paging);
        return result;

    }

    public void insertBoard(Board board) {
        bdao.insertBoard( board );
    }

    public void addReadCount(int num) {
        bdao.addReadCount( num );
    }

    public HashMap<String, Object> getBoard(int num) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Board board = bdao.getBoard(num);
        result.put("board", board);
        return result;
    }

    public void deleteBoard(int num) {
        bdao.deleteBoard( num );
    }

    public void updateBoard(Board board) {
        bdao.updateBoard( board );
    }
}

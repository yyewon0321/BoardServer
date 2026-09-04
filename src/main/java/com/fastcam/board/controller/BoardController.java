package com.fastcam.board.controller;

import com.fastcam.board.entity.Board;
import com.fastcam.board.service.BoardService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService bs;

    @GetMapping("/getBoardList/{page}")
    public HashMap<String, Object> getBoardList( @PathVariable("page") int page ){
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> result = bs.getBoardList(page);
        map.put("boardList", result.get("boardList") );
        map.put( "paging", result.get("paging") );
        return map;
    }

    @Autowired
    ServletContext sc;

    @PostMapping("/fileupload")
    public HashMap<String, Object> fileupload(@RequestParam("image") MultipartFile file){
        HashMap<String, Object> map = new HashMap<>();
        String path = sc.getRealPath("/images");
        Calendar today = Calendar.getInstance();
        long dt = today.getTimeInMillis();
        String filename = file.getOriginalFilename();
        String fn1 = filename.substring(0, filename.indexOf(".") );   // . 왼쪽 파일이름
        String fn2 = filename.substring(filename.indexOf(".") );   // . 오른쪽 확장자
        String uploadPath = path + "/" + fn1 + dt + fn2;
        try {
            file.transferTo( new File( uploadPath ) );
            map.put("image", filename);
            map.put("savefilename", fn1 + dt + fn2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }



    @PostMapping("/insertBoard")
    public HashMap<String, Object> insertBoard( @RequestBody Board board){
        HashMap<String, Object> map = new HashMap<String, Object>();
        bs.insertBoard( board );
        map.put("msg", "OK");
        return map;
    }


    @PostMapping("/addReadCount")
    public HashMap<String, Object> addReadCount( @RequestParam("num") int num){
        HashMap<String, Object> map = new HashMap<String, Object>();
        bs.addReadCount( num );
        map.put("msg", "OK");
        return map;
    }


    @GetMapping("/getBoard/{num}")
    public HashMap<String, Object> getBoard(@PathVariable("num") int num ){
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> result = bs.getBoard( num );
        map.put("board", result.get("board"));
        return map;
    }


    @PostMapping("/confirmPass")
    public HashMap<String, Object> confirmPass(
            @RequestParam("pass") String pass,
            @RequestParam("num") int num ){
        HashMap<String, Object> map = new HashMap<String, Object>();

        HashMap<String, Object> res = bs.getBoard( num );
        Board board = (Board)res.get("board");

        if( board.getPass().equals( pass ) )
            map.put("msg", "OK");
        else
            map.put("msg", "notOK");

        return map;
    }


    @DeleteMapping("/deleteBoard/{num}")
    public  HashMap<String, Object> deleteBoard( @PathVariable("num") int num ){
        HashMap<String, Object> map = new  HashMap<String, Object>();
        bs.deleteBoard(num);
        map.put("msg", "OK");
        return map;
    }



    @PostMapping("/updateBoard")
    public HashMap<String, Object> updateBoard( @RequestBody Board board){
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> res = bs.getBoard( board.getNum() );
        Board bdto = (Board) res.get("board");
        if( !bdto.getPass().equals( board.getPass() ) ){
            map.put("msg", "notOK");
        }else{
            bs.updateBoard( board );
            map.put("msg", "OK");
        }
        return map;
    }

}

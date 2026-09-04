package com.fastcam.board.controller;

import com.fastcam.board.entity.Reply;
import com.fastcam.board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    ReplyService rs;

    @GetMapping("/getReply/{num}")
    public HashMap<String, Object> getReply(@PathVariable("num") int num ){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Reply> list  =  rs.getReply( num );
        System.out.println("list size : " + list.size());
        map.put("replyList", rs.getReply( num ) );
        return map;
    }

    @PostMapping("/addReply")
    public HashMap<String, Object> addReply( @RequestBody Reply reply){
        HashMap<String, Object> map = new HashMap<String, Object>();
        rs.addReply( reply );
        map.put("msg", "OK");
        return map;
    }


    @DeleteMapping("/deleteReply/{replynum}")
    public HashMap<String, Object> deleteReply( @PathVariable("replynum") int replynum){
        HashMap<String, Object> map = new HashMap<String, Object>();
        rs.deleteReply( replynum );
        map.put("msg", "OK");
        return map;
    }


}

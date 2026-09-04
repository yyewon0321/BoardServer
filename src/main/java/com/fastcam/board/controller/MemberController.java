package com.fastcam.board.controller;

import com.fastcam.board.entity.Member;
import com.fastcam.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService ms;

    @GetMapping
    public String index(){
        return "<h1>Board Server Test!!</h1>";
    }

    @PostMapping("/idcheck")
    public HashMap<String, Object> idcheck( @RequestParam("userid") String userid ){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // System.out.println("userid : " + userid);
        Member mem = ms.getMember(userid);
        if( mem == null)
            map.put("msg", "OK");
        else
            map.put("msg", "NOTOK");

        return map;
    }


    @PostMapping("/join")
    public HashMap<String,Object> join(@RequestBody Member member ) {
        HashMap<String,Object> result = new HashMap<>();
        ms.insertMember( member );
        result.put("msg", "OK");
        return result;
    }

    @PostMapping("/login")
    public HashMap<String, Object> login( @RequestBody Member member ){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Member mem = ms.getMember( member.getUserid() );
        if( mem == null)
            map.put("message", "아이디 패스워드를 확인하세요");
        else if( !mem.getPwd().equals( member.getPwd() ) )
            map.put("message", "아이디 패스워드를 확인하세요");
        else{
            map.put("message", "OK");
            map.put("loginUser", mem);
        }
        return map;
    }

    @PostMapping("/deleteMemeber")
    public HashMap<String, Object> deleteMemeber( @RequestParam("userid") String userid ){
        HashMap<String, Object> map = new  HashMap<String, Object>();
        ms.deleteMember( userid );
        map.put("msg", "OK");
        return map;
    }


    @PostMapping("/updateMember")
    public HashMap<String, Object> updateMEmber( @RequestBody Member member){
        HashMap<String, Object> map = new  HashMap<String, Object>();
        ms.updateMember(member);
        map.put("msg", "OK");
        return map;
    }

}

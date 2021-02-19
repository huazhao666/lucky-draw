package com.huazhao.controller;

import com.huazhao.model.Member;
import com.huazhao.model.User;
import com.huazhao.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-02
 * Time : 12:12
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @PostMapping("/add")
    public Object add(@RequestBody Member member, HttpSession session){
        User user = (User) session.getAttribute("user");
        int n = memberService.add(member,user.getId());
        return member.getId();
    }
    @PostMapping("/update")
    public Object update(@RequestBody Member member){
        int n = memberService.update(member);
        return null;
    }
    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable Integer id){

        return memberService.delete(id);
    }

}

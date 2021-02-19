package com.huazhao.controller;

import com.huazhao.model.User;
import com.huazhao.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-02
 * Time : 12:12
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/add/{awardId}")
    public Object add(@RequestBody List<Integer>memberIds,@PathVariable Integer awardId){
        int n = recordService.add(memberIds,awardId);
        return null;//前端不需要id;
    }

    @GetMapping("delete/member")
    public Object deleteByMemberId(Integer id){
        int n =  recordService.deleteByMemberId(id);
        return null;

    }
    @GetMapping("delete/award")
    public Object deleteByAwardId(Integer id){
        int n = recordService.deleteByAwardId(id);
        return null;
    }
    @GetMapping("/delete/setting")
    public Object deleteBySetting(HttpSession session){
        User user = (User) session.getAttribute("user");
        //获取userid -- 关联
        int n = recordService.deleteByUserId(user.getId());
        return null;
    }

}

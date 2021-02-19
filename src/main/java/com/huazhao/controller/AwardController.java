package com.huazhao.controller;

import com.huazhao.model.Award;
import com.huazhao.model.User;
import com.huazhao.service.AwardService;
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
@RequestMapping("/award")
public class AwardController {
    @Autowired
    private AwardService awardService;

    @PostMapping("/add")
    public Object add(@RequestBody Award award, HttpSession session){ //json格式
        User user = (User) session.getAttribute("user");
        int n = awardService.add(award,user.getId());
        return award.getId(); //插入后返回前端自增主键：
    }
    @PostMapping("/update")
    public Object update(@RequestBody Award award){
        int n = awardService.update(award);
        return null;
    }
    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable Integer id){
        int n = awardService.delete(id);
        return null;
    }
}

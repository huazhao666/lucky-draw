package com.huazhao.controller;

import com.huazhao.model.Award;
import com.huazhao.model.Member;
import com.huazhao.model.Setting;
import com.huazhao.model.User;
import com.huazhao.service.AwardService;
import com.huazhao.service.MemberService;
import com.huazhao.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private MemberService memberService;

    /**
     * 进入抽奖设置页面，初始化接口，返回页面所需要的数据；
     * /setting对象中得属性 batchNumber
     * 目前没有的属性：
     * 1.user（用户信息）
     * awards(根据setting-id)，
     * member（setting-id）
     */
    @GetMapping("/query")
    public Object query(HttpSession session) { //此时已经登录了，所以可以直接使用session;

        //获取session的user；
        User user = (User) session.getAttribute("user");
        //根据userId查setting
        Setting setting = settingService.queryByUserId(user.getId());
        //把user设置到setting新增属性中；
        setting.setUser(user);
        //根据setting-id查award列表，设置setting新增属性到awards；
        List<Award> awards = awardService.queryByUserId(setting.getId());
        setting.setAwards(awards);
        //根据setting-id查member列表，设置setting新增属性到member；
        List<Member> members = memberService.queryByUserId(setting.getId());
        setting.setMembers(members);

        return setting;
    }

    @GetMapping("/update")
    public Object update(Integer batchNumber,HttpSession session){
        User user = (User) session.getAttribute("user");
        int n = settingService.update(batchNumber,user.getId());
        return null;

    }

}

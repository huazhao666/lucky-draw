package com.huazhao.service;

import com.huazhao.exception.AppException;
import com.huazhao.mapper.SettingMapper;
import com.huazhao.mapper.UserMapper;
import com.huazhao.model.Setting;
import com.huazhao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-02
 * Time : 12:08
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SettingMapper settingMapper;

    private static final  DateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");

    @Value("${user.head.local-path}")
    private String headLocalPath;

    @Value("${user.head.remote-path}")
    private String headRemotePath;

    public String saveHead(MultipartFile headFile) {
        Date now = new Date();
        String dirUri = "/" + dataFormat.format(now);
        File dir = new File(headLocalPath+dirUri);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //保存在本地以天为单位的文件夹，随机字符串为文件名，但是要保留文件后缀；
        String suffix  =  headFile.getOriginalFilename().
                substring(headFile.getOriginalFilename().lastIndexOf(".")); // 文件后缀；
        String headName = UUID.randomUUID().toString() + suffix; //随机名字
        String uri = dirUri + "/" + headName;//文件夹名字+随即名字；
        try {
            headFile.transferTo(new File(headLocalPath + uri));
        } catch (IOException e) {
            throw  new AppException("REG001","上传用户头像出错");
        }
        return headRemotePath+uri;

    }
    //多个更新，有部分查询+更新有时候也需要；多个更新必须；
    //内部实现：aop，方法执行前加入事务逻辑，方法执行后，抛异常rollback，没有异常com
    //禁止使用try catch吃异常；
    @Transactional //可以手动指定隔离级别和传播特性；
    public void register(User user) {
        //校验用户名不能重复;
        //插入user数据；插入后自增主键会按照useGeneratedKeys=true设置到对象属性；
        int n = userMapper.insertSelective(user);
        //插入setting数据：登陆后，进入设置页面，添加奖项和抽奖人员，需要setting-id；
        Setting setting = new Setting();
        setting.setUserId(user.getId());
        setting.setBatchNumber(8);//每次抽奖得数量:设置一个默认值，是8；
        settingMapper.insertSelective(setting);
    }

    public User queryUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}

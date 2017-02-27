package main.test;

import main.com.sun.h5weixin.user.model.User;
import main.com.sun.h5weixin.user.service.impl.UserServiceImpl;

/**
 * Created by SUN on 2017/2/16.
 */
public class testMain {
    public static void main(String[] args)
    {
        System.out.println("main");
//        User user = new User();
//        user.setOpenid("1111");
//        user.setNickname("苏");
//        user.setMobile("1342883612");
//
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.addUser(user);
        testMain test = new testMain();
//        test.findUserByOpenid();
        test.modifyUser();
//        test.addUser();
//        System.out.println("main:" + IsMobile.isMobileNo(""));
    }

    public void addUser()
    {
        User user = new User();
        user.setMobile("987");
        user.setInviteNumber(2);

        UserServiceImpl userService = new UserServiceImpl();
        userService.addUser(user);
    }

    public void findUserByOpenid()
    {
        String mobile = "987";
        UserServiceImpl userService = new UserServiceImpl();
        User user =  userService.findUserByMobile(mobile);
        System.out.println(user.getMobile() + "\t" + user.getId());

    }

    public void modifyUser()
    {
        String mobile = "123";
        UserServiceImpl userService = new UserServiceImpl();
        User user =  userService.findUserByMobile(mobile);
        user.setpMobile("222");

        userService.modifyUser(user);
        System.out.println(user.getpMobile());

    }
}
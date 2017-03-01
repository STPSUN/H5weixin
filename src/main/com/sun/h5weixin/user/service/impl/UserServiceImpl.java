package main.com.sun.h5weixin.user.service.impl;

import main.com.sun.h5weixin.user.dao.impl.UserImpl;
import main.com.sun.h5weixin.user.model.User;

import java.util.List;

/**
 * Created by SUN on 2017/2/16.
 */
public class UserServiceImpl {
    private UserImpl userImpl = new UserImpl();
    public void addUser(User user)
    {
        userImpl.addUser(user);
    }

    public User findUserByMobile(String mobile)
    {
        return userImpl.findUserByMobile(mobile);
    }

    public void modifyUser(User user)
    {
        userImpl.updateUser(user);
    }

    public List<User> findUserListByPMobile(String pMobile)
    {
        return  userImpl.findUserListByPMobile(pMobile);
    }
}

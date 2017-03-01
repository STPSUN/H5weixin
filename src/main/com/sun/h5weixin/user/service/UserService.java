package main.com.sun.h5weixin.user.service;

import main.com.sun.h5weixin.user.model.User;

import java.util.List;

/**
 * Created by SUN on 2017/2/16.
 */
public interface UserService {
    public void addUser(User user);

    public User findUserByMobile(String mobile);

    public void modifyUser(User user);

    public List<User> findUserListByPMobile(String pMobile);

}

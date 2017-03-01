package main.com.sun.h5weixin.user.dao;

import main.com.sun.h5weixin.user.model.User;

import java.util.List;

/**
 * Created by SUN on 2017/2/16.
 */
public interface UserDao {

    public boolean addUser(User user);

    public User findUserByMobile(String mobile);

    public void updateUser(User user);

    public List<User> findUserListByPMobile(String pMobile);

}

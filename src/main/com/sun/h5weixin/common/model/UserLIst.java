package main.com.sun.h5weixin.common.model;

import main.com.sun.h5weixin.user.model.User;

import java.util.List;

/**
 * Created by SUN on 2017/3/1.
 */
public class UserLIst {
    private int success;
    private List<User> userList;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

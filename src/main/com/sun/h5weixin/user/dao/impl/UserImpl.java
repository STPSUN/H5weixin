package main.com.sun.h5weixin.user.dao.impl;

import main.com.sun.h5weixin.tools.ConnectionJdbc;
import main.com.sun.h5weixin.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/2/16.
 */
public class UserImpl {
    public boolean addUser(User user)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "insert into user(mobile, certificationStatus, pMobile, awardStatus, " +
                "inviteNumber, award, province, city, awardTotal) values(?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getMobile());
            ps.setString(2, user.getCertificationStatus());
            ps.setString(3, user.getpMobile());
            ps.setString(4, user.getAwardStatus());
            ps.setInt(5, user.getInviteNumber());
            ps.setString(6, user.getAward());
            ps.setString(7, user.getProvince());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getAwardTotal());


            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return  result;
    }

    public User findUserByMobile(String mobile)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from user where mobile = ? ";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, mobile);
            rs = ps.executeQuery();
            while (rs.next())
            {
                user = new User();
                user.setMobile(mobile);
                user.setId(rs.getInt("id"));
                user.setCertificationStatus(rs.getString("certificationStatus"));
                user.setpMobile(rs.getString("pMobile"));
                user.setAwardStatus(rs.getString("awardStatus"));
                user.setInviteNumber(rs.getInt("inviteNumber"));
                user.setAward(rs.getString("award"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
                user.setAwardTotal("arardTotal");
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    public void updateUser(User user)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "update user set certificationStatus=?, pMobile=?, awardStatus=?," +
                "inviteNumber=?, award=?, province=?, city=?, awardTotal=? where mobile=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getCertificationStatus());
            ps.setString(2, user.getpMobile());
            ps.setString(3, user.getAwardStatus());
            ps.setInt(4, user.getInviteNumber());
            ps.setString(5, user.getAward());
            ps.setString(6, user.getProvince());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getAwardTotal());
            ps.setString(9, user.getMobile());

            ps.executeUpdate();
            ps.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<User> findUserListByPMobile(String pMobile)
    {
        List<User> userList = new ArrayList<User>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from user where pMobile = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, pMobile);
            rs = ps.executeQuery();
            while (rs.next())
            {
                User user = new User();
                user.setpMobile(pMobile);
                user.setId(rs.getInt("id"));
                user.setMobile(rs.getString("mobile"));
                user.setCertificationStatus(rs.getString("certificationStatus"));
                user.setAwardStatus(rs.getString("awardStatus"));
                user.setInviteNumber(rs.getInt("inviteNumber"));
                user.setAward(rs.getString("award"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
                user.setAwardTotal(rs.getString("awardTotal"));
                userList.add(user);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

}

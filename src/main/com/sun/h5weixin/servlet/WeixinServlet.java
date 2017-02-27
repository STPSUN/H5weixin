package main.com.sun.h5weixin.servlet;

import main.com.sun.h5weixin.user.model.User;
import main.com.sun.h5weixin.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

//import net.sf.json.JSONObject;

/**
 * Created by SUN on 2017/2/15.
 */
@WebServlet(name = "WeixinServlet")
public class WeixinServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "index";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }

        if("index".equals(action))
        {
            toIndex(request, response);
        }else if("getUserInfo".equals(action))
        {
            doGetUserInfo(request, response);
        }else if("down".equals(action))
        {
            doDown(request, response);
        }else if("getUserInfoMobile".equals(action))
        {
            doGetUsserInfoMobile(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doGetUsserInfoMobile(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {
        String mobile = request.getParameter("mobile");
        PrintWriter out = response.getWriter();
        int success = 0;
        if(mobile != null)
        {
            User user = userService.findUserByMobile(mobile);
            if(user != null)
            {
                out.println(success);
            }else
            {
                User newUser = new User();
                newUser.setMobile(mobile);
                newUser.setInviteNumber(0);
                userService.addUser(newUser);

                success = 1;
                out.println(success);
            }
        }
    }

    private void toIndex(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {


//        String userAgent = request.getHeader("user-agent");
//        if(userAgent.contains("iPhone") || userAgent.contains("Android") || userAgent.contains("iPad"))
//        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }else
//        {
//            request.getRequestDispatcher("pc.jsp").forward(request, response);
//        }

//        String code = request.getParameter("code");
//        String appid = "wx5511a8138f126126";
//        String secret = "1be98216a2c85ace5d4c7130269222c3";
//        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
//        //第一次请求 获取access_token 和 openid
//        String  oppid = new HttpRequestor().doGet(requestUrl);
//
//        JSONObject oppidObj =JSONObject.fromObject(oppid);
//        String access_token = (String) oppidObj.get("access_token");
//        String openid = (String) oppidObj.get("openid");
//        String requestUrl2 = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
//        String userInfoStr = new HttpRequestor().doGet(requestUrl2);
//        JSONObject wxUserInfo =JSONObject.fromObject(userInfoStr);

//        if (openid != null)
//        {
//            User user = new User();
//            user.setOpenid(openid);
//            user.setStatus(0);

//            UserServiceImpl userService = new UserServiceImpl();
//            userService.addUser(user);
//            request.setAttribute("openid", openid);

//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
//        else
//        {
//            request.getRequestDispatcher("WEB-INF/pages/false.jsp").forward(request, response);
//        }
    }

    private void doGetUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mobile = request.getParameter("mobile");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        PrintWriter out = response.getWriter();
        int success = 0;
        if(mobile != null)
        {
            User user = userService.findUserByMobile(mobile);
            if(user != null)
            {
                out.println(success);
            }else
            {
                User newUser = new User();
                newUser.setMobile(mobile);
                newUser.setInviteNumber(0);
                newUser.setProvince(province);
                newUser.setCity(city);
                userService.addUser(newUser);

                success = 1;
                out.println(success);
            }
        }
    }

    private void doDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int link = 0;
        String userAgent = request.getHeader("user-agent");
        System.out.println(userAgent);
        if(userAgent.contains("iPhone"))
        {
            link = 1;
        }else if(userAgent.contains("Android"))
        {
            link = 2;
        }else
        {
            link = 3;
        }

        PrintWriter out = response.getWriter();
        out.println(link);
        System.out.println("link:" + link);

//        Enumeration names = request.getHeaderNames();
//        while (names.hasMoreElements())
//        {
//            String name = "name test";
//            name = (String)names.nextElement();
//            request.setAttribute("name", name);
//            if(request.getHeader(name).contains("iPhone"))
//            {
//                url = "http://baidu.com";
//                break;
//            }
//
//            if(request.getHeader(name).contains("android"))
//            {
//                url = "http://weixin.qq.com";
//                break;
//            }
//        }
    }

    private String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }

}

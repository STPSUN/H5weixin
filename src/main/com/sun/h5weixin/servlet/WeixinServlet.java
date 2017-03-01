package main.com.sun.h5weixin.servlet;

import main.com.sun.h5weixin.common.model.Refresh;
import main.com.sun.h5weixin.common.model.UserLIst;
import main.com.sun.h5weixin.user.model.User;
import main.com.sun.h5weixin.user.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


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
            doGetUserInfoMobile(request, response);
        }else if("authCode".equals(action))
        {
            doSendAuthCode(request, response);
        }else if("refresh".equals(action))
        {
            doRefresh(request, response);
        }else if("getOperate".equals(action))
        {
            doGetOperate(request, response);
        }else if("refresh2".equals(action))
        {
            doRefresh2(request, response);
        }else if("getUserInfoArea".equals(action))
        {
            doGetUserInfoArea(request, response);
        }

        if("getData".equals(action))
        {
            doGetData(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doGetData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("doGetData");
        String mobile = (String)request.getSession().getAttribute("mobile");
//        System.out.println("mobile:" + mobile);
        UserLIst userLIst = new UserLIst();
        userLIst.setSuccess(0);
        PrintWriter out = response.getWriter();

        if(mobile != null && !mobile.equals(""))
        {
            System.out.println("into if");
            List<User> users = new ArrayList<User>();
            users = userService.findUserListByPMobile(mobile);
            userLIst.setUserList(users);
            userLIst.setSuccess(1);

            JSONObject json = JSONObject.fromObject(userLIst);
            out.println(json);
        }else
        {
            JSONObject json = JSONObject.fromObject(userLIst);
            out.println(json);
        }
    }

    private void doGetUserInfoArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mobile = (String) request.getSession().getAttribute("mobile");
        System.out.println(mobile);
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        PrintWriter out = response.getWriter();
        Refresh refresh = new Refresh();
        refresh.setSuccess(0);

        if(mobile != null)
        {
            User user = userService.findUserByMobile(mobile);
            if(user != null)
            {
                User newUser = user;
                newUser.setInviteNumber(0);
                newUser.setProvince(province);
                newUser.setCity(city);
                userService.modifyUser(newUser);
//                userService.addUser(newUser);

                refresh.setSuccess(1);
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);
            }else
            {
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);
            }
//            else
//            {
//                User newUser = new User();
//                newUser.setMobile(mobile);
//                newUser.setInviteNumber(0);
//                newUser.setProvince(province);
//                newUser.setCity(city);
//                userService.addUser(newUser);
//
//                refresh.setSuccess(1);
//                refresh.setMobile(mobile);
//                JSONObject data = JSONObject.fromObject(refresh);
//                out.println(data);
//            }
        }
    }

    private void doRefresh2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        int operate2= 2;
//        Cookie cookie = new Cookie("operate2", operate2);
//        response.addCookie(cookie);
////        request.getSession().setAttribute("operate2", operate2);
//        int operate2 = 0;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null)
//        {
//            for(Cookie c : cookies)
//            {
//                if(c.getValue().equals("2"))
//                {
//                    operate2 = 2;
//                    break;
//                }
////                else if(c.getValue().equals("2"))
////                {
////                    operate = 2;
////                    break;
////                }
//            }
//        }else
//        {
//            operate2 = 2;
//            Cookie cookie = new Cookie("operate2", operate2);
//            response.addCookie(cookie);
//        }
////        String operate = "1";
////        Cookie cookie = new Cookie("operate", operate);
////        response.addCookie(cookie);
//        int operate2 = 0;
//        PrintWriter out = response.getWriter();
//        out.println(operate2);

        String pMobile = request.getParameter("pMobile");
        if(pMobile != null)
        {
            request.setAttribute("pMobile", pMobile);
        }

        int operate2 = 0;
        HttpSession session = request.getSession();
//        String sessionID = session.getId();
//        String seesionValue = (String)session.getAttribute("operate2");
        if(session.isNew())
        {

        }else
        {
            operate2 = 2;
        }
//        request.setAttribute("operate2", operate2);
//        System.out.println(sessionID);
        session.setAttribute("operate2", operate2);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void doGetOperate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int refresh = 1;
        PrintWriter out = response.getWriter();
        out.println(refresh);
    }

    private void doRefresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        int num = 11111111;
//        System.out.println(num);

//        int refresh = 1;
//        request.setAttribute("refresh", refresh);
//        PrintWriter out = response.getWriter();
//        out.println(refresh);
//        String time = request.getParameter("time");
//        System.out.println("time:" + time);
//        String mobile = request.getParameter("mobile");
//        if(time != null)
//        {
//            if(time.equals("1"))
//            {
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                System.out.println("into if");
//            }else
//            {
//                System.out.println("into else");
//                int refresh = 1;
////        request.setAttribute("refresh", refresh);
//                PrintWriter out = response.getWriter();
//                out.println(refresh);
//            }
//        }
//        System.out.println("new index");
//        System.out.println("mobile:" + mobile);
//        int refresh = 1;
//        PrintWriter out = response.getWriter();
//        out.println(refresh);
//        int refresh = 1;
//        request.setAttribute("refresh", refresh);
//        int operate = 1;
//        request.getSession().setAttribute("operate", operate);
//        String operate = "0";
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null)
//        {
//            for(Cookie c : cookies)
//            {
//                if(c.getValue().equals("1"))
//                {
//                    operate = "1";
//                    break;
//                }
//                else if(c.getValue().equals("2"))
//                {
//                    operate = 2;
//                    break;
//                }
//            }
//        }else
//        {
//            operate = "1";
//            Cookie cookie = new Cookie("operate", operate);
//            response.addCookie(cookie);
//        }
//        String operate = "1";
//        Cookie cookie = new Cookie("operate", operate);
//        response.addCookie(cookie);
//        PrintWriter out = response.getWriter();
//        out.println(operate);

        String pMobile = request.getParameter("pMobile");
        if(pMobile != null)
        {
            request.setAttribute("pMobile", pMobile);
        }

        int operate = 0;
        HttpSession session = request.getSession();
        if(session.isNew())
        {

        }else
        {
            operate = 1;
        }

//        request.getSession().setAttribute("operate", operate);
//        request.setAttribute("operate", operate);
        session.setAttribute("operate", operate);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void doSendAuthCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        String authCode = request.getParameter("authCode");
        int authCode = 0000;
        request.getSession().setAttribute("authCode", authCode);
    }

    private void doGetUserInfoMobile(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {
        String pMobile = request.getParameter("pMobile");
        String mobile = request.getParameter("mobile");
        System.out.println("mobile:" + mobile);
        PrintWriter out = response.getWriter();
        Refresh refresh = new Refresh();
        refresh.setSuccess(0);

        if(mobile != null && !mobile.equals(""))
        {
            HttpSession session = request.getSession();
            session.setAttribute("mobile", mobile);
            User user = userService.findUserByMobile(mobile);
            if(user != null)
            {
                refresh.setMobile(mobile);
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);

            }else
            {
                User newUser = new User();
                newUser.setMobile(mobile);
                newUser.setInviteNumber(0);
                newUser.setpMobile(pMobile);
                userService.addUser(newUser);

                request.getSession().setAttribute("mobile", mobile);

                refresh.setSuccess(1);
                refresh.setMobile(mobile);
                JSONObject data = JSONObject.fromObject(refresh);

                out.println(data);
            }
        }else
        {
            System.out.println("else");
            JSONObject data = JSONObject.fromObject(refresh);
            out.println(data);
        }

//        System.out.println("doGetData");
//        String mobile = request.getParameter("mobile");
//        System.out.println("mobile:" + mobile);
//        if(mobile != null && !mobile.equals(""))
//        {
//            System.out.println("into if");
//            List<User> users = new ArrayList<User>();
//            users = userService.findUserListByPMobile(mobile);
//            UserLIst userLIst = new UserLIst();
//            userLIst.setUserList(users);
//            userLIst.setSuccess(1);
//
//            JSONObject json = JSONObject.fromObject(userLIst);
//            PrintWriter out = response.getWriter();
//            out.println(json);
//        }
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
        String pMobile = request.getParameter("pMobile");
        String mobile = request.getParameter("mobile");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        PrintWriter out = response.getWriter();
        Refresh refresh = new Refresh();
        refresh.setSuccess(0);

        if(mobile != null)
        {
            User user = userService.findUserByMobile(mobile);
            if(user != null)
            {
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);
            }else
            {
                User newUser = new User();
                newUser.setMobile(mobile);
                newUser.setInviteNumber(0);
                newUser.setProvince(province);
                newUser.setCity(city);
                newUser.setpMobile(pMobile);
                userService.addUser(newUser);

//                int operate = 1;
//                request.getSession().setAttribute("operate", operate);

                refresh.setSuccess(1);
                refresh.setMobile(mobile);
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);
            }
        }
    }

    private void doDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int link = 0;
        String userAgent = request.getHeader("user-agent");
//        System.out.println(userAgent);
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

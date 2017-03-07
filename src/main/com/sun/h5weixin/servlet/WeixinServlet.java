package main.com.sun.h5weixin.servlet;

import main.com.sun.h5weixin.common.SMSUtils;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
        if("sendCode".equals(action))
        {
            doSendCode(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doSendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        System.out.println("into doSendCode");
        String mobile = request.getParameter("mobile");
//        System.out.println(mobile);
        if(mobile != null && !mobile.equals(""))
        {
//            System.out.println("into if");
            int result = 0;
            PrintWriter out = response.getWriter();
            String code = createVerificationCode();
//            System.out.println("code:" + code);
            if(!code.isEmpty())
            {
                try
                {
//                    System.out.println("into try");
                    SMSUtils smsUtils = new SMSUtils();
                    smsUtils.sendCheckCodeSMS(code, mobile);
                    System.out.println("sms_send ok");

                    HttpSession session = request.getSession();
                    session.setAttribute("code", code);
                    session.setAttribute("codeMobile", mobile);

                    result = 1;
                    out.println(result);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }else
            {
                out.println(result);
            }
        }

    }

    private void doGetData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mobile = (String)request.getSession().getAttribute("mobile");
//        System.out.println("mobile:" + mobile);
        UserLIst userLIst = new UserLIst();
        userLIst.setSuccess(0);
        PrintWriter out = response.getWriter();

        if(mobile != null && !mobile.equals(""))
        {
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
        }
    }

    private void doRefresh2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pMobile = request.getParameter("pMobile");
        if(pMobile != null)
        {
            request.setAttribute("pMobile", pMobile);
        }

//        int operate2 = 0;
//        HttpSession session = request.getSession();
//        String sessionID = session.getId();
//        String seesionValue = (String)session.getAttribute("operate2");
//        if(session.isNew())
//        {
//
//        }else
//        {
//            operate2 = 2;
//        }
//        request.setAttribute("operate2", operate2);
//        System.out.println(sessionID);
//        session.setAttribute("operate2", operate2);
        openType(request, response);
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void doGetOperate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int refresh = 1;
        PrintWriter out = response.getWriter();
        out.println(refresh);
    }

    private void doRefresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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

        session.setAttribute("operate", operate);

        String userAgent = request.getHeader("user-agent");
//        System.out.println(userAgent);
        openType(request, response);

    }

    private void doGetUserInfoMobile(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {
//        System.out.println("into doGetUserInfoMobile");
        String code = request.getParameter("code");
        String pMobile = request.getParameter("pMobile");
        String mobile = request.getParameter("mobile");
//        System.out.println("mobile:" + mobile);
        PrintWriter out = response.getWriter();
        Refresh refresh = new Refresh();
        refresh.setSuccess(0);
//        refresh.setCode("0");

//        System.out.println("code:" + code);

        if(mobile != null && !mobile.equals("") && !code.isEmpty())
        {
            HttpSession session = request.getSession();
            if(code.equals(session.getAttribute("code")) && mobile.equals(session.getAttribute("codeMobile")))
            {
                session.setAttribute("mobile", mobile);
                User user = userService.findUserByMobile(mobile);
                if(user != null)
                {
                    refresh.setMobile(mobile);
                    refresh.setCode("1");
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
                    refresh.setCode("1");
                    JSONObject data = JSONObject.fromObject(refresh);

                    out.println(data);
                }
            }else
            {
                refresh.setCode("0");
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);
            }

        }else
        {
            JSONObject data = JSONObject.fromObject(refresh);
            out.println(data);
        }
    }

    private void toIndex(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {
        openType(request, response);
    }

    private void doGetUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String code = request.getParameter("code");
        String pMobile = request.getParameter("pMobile");
        String mobile = request.getParameter("mobile");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        PrintWriter out = response.getWriter();
        Refresh refresh = new Refresh();
        refresh.setSuccess(00);
        refresh.setCode("0");

        if(!mobile.isEmpty() && !code.isEmpty())
        {
            HttpSession session = request.getSession();
//            User user1 = userService.findUserByMobile((String)session.getAttribute("codeMobile"));
//            if(user1 != null)
//            {
//                refresh.setSuccess(0);
//                JSONObject data = JSONObject.fromObject(refresh);
//            }

            if(code.equals(session.getAttribute("code")) && mobile.equals(session.getAttribute("codeMobile")))
            {
                refresh.setCode("1");
                User user = userService.findUserByMobile(mobile);
                if(user != null)
                {
                    refresh.setSuccess(0);
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

                    refresh.setSuccess(1);
                    refresh.setMobile(mobile);
                    JSONObject data = JSONObject.fromObject(refresh);
                    out.println(data);
                }
            }else
            {
                refresh.setCode("0");
                refresh.setSuccess(1);
                JSONObject data = JSONObject.fromObject(refresh);
                out.println(data);
            }

        }else
        {
            refresh.setSuccess(00);
            JSONObject data = JSONObject.fromObject(refresh);
            out.println(data);
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

    }

    private String createVerificationCode()
    {
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 6; i++)
        {
            result += random.nextInt(10);
        }

        return result;
    }

    private void openType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userAgent = request.getHeader("user-agent");
//        System.out.println(userAgent);
        if(userAgent.contains("iPhone") || userAgent.contains("Android"))
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else
        {
            request.getRequestDispatcher("pc.jsp").forward(request, response);
        }
    }

}

package service;

import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int state = -1;
        String msg = "";
        //1.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserInfoDao userInfoDao = new UserInfoDao();
        if(username!=null && password != null){
            //2.调用数据库验证
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            try {
//                state = userInfoDao.isLogin(userInfo)?200:100;
                userInfo = userInfoDao.getUserInfo(userInfo);
                if(userInfo.getId()>=1){
                    //表示登陆成功
                    state = 200;
                    //如果登陆成功，需要创建一个session信息
                    HttpSession session = request.getSession();
                    //将当前登录信息放在session中
                    session.setAttribute("userinfo",userInfo);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "操作失败，参数不全!";
        }

        //3.将信息返回给前端
        HashMap<String,Object> result = new HashMap<>();
        result.put("state",state);
        result.put("msg",msg);
        ResultJSONUtils.writeMap(response,result);
    }
}

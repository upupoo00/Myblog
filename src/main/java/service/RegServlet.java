package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int state = -1; //200表示接口执行成功
        String msg = "";
        //接收前端参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || password == null){
            //参数不正确
            msg = "参数不正确";
        } else {
            //操作数据库进行插入操作
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            //调用操作数据库的方法
            UserInfoDao userInfoDao = new UserInfoDao();
            boolean res = false;
            try {
                res = userInfoDao.add(userInfo);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(res){
                //操作数据库成功
                state = 200;
            }else {
                state = 100;
            }
        }
        HashMap<String,Object> result = new HashMap<>();
        result.put("state",state);
        result.put("msg",msg);
        ObjectMapper mapper = new ObjectMapper();
        ResultJSONUtils.write(response,mapper.writeValueAsString(result));
//        {"state":100,"msg":"msg"}
//        response.getWriter().write("{\"state\":"+state
//                +",\"msg\":\""+msg+"\"}");
    }
}

package service;

import dao.ArticleInfoDao;
import models.ArticleInfo;
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
import java.util.List;

public class MyArtListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int state = -1;
        String msg = "";
        List<ArticleInfo> list = null;
        // 1.权限验证 -> uid
        HttpSession session = request.getSession(false);
        if (session == null ||
                session.getAttribute("userinfo") == null) {
            // 用户为登录
            state = 100;
            msg = "当前用户未登录";
        } else {
            // 查询个人的文章列表
            UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
            int uid = userInfo.getId();
            // 2.去数据库查询列表信息
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                // 查询数据库（我的文章）
                list = articleInfoDao.getMyArtList(uid);
                state = 200;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        // 3.返回信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("state", state);
        map.put("msg", msg);
        map.put("list", list);
        ResultJSONUtils.writeMap(response, map);
    }
}
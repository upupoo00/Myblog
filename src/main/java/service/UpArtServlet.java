package service;

import dao.ArticleInfoDao;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class UpArtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        // 1.获得前端参数
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int id = Integer.parseInt(request.getParameter("id"));
        if (title != null && content != null &&
                !title.equals("") && !content.equals("")
                && id > 0) {
            // 去 session 中获取uid
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("userinfo") != null) {
                // 2.去数据库执行修改操作
                ArticleInfoDao articleInfoDao = new ArticleInfoDao();
                // 去数据库执行修改操作
                try {
                    succ = articleInfoDao.update(title, content, id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                msg = "非法操作，请先登录！";
            }
        } else {
            msg = "非法清操作，参数传递不全！";
        }
        // 3.返回结果给前端
        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        ResultJSONUtils.writeMap(response, result);
    }
}

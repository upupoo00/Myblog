package service;

import dao.ArticleInfoDao;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/upcount")
public class UpCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        int id = Integer.parseInt(req.getParameter("id"));
        if (id > 0) {
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                succ = articleInfoDao.upcount(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            // 非法参数
            msg = "非法参数";
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        ResultJSONUtils.writeMap(resp, result);
    }
}

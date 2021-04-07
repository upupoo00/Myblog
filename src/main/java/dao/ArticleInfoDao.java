package dao;

import models.ArticleInfo;
import models.vo.ArticleInfoVO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleInfoDao {

    // 获取文章列表
    public List<ArticleInfo> getMyArtList(int uid) throws SQLException {
        List<ArticleInfo> list = new ArrayList<>();
        Connection connection = DBUtils.getConnect();
        String sql = "select * from articleinfo where uid=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, uid);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setId(resultSet.getInt("id"));
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            articleInfo.setUpdatetime(resultSet.getDate("updatetime"));
            articleInfo.setRcount(resultSet.getInt("rcount"));
            list.add(articleInfo);
        }
        // 关闭连接
        DBUtils.close(connection, statement, resultSet);
        return list;
    }

    // 删除文章
    public int delArtileById(int id) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnect();
        String sql = "delete from articleinfo where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        // 真正的执行操作
        result = statement.executeUpdate();
        // 关闭数据库连接
        DBUtils.close(connection, statement, null);
        return result;
    }

    // 添加文章
    public int add(String title, String content, int uid) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnect();
        String sql = "insert into articleinfo(title,content,uid) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, content);
        statement.setInt(3, uid);
        // 执行sql
        result = statement.executeUpdate();
        DBUtils.close(connection, statement, null);
        return result;
    }

    // 文章修改
    public int update(String title, String content, int id) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnect();
        String sql = "update articleinfo set title=?,content=? where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, content);
        statement.setInt(3, id);
        result = statement.executeUpdate();
        DBUtils.close(connection, statement, null);
        return result;
    }

    // 文章查询方法
    public ArticleInfoVO getArtById(int id) throws SQLException {
        ArticleInfoVO articleInfo = new ArticleInfoVO();
        Connection connection = DBUtils.getConnect();
        String sql = "select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id where a.id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            articleInfo.setRcount(resultSet.getInt("rcount"));
            articleInfo.setUsername(resultSet.getString("username"));
        }
        DBUtils.close(connection, statement, resultSet);
        return articleInfo;
    }
    //获取所有人的文章列表
    public List<ArticleInfoVO> getList() throws SQLException {
        List<ArticleInfoVO> list = new ArrayList<>();
        String sql = "select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id";
        Connection connection = DBUtils.getConnect();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            ArticleInfoVO vo = new ArticleInfoVO();
            vo.setId(resultSet.getInt("id"));
            vo.setUsername(resultSet.getString("username"));
            vo.setTitle(resultSet.getString("title"));
            vo.setRcount(resultSet.getInt("rcount"));
            vo.setCreatetime(resultSet.getDate("createtime"));
            list.add(vo);
        }
        DBUtils.close(connection,statement,resultSet);
        return list;
    }
    //根据分页查询文章列表
    public List<ArticleInfoVO> getListBypage(int cpage, int psize) throws SQLException {
        List<ArticleInfoVO> list = new ArrayList<>();
        String sql = "select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id limit ?,?";
        Connection connection = DBUtils.getConnect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,(cpage-1)*psize);
        statement.setInt(2,psize);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            ArticleInfoVO vo = new ArticleInfoVO();
            vo.setId(resultSet.getInt("id"));
            vo.setUsername(resultSet.getString("username"));
            vo.setTitle(resultSet.getString("title"));
            vo.setRcount(resultSet.getInt("rcount"));
            vo.setCreatetime(resultSet.getDate("createtime"));
            list.add(vo);
        }
        DBUtils.close(connection,statement,resultSet);
        return list;
    }
    // 修改阅读量 +1
    public int upcount(int id) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnect();
        String sql = "update articleinfo set rcount=rcount+1 where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        result = statement.executeUpdate();
        DBUtils.close(connection, statement, null);
        return result;
    }
}
package cn.mgl.dao;

import cn.mgl.pojo.User;
import cn.mgl.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MongieLee
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int insert(User user) throws SQLException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "insert into t_user(username,balance) values(?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setBigDecimal(2, user.getBalance());
            count = ps.executeUpdate();
        } finally {
            DBUtil.close(null, ps, null);
        }
        return count;
    }

    @Override
    public int update(User user) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "update t_user set balance = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setBigDecimal(1, user.getBalance());
            ps.setInt(2, user.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "delete from t_user where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return count;
    }

    @Override
    public User selectById(Integer id) {
        PreparedStatement ps = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "select id,username,balance from t_user where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setBalance(resultSet.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, resultSet);
        }
        return user;
    }

    @Override
    public User selectByUsername(String from) {
        PreparedStatement ps = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "select id,username,balance from t_user where username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, from);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setBalance(resultSet.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, resultSet);
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "select id,username,balance from t_user";
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setBalance(resultSet.getBigDecimal("balance"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, resultSet);
        }
        return userList;
    }
}

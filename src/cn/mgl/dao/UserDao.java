package cn.mgl.dao;

import cn.mgl.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    int insert(User user) throws SQLException;

    int update(User user);

    int delete(Integer id);

    User selectById(Integer id);

    User selectByUsername(String username);

    List<User> selectAll();
}

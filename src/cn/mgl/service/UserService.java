package cn.mgl.service;

import cn.mgl.exception.ApplicationException;
import cn.mgl.exception.InsufficientBalanceException;
import cn.mgl.pojo.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author MongieLee
 * @version 1.0
 * @date 2022/9/20 15:42
 */
public interface UserService {
    void transfer(String from, String to, BigDecimal money) throws InsufficientBalanceException, ApplicationException;

    int saveUser(User user) throws SQLException;

    List<User> selectAll();
}

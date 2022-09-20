package cn.mgl.service.impl;

import cn.mgl.dao.UserDao;
import cn.mgl.dao.UserDaoImpl;
import cn.mgl.exception.ApplicationException;
import cn.mgl.exception.InsufficientBalanceException;
import cn.mgl.pojo.User;
import cn.mgl.service.UserService;
import cn.mgl.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author MongieLee
 * @version 1.0
 * @date 2022/9/20 15:42
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    public void transfer(String from, String to, BigDecimal money) throws InsufficientBalanceException, ApplicationException {
        try (Connection coon = DBUtil.getConnection()) {
            coon.setAutoCommit(false);
            User fromUser = userDao.selectByUsername(from);
            if (fromUser.getBalance().compareTo(money) < 0) {
                System.out.println("说明余额不足");
                throw new InsufficientBalanceException("余额不足转账");
            }
            User toUser = userDao.selectByUsername(to);

            fromUser.setBalance(fromUser.getBalance().subtract(money));

            toUser.setBalance(toUser.getBalance().add(money));

            int count = userDao.update(fromUser);
            count += userDao.update(toUser);
            if (count != 2) {
                throw new ApplicationException("转账异常");
            }
            coon.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveUser(User user) throws SQLException {
        try (Connection conn = DBUtil.getConnection()) {
            return userDao.insert(user);
        }
    }

    @Override
    public List<User> selectAll() {
        try (Connection conn = DBUtil.getConnection()) {
            return userDao.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

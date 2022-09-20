package cn.mgl.controller;

import cn.mgl.pojo.User;
import cn.mgl.service.UserService;
import cn.mgl.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @author MongieLee
 * @version 1.0
 * @date 2022/9/20 16:04
 */
@WebServlet("/createUser")
public class AddUser extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        User user = new User();
        user.setUsername(username);
        user.setBalance(BigDecimal.valueOf(0));
        PrintWriter writer = resp.getWriter();

        try {
            userService.saveUser(user);
            writer.println("{\"message\":\"请求成功\"}");
        } catch (Exception e) {
            writer.println("{\"message\":\"" + "服务器异常，" + e.getMessage() + "\"}");
        }
    }
}

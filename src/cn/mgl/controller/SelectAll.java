package cn.mgl.controller;

import cn.mgl.pojo.User;
import cn.mgl.service.UserService;
import cn.mgl.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author MongieLee
 * @version 1.0
 * @date 2022/9/20 16:31
 */
@WebServlet("/selectAll")
public class SelectAll extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
        PrintWriter writer = resp.getWriter();
        writer.println("请求成功，列表为:"+ users.toString());
    }
}

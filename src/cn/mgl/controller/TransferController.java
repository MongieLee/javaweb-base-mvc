package cn.mgl.controller;

import cn.mgl.exception.InsufficientBalanceException;
import cn.mgl.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/transfer")
public class TransferController extends HttpServlet {
    UserServiceImpl transferService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String balance = req.getParameter("balance");
        BigDecimal decimal = new BigDecimal(balance);

        PrintWriter writer = resp.getWriter();
        try {
            transferService.transfer(from, to, decimal);
            writer.println("{\"message\":\"请求成功\"}");
        } catch (InsufficientBalanceException e) {
            writer.println("{\"message\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            writer.println("{\"message\":\"" + "服务器异常" + e.getMessage() + "\"}");
        }
    }
}

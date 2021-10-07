package com.liu.web;

import com.google.gson.Gson;
import com.liu.pojo.Book;
import com.liu.pojo.Cart;
import com.liu.pojo.CartItem;
import com.liu.service.BookService;
import com.liu.service.impl.BookServiceImpl;
import com.liu.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author liuliang
 * @create2021-09-2021/9/5-12:26
 * @email 2640336916@qq.com
 * @explain
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();


    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        int count =  WEBUtils.parseInt(req.getParameter("count"),0);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
        }
        req.getSession().removeAttribute("lastAdd");
        System.out.println(cart);
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
            if (cart.getTotalCount() == 0 && req.getSession().getAttribute("lastAdd") != null){
                req.getSession().removeAttribute("lastAdd");
            }
        }

        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(item);
        req.getSession().setAttribute("lastAdd",book.getName());
        System.out.println(cart);
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(item);
        req.getSession().setAttribute("lastAdd",book.getName());
        System.out.println(cart);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("lastAdd",book.getName());
        resultMap.put("count",cart.getTotalCount());
        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJson);

    }
}

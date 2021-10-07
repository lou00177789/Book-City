package com.liu.web;


import com.liu.pojo.Book;
import com.liu.pojo.Page;
import com.liu.service.impl.BookServiceImpl;
import com.liu.utils.WEBUtils;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-14:25
 * @email 2640336916@qq.com
 * @explain
 */
public class BookServlet extends BaseServlet {
    protected BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WEBUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = WEBUtils.copyParamToBean(new Book(), parameterMap);
        String pageNo = req.getParameter("pageNo");
        System.out.println(book);
        bookService.addBook(book);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);


    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        String pageNo = req.getParameter("pageNo");
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();

        Book book = WEBUtils.copyParamToBean(new Book(), parameterMap);

        String pageNo = req.getParameter("pageNo");
        System.out.println(book);
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
//        pages/manager/book_edit.jsp
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int id = WEBUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
//        String pageNo = req.getParameter("pageNo");
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("*************");
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}

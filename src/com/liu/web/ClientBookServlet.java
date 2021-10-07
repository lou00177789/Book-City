package com.liu.web;

import com.liu.pojo.Book;
import com.liu.pojo.Page;
import com.liu.service.impl.BookServiceImpl;
import com.liu.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuliang
 * @create2021-09-2021/9/1-22:37
 * @email 2640336916@qq.com
 * @explain
 */
public class ClientBookServlet extends BaseServlet {

    protected BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("经过了ClientBookServlet");
        Integer pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);


    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("经过了ClientBookServlet_pageByPrice!");
        Integer pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,req.getParameter("min"),req.getParameter("max"));

        StringBuffer url = new StringBuffer("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min") != null){
            url.append("&min=").append(req.getParameter("min"));
        }

        if(req.getParameter("max") != null){
            url.append("&max=").append(req.getParameter("max"));
        }
        System.out.println(url.toString());
        page.setUrl(url.toString());
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);


    }


}

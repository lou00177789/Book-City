package com.liu.service.impl;

import com.liu.dao.impl.BookDaoImpl;
import com.liu.pojo.Book;
import com.liu.pojo.Page;
import com.liu.service.BookService;
import com.liu.utils.WEBUtils;

import java.util.List;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-14:01
 * @email 2640336916@qq.com
 * @explain
 */
public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {

        Page<Book> bookPage = new Page<>();

        bookPage.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        bookPage.setPageTotalCount(pageTotalCount);

        //总页数 = 记录总数/每页条数
        Integer pageTotal = pageTotalCount%pageSize != 0 ? pageTotalCount/pageSize + 1 : pageTotalCount/pageSize;
        bookPage.setPageTotal(pageTotal);


        bookPage.setPageNo(pageNo);
        //当前页数据
        Integer begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryPageItems(begin,pageSize);
        bookPage.setItems(items);

        return bookPage;
    }

    @Override
    public void addBook(Book book) {

        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {

        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {

        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, String min, String max) {
        Page<Book> bookPage = new Page<>();

        bookPage.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDao.queryForPageByPriceTotalCount(min,max);
        bookPage.setPageTotalCount(pageTotalCount);

        //总页数 = 记录总数/每页条数
        Integer pageTotal = pageTotalCount%pageSize != 0 ? pageTotalCount/pageSize + 1 : pageTotalCount/pageSize;
        bookPage.setPageTotal(pageTotal);


        bookPage.setPageNo(pageNo);
        //当前页数据
        Integer begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryPageByPriceItems(begin,pageSize,min,max);
        bookPage.setItems(items);

        return bookPage;
    }
}

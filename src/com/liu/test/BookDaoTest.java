package com.liu.test;

import com.liu.dao.impl.BookDaoImpl;
import com.liu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-13:26
 * @email 2640336916@qq.com
 * @explain
 */
public class BookDaoTest {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Test
    public void queryPageByPriceItems() {
        List list = bookDao.queryPageByPriceItems(3,5,"80","130");
        list.forEach(System.out::println);
    }
    @Test
    public void queryForPageByPriceTotalCount() {
        System.out.println(bookDao.queryForPageByPriceTotalCount("80", "130"));

    }
    @Test
    public void queryPageItems() {
        List list = bookDao.queryPageItems(4, 7);
        list.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void addBook() {
        Book book = new Book(null, "考研英语", "刘亮", new BigDecimal(160), 3000, 2000, null);
        int i = bookDao.addBook(book);
        System.out.println(i);
    }

    @Test
    public void deleteBookById() {
        int i = bookDao.deleteBookById(26);
        System.out.println(i);
    }


    @Test
    public void updateBook() {
        Book book = new Book(42, "考研英语", "刘亮", new BigDecimal(160), 2548, 2000, null);

        int i = bookDao.updateBook(book);
        System.out.println(i);
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(34);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }
}
package com.liu.test;


import com.liu.pojo.Book;
import com.liu.pojo.Page;
import com.liu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-14:07
 * @email 2640336916@qq.com
 * @explain
 */
public class BookServiceTest {


    private  BookServiceImpl bookService= new BookServiceImpl();

    @Test
    public void pageByPrice() {

        Page<Book> page = bookService.pageByPrice(1,4,"80","130");
        System.out.println(page);
        page.getItems().forEach(System.out::println);

    }


    @Test
    public void page() {

        Page<Book> page = bookService.page(1, 4);
        System.out.println(page);

    }
    @Test
    public void addBook() {
        Book book = new Book(42, "考研英语", "刘小亮", new BigDecimal(160), 2548, 2000, null);

        bookService.addBook(book );

    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(42);
    }

    @Test
    public void updateBook() {
        Book book = new Book(43, "考研英语", "刘小8亮", new BigDecimal(160), 2548, 2000, null);

        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(43);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }
}
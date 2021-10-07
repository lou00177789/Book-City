package com.liu.service;

import com.liu.pojo.Book;
import com.liu.pojo.Page;

import java.util.List;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-13:57
 * @email 2640336916@qq.com
 * @explain
 */
public interface BookService {
    public Page<Book> page(Integer pageNo, Integer pageSize);
    public void addBook(Book book);

    public void deleteBookById(int id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, String min, String max);
}

package com.liu.dao;

import com.liu.pojo.Book;

import java.util.List;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-12:50
 * @email 2640336916@qq.com
 * @explain
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List queryPageItems(Integer begin, Integer pageSize);

    public Integer queryForPageByPriceTotalCount(String min, String max);

    public List<Book> queryPageByPriceItems(Integer begin, Integer pageSize, String min, String max);
}

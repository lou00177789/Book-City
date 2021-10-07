package com.liu.dao.impl;

import com.liu.dao.BookDao;
import com.liu.pojo.Book;

import java.util.List;

/**
 * @author liuliang
 * @create2021-08-2021/8/30-12:55
 * @email 2640336916@qq.com
 * @explain
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book" +
                "(`name`,`price`,`author`,`sales`,`stock`,`img_path`)" +
                "values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from `t_book` where `id` = ?";
        return update(sql,id);

    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql ="select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql ="select count(*) from t_book";
        Number count = (Number)queryForSingleValue(sql);
        return count.intValue();

    }

    @Override
    public List queryPageItems(Integer begin, Integer pageSize) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageByPriceTotalCount(String min, String max) {
        String sql ="select count(*) from t_book where `price` between ? and ?";
        Number count = (Number)queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryPageByPriceItems(Integer begin, Integer pageSize, String min, String max) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath from t_book where `price` between ? and ? limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}

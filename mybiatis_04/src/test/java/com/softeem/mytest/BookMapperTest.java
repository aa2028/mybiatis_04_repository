package com.softeem.mytest;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.mapper.BookMapper;
import com.softeem.pojo.Book;
import com.softeem.pojo.BookExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookMapperTest {

    private BookMapper bookMapper ;

    @Before
    public void init() throws IOException {
        //InputStream in = UserMapperTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        //读取MyBatis的核心配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(in);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession(false);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //通过代理模式创建UserMapper接口的代理实现类对象
        bookMapper = sqlSession.getMapper(BookMapper.class);
    }

    @Test
    public void findAllTest(){
        PageHelper.startPage(1000,5);
        //PageHelper.offsetPage((pageNo - 1 )* pageSize, 5);
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andSalesBetween(1 , 500);
        List<Book> bookList = bookMapper.selectByExample(bookExample);
        PageInfo<Book> pageInfo = new PageInfo<>(bookList);

        /*BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andSalesBetween(1 , 500);
        PageInfo<Book> pageInfo = PageHelper.startPage(1,5).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                bookMapper.selectByExample(bookExample);
            }
        });*/

        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页的数量:"+pageInfo.getPageSize());
        System.out.println("当前页的数量:"+pageInfo.getSize());
        System.out.println("当前页面第一个元素在数据库中的行号:"+pageInfo.getStartRow());
        System.out.println("当前页面最后一个元素在数据库中的行号:"+pageInfo.getEndRow());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("前一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否为第一页:"+pageInfo.isIsFirstPage());
        System.out.println("是否为最后一页:"+pageInfo.isIsLastPage());
        System.out.println("是否有前一页:"+ pageInfo.isHasPreviousPage());
        System.out.println("是否有下一页:"+pageInfo.isHasNextPage());
        System.out.println("导航页码数:"+pageInfo.getNavigatePages());
        System.out.println("所有导航页号:"+ Arrays.toString(pageInfo.getNavigatepageNums()));
        System.out.println("导航条上的第一页:"+pageInfo.getNavigateFirstPage());
        System.out.println("导航条上的最后一页:"+pageInfo.getNavigateLastPage());
        System.out.println("总记录数:"+pageInfo.getTotal());
        System.out.println("结果集:");
        for (Book book : pageInfo.getList()) {
            System.out.println(book);
        }

    }

    @Test
    public void loginTest(){
        BookExample bookExample = new BookExample() ;
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andNameEqualTo("张嘉伦");
        criteria.andSalesEqualTo(1111);
        List<Book> books = bookMapper.selectByExample(bookExample);

        if( books.size() > 0){
            System.out.println("登录成功");
        }else{
            System.out.println("用户名密码错误!");
        }
    }


    @Test
    public void peiJieQueryTest(){
        List<Book> list = bookMapper.peiJieQuery("张", 1, 5);
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页的数量:"+pageInfo.getPageSize());
        System.out.println("当前页的数量:"+pageInfo.getSize());
        System.out.println("当前页面第一个元素在数据库中的行号:"+pageInfo.getStartRow());
        System.out.println("当前页面最后一个元素在数据库中的行号:"+pageInfo.getEndRow());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("前一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否为第一页:"+pageInfo.isIsFirstPage());
        System.out.println("是否为最后一页:"+pageInfo.isIsLastPage());
        System.out.println("是否有前一页:"+ pageInfo.isHasPreviousPage());
        System.out.println("是否有下一页:"+pageInfo.isHasNextPage());
        System.out.println("导航页码数:"+pageInfo.getNavigatePages());
        System.out.println("所有导航页号:"+ Arrays.toString(pageInfo.getNavigatepageNums()));
        System.out.println("导航条上的第一页:"+pageInfo.getNavigateFirstPage());
        System.out.println("导航条上的最后一页:"+pageInfo.getNavigateLastPage());
        System.out.println("总记录数:"+pageInfo.getTotal());
        System.out.println("结果集:");
        for (Book book : pageInfo.getList()) {
            System.out.println(book);
        }
    }

    @Test
    public void peiJieQuery2Test(){
        Book book = new Book();
        book.setName("张");
        book.setPageNum(1);
        book.setPageSize(3);
        List<Book> list = bookMapper.peiJieQuery2(book);
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页的数量:"+pageInfo.getPageSize());
        System.out.println("当前页的数量:"+pageInfo.getSize());
        System.out.println("当前页面第一个元素在数据库中的行号:"+pageInfo.getStartRow());
        System.out.println("当前页面最后一个元素在数据库中的行号:"+pageInfo.getEndRow());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("前一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否为第一页:"+pageInfo.isIsFirstPage());
        System.out.println("是否为最后一页:"+pageInfo.isIsLastPage());
        System.out.println("是否有前一页:"+ pageInfo.isHasPreviousPage());
        System.out.println("是否有下一页:"+pageInfo.isHasNextPage());
        System.out.println("导航页码数:"+pageInfo.getNavigatePages());
        System.out.println("所有导航页号:"+ Arrays.toString(pageInfo.getNavigatepageNums()));
        System.out.println("导航条上的第一页:"+pageInfo.getNavigateFirstPage());
        System.out.println("导航条上的最后一页:"+pageInfo.getNavigateLastPage());
        System.out.println("总记录数:"+pageInfo.getTotal());
        System.out.println("结果集:");
        for (Book book1 : pageInfo.getList()) {
            System.out.println(book1);
        }
    }
}

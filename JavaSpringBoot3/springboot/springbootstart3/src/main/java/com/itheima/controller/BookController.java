package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.controller.utile.R;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import com.itheima.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService service;

    @GetMapping
    public R getAll(){
        List<Book> list = service.list();
        return new R(true,list);
    }

    @PostMapping
    public R save(@RequestBody Book book){
        boolean flag = service.save(book);
        return new R(flag,flag?"添加成功":"添加失败");
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable int id){//原来是Integer 类型？？？？？？？？？？？？？？？？
        return new R(service.removeById(id));
    }

    @RequestMapping
    public R update(@RequestBody Book book){
        boolean b = service.updateById(book);
        return new R(b,b?"修改成功":"修改失败");
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){
//        IPage<Book> page=new Page<>(currentPage,pageSize,book);

        IPage<Book> page = service.getPage(currentPage, pageSize,book);
        service.page(page,null);
        return new R(true,page);
    }


}

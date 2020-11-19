package com.yuzhou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    private Integer id;
    private String borrowTime;
    private String returnTime;
    //面向对象思想，把数据表封装成对象，对象之间可以关联
    private List<Book> books;
    private List<Reader> readers;
    private Integer state;
}

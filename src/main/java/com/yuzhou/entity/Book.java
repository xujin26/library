package com.yuzhou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private Integer pages;
    private Double price;
    private BookCase bookCase;//分类封装成另一个对象

    //适应Borrow，方便封装
    public Book(String name, String author, String publish) {
        this.name = name;
        this.author = author;
        this.publish = publish;
    }
}

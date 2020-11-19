package com.yuzhou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String cardId;
    private String gender;

    //适应Borrow，方便封装
    public Reader(String name, String tel, String cardId) {
        this.name = name;
        this.tel = tel;
        this.cardId = cardId;
    }
}

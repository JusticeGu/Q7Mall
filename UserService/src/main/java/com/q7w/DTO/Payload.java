package com.q7w.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/27 14:53
 **/
@Data
public class Payload {
    private Long id;
    private String user_name;
    private Long exp;
    private String jti;
    private String client_id;
    private List scope;

}

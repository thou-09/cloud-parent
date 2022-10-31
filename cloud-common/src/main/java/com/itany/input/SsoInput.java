package com.itany.input;

import lombok.Data;

import java.io.Serializable;

/**
 * SsoInput.
 *
 * @author Thou
 * @date 2022/10/31
 */
@Data
public class SsoInput implements Serializable {

    private static final long serialVersionUID = -1298968725164488658L;

    private String name;

    private String password;

    private String checkCode;

    private String server;

    private String ticket;
}

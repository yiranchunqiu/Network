package com.pxz.network;

/**
 * 类说明：
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/11/20 14:15
 */
public class Bean {
    private String username;
    private String password;
    private String repassword;

    public Bean(String username, String password, String repassword) {
        this.username = username;
        this.password = password;
        this.repassword = repassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}

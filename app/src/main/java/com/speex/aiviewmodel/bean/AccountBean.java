package com.speex.aiviewmodel.bean;


/**
 * 账号对象
 */
public class AccountBean {
    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 博客
     */
    private String blogs;

    /**
     * 消息内容
     */
    private String message;

    public AccountBean(String name, String phone, String blogs, String message) {
        this.name = name;
        this.phone = phone;
        this.blogs = blogs;
        this.message = message;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlogs() {
        return blogs;
    }

    public void setBlogs(String blogs) {
        this.blogs = blogs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

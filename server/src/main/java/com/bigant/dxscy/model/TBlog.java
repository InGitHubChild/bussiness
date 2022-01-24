package com.bigant.dxscy.model;

import lombok.Data;

/**
 *
 */
@Data
public class TBlog {
    /**
     *
     */
    private Integer blogId;
    /**
     *
     */
    private Integer userId;
    /**
     *
     */
    private String blogOwner;
    /**
     *
     */
    private String blogTitle;
    /**
     *
     */
    private Integer viewTitmes;
    /**
     *
     */
    private Integer blogLikes;
    /**
     *
     */
    private String blogText;
    /**
     *
     */
    private String blogTag;
}
package com.bigant.dxscy.model;

import lombok.Data;

/**
 *
 */
@Data
public class TArticle {
    /**
     *
     */
    private Integer articleId;
    /**
     *
     */
    private String articleTitle;
    /**
     *
     */
    private String articleText;
    /**
     *
     */
    private String articlePic;
    /**
     *
     */
    private Integer aviewTimes;
    /**
     *
     */
    private Integer articleCollects;
    /**
     *
     */
    private String collectarticleTime;
}
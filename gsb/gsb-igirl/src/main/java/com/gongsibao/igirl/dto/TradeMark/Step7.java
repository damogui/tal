package com.gongsibao.igirl.dto.TradeMark;

import org.netsharp.util.StringManager;

//商标图样
public class Step7 {
    //图样URL
    private String picPath;

    //图样文件名
    private String picName;

    //黑白稿，true则上传
    private String isBlack;

    //黑白稿Url
    private String blackPath;

    //黑白稿文件名
    private String blackName;

    //以肖像作为商标申请注册,checkbox,默认false
    private String isPersonPhoto;

    //肖像图片URL
    private String personPhotoPath;

    //肖像图片文件名
    private String personPhotoName;

    //说明文件url
    private String commentPath;

    //说明文件文件名,pdf
    private String commentName;


    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(String isBlack) {
        this.isBlack = isBlack;
    }

    public String getBlackPath() {
        return StringManager.isNullOrEmpty(blackPath)?"":blackPath;
    }

    public void setBlackPath(String blackPath) {
        this.blackPath = blackPath;
    }

    public String getBlackName() {
        return StringManager.isNullOrEmpty(blackName)?"":blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
    }

    public String getIsPersonPhoto() {
        return isPersonPhoto;
    }

    public void setIsPersonPhoto(String isPersonPhoto) {
        this.isPersonPhoto = isPersonPhoto;
    }

    public String getPersonPhotoPath() {
        return StringManager.isNullOrEmpty(personPhotoPath)?"": personPhotoPath;
    }

    public void setPersonPhotoPath(String personPhotoPath) {
        this.personPhotoPath = personPhotoPath;
    }

    public String getPersonPhotoName() {
        return StringManager.isNullOrEmpty(personPhotoName)?"": personPhotoName;
    }

    public void setPersonPhotoName(String personPhotoName) {
        this.personPhotoName = personPhotoName;
    }

    public String getCommentPath() {
        return StringManager.isNullOrEmpty(commentPath)?"":commentPath;
    }

    public void setCommentPath(String commentPath) {
        this.commentPath = commentPath;
    }

    public String getCommentName() {
        return StringManager.isNullOrEmpty(commentName)?"":commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }
}

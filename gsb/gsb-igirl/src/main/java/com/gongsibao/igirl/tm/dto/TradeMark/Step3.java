package com.gongsibao.igirl.tm.dto.TradeMark;

import org.netsharp.util.StringManager;

//商标声明
public class Step3 {
    //商标类型：tmType1=一般，tmType2=集体，tmType3=证明
    private String tmType;

    ////是否三维标志,ifSolidTm1=否，ifSolidTm=是
    private String ifSolidTm;

    //是否颜色组合,colourSign1=否，colourSign2=是
    private String colourSign;

    //声音商标
    private String tmFormType;

    //声音文件的全URL
    private String tmFormTypeFilePath;

    //声音文件的文件名
    private String tmFormTypeFileName;

    //商标说明,多行文本
    private String tmDesignDeclare;

    public String getTmType() {
        return tmType;
    }

    public void setTmType(String tmType) {
        this.tmType = tmType;
    }

    public String getIfSolidTm() {
        return ifSolidTm;
    }

    public void setIfSolidTm(String ifSolidTm) {
        this.ifSolidTm = ifSolidTm;
    }

    public String getColourSign() {
        return colourSign;
    }

    public void setColourSign(String colourSign) {
        this.colourSign = colourSign;
    }

    public String getTmFormType() {
        return tmFormType;
    }

    public void setTmFormType(String tmFormType) {
        this.tmFormType = tmFormType;
    }

    public String getTmFormTypeFilePath() {
        return StringManager.isNullOrEmpty(tmFormTypeFilePath)?"":tmFormTypeFilePath;
    }

    public void setTmFormTypeFilePath(String tmFormTypeFilePath) {
        this.tmFormTypeFilePath = tmFormTypeFilePath;
    }

    public String getTmFormTypeFileName() {
        return StringManager.isNullOrEmpty(tmFormTypeFileName)?"":tmFormTypeFileName;
    }

    public void setTmFormTypeFileName(String tmFormTypeFileName) {
        this.tmFormTypeFileName = tmFormTypeFileName;
    }

    public String getTmDesignDeclare() {
        return tmDesignDeclare;
    }

    public void setTmDesignDeclare(String tmDesignDeclare) {
        this.tmDesignDeclare = tmDesignDeclare;
    }
}

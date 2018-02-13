package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum MarkState implements IEnum {

    READY(0, "资料准备","READY"),//business_liense
    DOCFINISH(1, "资料齐全","DOCFINISH"),//business_liense;
    WAITCOMMIT(2, "待提交","WAITCOMMIT"),//business_liense
    COMMITED(3, "人工已提交","COMMITED"),// trademark picture
    RECVCOMMIT(4, "已收提交","RECVCOMMIT"),// trademark picture
    RECVED(5, "已受理","RECVED"),
    PARTREJECT(6, "部分驳回","PARTREJECT"),
    ALLREJECT(7, "全部驳回","ALLREJECT"),
    FIRSTPUB(8, "商家异议","FIRSTPUB"),
    ROBOT(9, "机器人已提交","ROBOT"),
    PASSED(10, "已通过","PASSED"),
	  FILLEXCEPTION(11, "填报异常","FILLEXCEPTION"),
	  FILELIST(12, "交文清单","FILELIST"),
	  NOTRECVED(13, "不予受理","NOTRECVED"),
	  ENVLOPE(14, "信封正反面","ENVLOPE"),
	  ADDTIONALPROOF(15, "补证通知","ADDTIONALPROOF"),
	  DECLARE(16, "初步审定公告","DECLARE"),
	  SENTENCE(17, "裁定通知","SENTENCE"),
	  NOTAPPROVAL(18, "不予核准","NOTAPPROVAL"),
	  APPROVAL(19, "核准通知","APPROVAL"),
	  SAMEDAYCORP(20, "同日申请协商","SAMEDAYCORP"),
	  SAMEDAYADDTIONALPROOF(21, "同日申请补送使用证据","SAMEDAYADDTIONALPROOF");
	/**
	 * 受理通知 5
	 * 部分驳回  6
	 * 驳回通知 7
	 * 交文清单 12
	 * 不予受理 13
	 * 信封正反面 14
	 * 商标注册证 10
	 * 补证通知 15
	 * 异议通知 8
	 * 初步审定公告 16
	 * 裁定通知  17
	 * 不予核准 18
	 * 核准通知 19
	 * 同日申请协商通知书 20
	 * 同日申请补送使用证据 21
	 */
    private int value;
    private String text;
    private String code;

    MarkState(int value, String text,String code) {
        this.value = value;
        this.text = text;
        this.code=code;
    }

    @JsonCreator
    public static MarkState getItem(int value) {

        for (MarkState item : values()) {

            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }
  
    public static MarkState getItemByCode(String code) {

        for (MarkState item : values()) {

            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }


    public String getText() {
        return this.text;
    }
    
    public String getCode() {
        return this.code;
    }

    @Override
    public Integer getValue() {

        return this.value;
    }
}

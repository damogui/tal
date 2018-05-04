package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum AttachmentCat implements IEnum {

    BUSINESS_LIEN(0, "营业执照"),//business_liense
    TRADEMARK_PICT(1, "商标图样"),// trademark picture
    //delegate proof
    DELEGATE_PROOF(2, "委托书"),
    CONFIRM_PROOF(3, "确认函"),//delegate proof
    PAYMENT_PROOF(4, "付款证明"),//payment proof
    MEMO_DESC(5, "有关说明"),//payment proof
	  PERSON_PROOF(6,"身份证");//payment proof
    private int value;
    private String text;

    AttachmentCat(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static AttachmentCat getItem(int value) {

        for (AttachmentCat item : values()) {

            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public Integer getValue() {

        return this.value;
    }
}

package com.gongsibao.tools;

import org.netsharp.core.EntityState;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.entity.PToolbarItem;

/**
 * Created by win on 2018/3/3.
 */
/*toolbar的帮助类*/
public class PToolbarHelper {

    /*普通的加号*/
    public  static    String iconAdd="fa fa-plus fa-fw";

    /*
     * 添加用户的图标
     */
    public  static    String iconAddUser="fa fa-user-plus fa-fw";

    /**
     * 转移
     */
    public  static    String iconTran="fa fa-share-square-o";

    /**
     * 普通的对号
     */
    public  static    String iconCheck="fa fa-check";

    /**
     * 收回
     */
    public  static    String iconBack="fa fa-mail-reply";

    /**
     * 刷新
     */
    public  static    String iconRefresh="fa fa-refresh";

    /**
     * 修改
     */
    public  static    String iconEdit="fa fa-edit";

    /**
     * 删除
     */
    public  static    String iconDle="fa fa-trash-o";
    /**
     * 附件
     */
    public  static    String iconExtr="fa fa-link";
    /**
     * 工具栏item选项
     * @entityState 状态
     * @code code值
     * @icon 图标
     * @name 名称
     * @operationType 操作类型
     * @seq 索引顺序
     * @command 对应js的方法
     * @return 返回PToolbarItem选项
     */
    public static PToolbarItem  getPToolbarItem(EntityState  entityState,String code,String icon,String name, OperationType operationType,Integer seq,String command){
        PToolbarItem  item=new PToolbarItem ();
        item.setEntityState (entityState);
        item.setCode (code);
        item.setIcon (icon);//fa-user-plus
        item.setName (name);
        item.setOperationType (operationType);
        item.setSeq (seq);
        item.setCommand (command);
        return  item;

    }




}

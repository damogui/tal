package com.gongsibao.entity.igirl.ic;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name = "ic_person",header = "股东信息")
public class IcPerson {
    @Column(name = "name",header = "姓名")
    private String name;
}

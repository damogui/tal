package com.gongsibao.trade.service.action.order.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

/**   
 * @ClassName:  ActionSaveOrderTrademark   
 * @Description:TODO 处理商标(暂不处理)
 * 执行顺序：1
 * @author: 韩伟
 * @date:   2018年3月2日 下午5:03:23   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionSaveOrderTrademark implements IAction{

	@Override
	public void execute(ActionContext ctx) {

//		SoOrder soOrder = (SoOrder) ctx.getItem();
		
        //region 商标信息
//        YjTrademark yjTrademark = soOrderProd.getYjTrademark();
//        if (yjTrademark != null) {
//            yjTrademark.setOrderProdId(orderProdId);
//            Integer yjTrademarkId = yjTrademarkService.insert(yjTrademark);
//            List<YjTrademarkImg> trademarkImgList = yjTrademark.getTrademarkImgList();
//            for (YjTrademarkImg yjTrademarkImg : trademarkImgList) {
//                yjTrademarkImg.setTrademarkId(yjTrademarkId);
//                yjTrademarkImgService.insert(yjTrademarkImg);
//            }
//        }
        // endregion
	}

}

///*添加商标信息*/
//private YjTrademark mapToTradeMark(Map<String, Object> trademarkMap, YjCompany company) {
//    if (MapUtils.isEmpty(trademarkMap)) return null;
//    YjTrademark yjTrademark = new YjTrademark();
//    //商标id(当购买比如【商标变更】时，到底是针对哪个商标购买的呢？)
//    int trademarkId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(trademarkMap.get("pkidStr"))));
//    //商标名称
//    String name = StringUtils.trimToEmpty(trademarkMap.get("name"));
//    yjTrademark.setPkid(trademarkId);
//    yjTrademark.setName(name);
//    //上传的图片id集合
//    ArrayList imgUrlIdList = (ArrayList) trademarkMap.get("imgUrlIdList");
//
//    List<YjTrademarkCategory> fristList = yjTrademarkCategoryService.getByLevel(1);
//    LinkedHashMap categoryMap = (LinkedHashMap) trademarkMap.get("category");
//    if (MapUtils.isNotEmpty(categoryMap)) {
//        //大类id
//        int firstCategoryId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(categoryMap.get("firstCategoryIdStr")));
//        List<YjTrademarkCategory> yjTrademarkCategoryList = fristList.stream().filter(x -> x.getPkid().equals(firstCategoryId)).collect(Collectors.toList());
//        if (CollectionUtils.isNotEmpty(yjTrademarkCategoryList)) {
//            YjTrademarkCategory yjTrademarkCategory = yjTrademarkCategoryList.get(0);
//            List<String> intClsCategory = new ArrayList<>();
//
//            yjTrademark.setYjCompanyId(company.getPkid());//云聚企业信息id
//            yjTrademark.setCompanyId(company.getCompanyId());//大表单id
//            yjTrademark.setIntCls(NumberUtils.toInt(yjTrademarkCategory.getCode()));
//            yjTrademark.setIntClsName(yjTrademarkCategory.getName());
//            yjTrademark.setIsFormal(0);//是否正式：0否 1是
//            //申请人，即该公司名称
//            yjTrademark.setApplicantCn(company.getName().replace("（", "(").replace("）", ")"));
//            ArrayList productIdStrList = (ArrayList) categoryMap.get("productIdStrList");
//            if (CollectionUtils.isNotEmpty(productIdStrList)) {
//                List<YjTrademarkCategory> level3ByFristId = yjTrademarkCategoryService.getLevel3ByFristId(yjTrademarkCategory.getPkid());
//                for (Object productId : productIdStrList) {
//                    LinkedHashMap productIdMap = (LinkedHashMap) productId;
//                    int trademarkprodcutcateId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(productIdMap.get("idStr")));
//                    List<YjTrademarkCategory> trademarkprodcutcateList = level3ByFristId.stream().filter(x -> x.getPkid() == trademarkprodcutcateId).collect(Collectors.toList());
//                    if (CollectionUtils.isNotEmpty(trademarkprodcutcateList)) {
//                        YjTrademarkCategory cate = trademarkprodcutcateList.get(0);
//                        intClsCategory.add(cate.getName());
//                    }
//                }
//            }
//            yjTrademark.setIntClsCategory(JsonUtils.objectToJson(intClsCategory));
//            //商标图片
//            List<YjTrademarkImg> imglsit = new ArrayList<>();
//            if (CollectionUtils.isNotEmpty(imgUrlIdList)) {
//                for (Object imgUrlId : imgUrlIdList) {
//                    YjTrademarkImg yjTrademarkImg = new YjTrademarkImg();
//                    LinkedHashMap imgUrlIdMap = (LinkedHashMap) imgUrlId;
//                    int imgFileId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(imgUrlIdMap.get("fileIdStr")));
//                    if (imgFileId > 0) {
//                        yjTrademarkImg.setFileId(imgFileId);
//                        yjTrademarkImg.setName(StringUtils.trimToEmpty(imgUrlIdMap.get("name")));
//                        imglsit.add(yjTrademarkImg);
//                    }
//                    //图片url
//                    String imgUrl = StringUtils.trimToEmpty(imgUrlIdMap.get("fileUrl"));
//                    if (imgFileId == 0 && StringUtils.isNotBlank(imgUrl)) {
//                        BdFile file = new BdFile();
//                        file.setAddTime(new Date());
//                        file.setTabName("yj_trademark_img");
//                        file.setUrl(imgUrl);
//                        file.setFormId(0);
//                        file.setAddUserId(0);
//                        file.setName(StringUtils.trimToEmpty(imgUrlIdMap.get("name")));
//                        int imgId = bdFileService.insert(file);
//                        yjTrademarkImg.setFileId(imgId);
//                        yjTrademarkImg.setName(StringUtils.trimToEmpty(imgUrlIdMap.get("name")));
//                        imglsit.add(yjTrademarkImg);
//                    }
//                }
//            }
//            yjTrademark.setTrademarkImgList(imglsit);
//        }
//    }
//    return yjTrademark;
//}

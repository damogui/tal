package com.gongsibao.crm.service.action.task.allocation.auto;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.bd.dic.DictType;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanProduct;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.supplier.base.ISalesmanService;

/**
 * @author zhangchao 分配逻辑
 */
public class ActionAutoAllocationPersist implements IAction {

    // 商机服务对象
    INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
    // 业务员服务
    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
    // 商机服务
    IPersister<NCustomerTask> taskPm = PersisterFactory.create();

    @Override
    public void execute(ActionContext ctx) {
        NCustomerTask entity = (NCustomerTask) ctx.getItem();

        Map<String, Object> statusMap = new HashMap<String, Object>();
        statusMap.put("formUserId", NumberUtils.toInt(entity.getOwnerId()));
        ctx.setStatus(statusMap);
        // 查询出符合条件的业务员
        List<Salesman> salesmanList = getListByCondition(entity);
        // 符合服务范围的业务员
        List<Salesman> taskSalesmanProducts = getTaskSalesmanProducts(salesmanList, entity.getProducts());

        // 无符合意向产品/地区的组织机构
        if (CollectionUtils.isEmpty(taskSalesmanProducts)) {
            // 无市场投放
            if (!entity.getCosted()) {
                // 将分配方式选中【手动分配】
                updateTaskAllocationType(entity.getId(), NAllocationType.MANUAL);
                entity.setAllocationType(NAllocationType.MANUAL);
                // TODO:提醒售前客服负责人进行手动分配，日志信息
                return;
            } else {
                // 将分配方式选中【半自动分配】
                updateTaskAllocationType(entity.getId(), NAllocationType.SemiAutomatic);
                // 分配至目标部门的【公海】,此时的跟进服务商修改成【有市场投放的部门（服务商）】
                updateTaskOwnerId(entity.getId(), 0, entity.getCostSupplierId(), 0);
                // 跟新实体，防止后面的action用到实体时，不是最新的就要重新查一下，影响效率
                entity.setAllocationType(NAllocationType.SemiAutomatic);
                entity.setOwnerId(0);
                entity.setSupplierId(entity.getCostSupplierId());
                entity.setDepartmentId(0);
                // TODO:提醒部门负责人进行商机分配，日志信息
                return;
            }
        }

        // 分配方式:半自动分配时（分配到跟进服务商即可）
        if (entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
            // 分配至目标服务商的【公海】,直接就是剩下业务员所在的服务商（如果有市场投放，则都是该有市场投放部门的人，如果没有市场投放则就在剩下业务员所在部门随便挑一个）
            updateTaskOwnerId(entity.getId(), 0, taskSalesmanProducts.get(0).getSupplierId(), taskSalesmanProducts.get(0).getDepartmentId());
            entity.setOwnerId(0);
            entity.setSupplierId(taskSalesmanProducts.get(0).getSupplierId());
            entity.setDepartmentId(taskSalesmanProducts.get(0).getDepartmentId());
            // TODO:提醒部门负责人进行商机分配，日志信息
            return;
        }

        // 分配方式:自动分配时
        allocation(entity, taskSalesmanProducts);
    }

    // 获取符合条件的【业务员】
    private List<Salesman> getListByCondition(NCustomerTask entity) {
        List<Salesman> resList = new ArrayList<Salesman>();
        // 查询业务员的条件集合
        List<String> salesmanSqlWhereList = new ArrayList<String>();
        salesmanSqlWhereList.add(" disabled=0 ");// 没有停用的
        salesmanSqlWhereList.add(" receiving=1 ");// 是否接单(是否接单)
        // 当有市场投放时，优先有市场投放的部门
        if (entity.getCosted()) {
            salesmanSqlWhereList.add(" supplier_id=" + entity.getCostSupplierId() + " ");
        } else {
            // 当有跟进【服务商时】，将分配组织机构范围缩小至跟进部门
            if (NumberUtils.toInt(entity.getSupplierId()) != 0) {
                salesmanSqlWhereList.add(" supplier_id=" + entity.getSupplierId() + " ");
            }
        }
        // 新客户时(【新客户】或【全部】标签的组织机构)
        if (entity.getTaskType().equals(TaskCustomerType.NEW)) {
            salesmanSqlWhereList.add(" (customer_type=" + TaskCustomerType.NEW.getValue() + " or customer_type=" + TaskCustomerType.All.getValue() + ") ");
        }
        // 老客户时(【老客户】或【全部】标签的组织机构)
        if (entity.getTaskType().equals(TaskCustomerType.OLD)) {
            salesmanSqlWhereList.add(" (customer_type=" + TaskCustomerType.OLD.getValue() + " or customer_type=" + TaskCustomerType.All.getValue() + ") ");
        }
        // 平台：自营时
        if (entity.getAllocationDispositon().equals(SupplierType.SELFSUPPORT)) {
            salesmanSqlWhereList.add(" (allocation_dispositon=" + SupplierType.SELFSUPPORT.getValue() + " or allocation_dispositon=" + SupplierType.UNLIMITED.getValue() + ") ");
        }
        // 平台：平台时
        if (entity.getAllocationDispositon().equals(SupplierType.PLATFORM)) {
            salesmanSqlWhereList.add(" (allocation_dispositon=" + SupplierType.PLATFORM.getValue() + " or allocation_dispositon=" + SupplierType.UNLIMITED.getValue() + ") ");
        }

        // 查询业务员的条件的字符串
        String whereString = StringManager.join(" and ", salesmanSqlWhereList);

        Oql salesmanOql = new Oql();
        {
            salesmanOql.setType(Salesman.class);
            salesmanOql.setSelects("Salesman.*,Salesman.products.*");
            salesmanOql.setFilter(whereString);
        }

        // 查询出符合条件的业务员
        resList = salesmanService.queryList(salesmanOql);
        return resList;
    }

    // 符合服务范围的业务员
    private List<Salesman> getTaskSalesmanProducts(List<Salesman> salesmanList, List<NCustomerProduct> taskProducts) {
        List<Salesman> resList = new ArrayList<Salesman>();
        // 筛选出满足服务范围的业务员
        for (Salesman s : salesmanList) {
            for (NCustomerProduct nCustomerProduct : taskProducts) {
                // 商机产品一级分类
                Integer taskProductCategoryId1 = nCustomerProduct.getProductCategoryId1() == null ? 0 : nCustomerProduct.getProductCategoryId1();
                // 商机产品一级分类
                Integer taskProductCategoryId2 = nCustomerProduct.getProductCategoryId2() == null ? 0 : nCustomerProduct.getProductCategoryId2();
                // 商机产品id
                Integer taskProductId = nCustomerProduct.getProductId() == null ? 0 : nCustomerProduct.getProductId();
                // 获取【产品一级】、【产品二级】
                if (!taskProductId.equals(0)) {
                    if (taskProductCategoryId2.equals(0)) {
                        taskProductCategoryId2 = getProdType(taskProductId);
                    }
                    if (taskProductCategoryId1.equals(0)) {
                        taskProductCategoryId1 = getDictId(taskProductCategoryId2, DictType.Cpfl.getValue());
                        taskProductCategoryId1 = taskProductCategoryId1.equals(0) ? taskProductCategoryId2 : taskProductCategoryId1;
                    }
                }
                // 获取【产品一级】
                if (taskProductId.equals(0) && !taskProductCategoryId2.equals(0)) {
                    if (taskProductCategoryId1.equals(0)) {
                        taskProductCategoryId1 = getDictId(taskProductCategoryId2, DictType.Cpfl.getValue());
                        taskProductCategoryId1 = taskProductCategoryId1.equals(0) ? taskProductCategoryId2 : taskProductCategoryId1;
                    }
                }

                // 商机省id
                Integer taskProvinceId = nCustomerProduct.getProvinceId() == null ? 0 : nCustomerProduct.getProvinceId();
                // 商机市id
                Integer taskCityId = nCustomerProduct.getCityId() == null ? 0 : nCustomerProduct.getCityId();
                // 商机区id
                Integer taskCountyId = nCustomerProduct.getCountyId() == null ? 0 : nCustomerProduct.getCountyId();

                // 获取【省】、【市】
                if (!taskCountyId.equals(0)) {
                    if (taskCityId.equals(0)) {
                        taskCityId = getDictId(taskCountyId, DictType.Diqu.getValue());
                    }
                    if (taskProvinceId.equals(0)) {
                        taskProvinceId = getDictId(taskCityId, DictType.Diqu.getValue());
                    }
                }
                // 获取【省】
                if (taskCountyId.equals(0) && !taskCityId.equals(0)) {
                    if (taskProvinceId.equals(0)) {
                        taskProvinceId = getDictId(taskCityId, DictType.Diqu.getValue());
                    }
                }

                List<SalesmanProduct> salesmanProducts = s.getProducts();

                for (SalesmanProduct salesmanProduct : salesmanProducts) {

                    // 商机产品一级分类
                    Integer salesmanProductCategoryId1 = salesmanProduct.getProductCategoryId1() == null ? 0 : salesmanProduct.getProductCategoryId1();
                    // 商机产品一级分类
                    Integer salesmanProductCategoryId2 = salesmanProduct.getProductCategoryId2() == null ? 0 : salesmanProduct.getProductCategoryId2();
                    // 商机产品id
                    Integer salesmanProductId = salesmanProduct.getProductId() == null ? 0 : salesmanProduct.getProductId();

                    // 获取【产品一级】、【产品二级】
                    if (!salesmanProductId.equals(0)) {
                        if (salesmanProductCategoryId2.equals(0)) {
                            salesmanProductCategoryId2 = getProdType(salesmanProductId);
                        }
                        if (salesmanProductCategoryId1.equals(0)) {
                            taskProductCategoryId1 = getDictId(taskProductCategoryId2, DictType.Cpfl.getValue());
                            taskProductCategoryId1 = taskProductCategoryId1.equals(0) ? taskProductCategoryId2 : taskProductCategoryId1;
                        }
                    }
                    // 获取【产品一级】
                    if (salesmanProductId.equals(0) && !salesmanProductCategoryId2.equals(0)) {
                        if (salesmanProductCategoryId1.equals(0)) {
                            taskProductCategoryId1 = getDictId(taskProductCategoryId2, DictType.Cpfl.getValue());
                            taskProductCategoryId1 = taskProductCategoryId1.equals(0) ? taskProductCategoryId2 : taskProductCategoryId1;
                        }
                    }

                    // 商机省id
                    Integer salesmanProvinceId = salesmanProduct.getProvinceId() == null ? 0 : salesmanProduct.getProvinceId();
                    // 商机市id
                    Integer salesmanCityId = salesmanProduct.getCityId() == null ? 0 : salesmanProduct.getCityId();
                    // 商机区id
                    Integer salesmanCountyId = salesmanProduct.getCountyId() == null ? 0 : salesmanProduct.getCountyId();

                    // 获取【省】、【市】
                    if (!salesmanCountyId.equals(0)) {
                        if (salesmanCityId.equals(0)) {
                            salesmanCityId = getDictId(salesmanCountyId, DictType.Diqu.getValue());
                        }
                        if (salesmanProvinceId.equals(0)) {
                            salesmanProvinceId = getDictId(salesmanCityId, DictType.Diqu.getValue());
                        }
                    }
                    // 获取【省】
                    if (salesmanCountyId.equals(0) && !salesmanCityId.equals(0)) {
                        if (salesmanProvinceId.equals(0)) {
                            salesmanProvinceId = getDictId(salesmanCityId, DictType.Diqu.getValue());
                        }
                    }
                    // 是否符合产品服务范围条件
                    Boolean isAddForProduct = false;
                    // 是否符合区域服务范围条件
                    Boolean isAddForCity = false;
                    // 该商机的该服务范围选了具体的产品时
                    if (!taskProductId.equals(0)) {
                        // 当该业务员也选择了产品，则两者选择的产品也要相等
                        if (!salesmanProductId.equals(0) && salesmanProductId.equals(taskProductId)) {
                            isAddForProduct = true;
                        }
                        // 当业务员没有选择产品时，则业务员选项的产品二级分类要和商机的二级分类相等
                        if (salesmanProductId.equals(0) && !salesmanProductCategoryId2.equals(0) && salesmanProductCategoryId2.equals(taskProductCategoryId2)) {
                            isAddForProduct = true;
                        }
                        // 当业务员没有选择产品，也没有选择产品二级分类时，则两者的产品大类要相等
                        if (salesmanProductId.equals(0) && salesmanProductCategoryId2.equals(0) && salesmanProductCategoryId1.equals(taskProductCategoryId1)) {
                            isAddForProduct = true;
                        }
                    }
                    // 当该商机没有选择具体的服务产品时
                    if (taskProductId.equals(0)) {
                        // 当该商机选择了产品二级分类时
                        if (!taskProductCategoryId2.equals(0)) {
                            // 当业务员没有选择了产品二级分类时，则只需要两者选择的产品一级分类相等就行了
                            if (salesmanProductCategoryId2.equals(0) && salesmanProductCategoryId1.equals(taskProductCategoryId1)) {
                                isAddForProduct = true;
                            }
                            // 当业务员选择了产品二级分类时，则只需要两者选择的产品二级分类相等就行了
                            if (!salesmanProductCategoryId2.equals(0) && salesmanProductCategoryId2.equals(taskProductCategoryId2)) {
                                isAddForProduct = true;
                            }
                        }
                        // 当该商机只选择了产品大类，则只需要两者的产品大类相等就行了
                        if (!taskProductCategoryId1.equals(0) && taskProductCategoryId2.equals(0)) {
                            if (salesmanProductCategoryId1.equals(taskProductCategoryId1)) {
                                isAddForProduct = true;
                            }
                        }
                    }

                    // 当该商机服务范围的省市区都不选时，默认【全国】
                    if (taskProvinceId.equals(0) && taskCityId.equals(0) && taskCountyId.equals(0)) {
                        isAddForCity = true;
                    }
                    // 当该业务员服务范围的省市区都不选时，默认【全国】
                    if (salesmanProvinceId.equals(0) && salesmanCityId.equals(0) && salesmanCountyId.equals(0)) {
                        isAddForCity = true;
                    }

                    // 当该商机的该服务范围，选择到了区
                    if (!taskCountyId.equals(0)) {
                        if (!salesmanCountyId.equals(0) && taskCountyId.equals(salesmanCountyId)) {
                            isAddForCity = true;
                        }
                        if (salesmanCountyId.equals(0) && !salesmanCityId.equals(0) && salesmanCityId.equals(taskCityId)) {
                            isAddForCity = true;
                        }
                        if (salesmanCountyId.equals(0) && salesmanCityId.equals(0) && salesmanProvinceId.equals(taskProvinceId)) {
                            isAddForCity = true;
                        }
                    }

                    if (taskCountyId.equals(0)) {
                        if (!taskCityId.equals(0)) {
                            if (salesmanCityId.equals(0) && salesmanProvinceId.equals(taskProvinceId)) {
                                isAddForCity = true;
                            }
                            if (!salesmanCityId.equals(0) && salesmanCityId.equals(taskCityId)) {
                                isAddForCity = true;
                            }
                        }
                        if (!taskProvinceId.equals(0) && taskCityId.equals(0)) {
                            if (salesmanProvinceId.equals(taskProvinceId)) {
                                isAddForCity = true;
                            }
                        }
                    }

                    // 防止重复
                    if (!resList.contains(s) && isAddForProduct && isAddForCity) {
                        resList.add(s);
                    }

                }
            }
        }
        return resList;
    }

    public Integer getDictId(Integer cityId, Integer type) {
        Integer resId = 0;
        Oql oql = new Oql();
        {
            oql.setType(Dict.class);
            oql.setSelects("*");
            oql.setFilter("pkid=? AND type = ? ");
            oql.getParameters().add("pkid", cityId, Types.INTEGER);
            oql.getParameters().add("type", type, Types.INTEGER);
        }

        IPersister<Dict> dictPm = PersisterFactory.create();
        Dict city = dictPm.queryFirst(oql);
        resId = city == null ? 0 : city.getParentId();
        return resId;
    }

    private Integer getProdType(Integer prodId) {
        Integer resId = 0;
        Oql oql = new Oql();
        {
            oql.setType(Product.class);
            oql.setSelects("*");
            oql.setFilter("pkid=? AND is_enabled = 1 ");
            oql.getParameters().add("pkid", prodId, Types.INTEGER);
        }

        IPersister<Product> productPm = PersisterFactory.create();
        Product product = productPm.queryFirst(oql);
        resId = product == null ? 0 : product.getTypeId();
        return resId;

    }

    // 分配商机给业务员(自动分配)
    private void allocation(NCustomerTask entity, List<Salesman> taskSalesmanProducts) {
        // 最后的结果集合
        List<Salesman> resSalesmanList = new ArrayList<Salesman>();
        // 分配方式:自动分配时
        if (entity.getAllocationType().equals(NAllocationType.AUTO)) {
            List<Integer> employeeIdList = new ArrayList<Integer>();
            for (Salesman s : taskSalesmanProducts) {
                employeeIdList.add(s.getEmployeeId());
            }
            // 业务员的日已分配数量
            Map<Integer, Integer> dayMap = nCustomerTaskService.getTaskCountByEmployeeIdList(employeeIdList, 0);
            // 业务员的周已分配数量
            Map<Integer, Integer> weekMap = nCustomerTaskService.getTaskCountByEmployeeIdList(employeeIdList, 1);
            // 业务员的XAB类已分配数量
            Map<Integer, Integer> abxMap = nCustomerTaskService.getTaskCountByEmployeeIdList(employeeIdList, 2);

            for (Salesman salesman : taskSalesmanProducts) {
                // 是否接受自动分配(是否接单)
                if (!salesman.getReceiving())
                    continue;
                int dayCount = dayMap.get(salesman.getEmployeeId());
                int weekCount = weekMap.get(salesman.getEmployeeId());
                int abxCount = abxMap.get(salesman.getEmployeeId());
                // 日分配上线
                if (NumberUtils.toInt(salesman.getDayMax()) < dayCount)
                    continue;
                // 周分配上线
                if (NumberUtils.toInt(salesman.getWeekMax()) < weekCount)
                    continue;
                // XAB类商机上限
                if (NumberUtils.toInt(salesman.getXabMax()) < abxCount)
                    continue;
                salesman.setDayAllocatedCount(dayCount);
                // 取一个后跳出循环（需求说：选取随机一人进行分配）
                resSalesmanList.add(salesman);
            }
            // 当有符合条件【业务员】时，才更新
            if (CollectionUtils.isNotEmpty(resSalesmanList)) {
                // 找到今日已分配数量最少的人，然后选取随机一人进行分配
                resSalesmanList.sort(new Comparator<Salesman>() {
                    // 按照今日分配量，升序排序
                    @Override
                    public int compare(Salesman s1, Salesman s2) {
                        return s1.getDayAllocatedCount().compareTo(s2.getDayAllocatedCount());
                    }
                });
                //业务员id
                Integer ownerId = resSalesmanList.get(0).getEmployeeId();
                //部门id
                Integer departmentId = resSalesmanList.get(0).getDepartmentId();
                //服务商id
                Integer supplierId = resSalesmanList.get(0).getSupplierId();
                // 跟新业务员
                updateTaskOwnerId(entity.getId(), ownerId, supplierId, departmentId);
                // 跟新实体，防止后面的action用到实体时，不是最新的就要重新查一下，影响效率
                entity.setOwnerId(ownerId);
                entity.setSupplierId(supplierId);
                entity.setDepartmentId(departmentId);
            } else {// 无可分配对象->分配至目标部门的【公海】->将分配方式选中【手动分配】->提醒部门负责人进行商机分配
                // 将分配方式选中【手动分配】
                updateTaskAllocationType(entity.getId(), NAllocationType.MANUAL);
                // 跟新实体，防止后面的action用到实体时，不是最新的就要重新查一下，影响效率
                entity.setAllocationType(NAllocationType.MANUAL);
            }
        }
    }

    // 跟新业务员
    private void updateTaskOwnerId(Integer taskId, Integer ownerId, Integer supplierId, Integer departmentId) {
        // 跟新业务员
        // IPersister<NCustomerTask> taskPm = PersisterFactory.create();

        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("n_crm_customer_task");
            updateSql.set("owner_id", ownerId);
            updateSql.set("supplier_id", supplierId);
            updateSql.set("department_id", departmentId);
            updateSql.where("id=?");
        }
        String cmdText = updateSql.toSQL();

        QueryParameters qps = new QueryParameters();
        qps.add("id", taskId, Types.INTEGER);

        taskPm.executeNonQuery(cmdText, qps);
    }

    // 修改商机的【分配方式】
    private void updateTaskAllocationType(Integer taskId, NAllocationType allocationType) {
        // 跟新【分配方式】
        // IPersister<NCustomerTask> taskPm = PersisterFactory.create();

        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("n_crm_customer_task");
            updateSql.set("allocation_type", allocationType.getValue());
            updateSql.where("id=?");
        }
        String cmdText = updateSql.toSQL();

        QueryParameters qps = new QueryParameters();
        qps.add("id", taskId, Types.INTEGER);

        taskPm.executeNonQuery(cmdText, qps);
    }
}

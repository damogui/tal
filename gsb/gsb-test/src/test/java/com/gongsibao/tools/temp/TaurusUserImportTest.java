package com.gongsibao.tools.temp;

import java.io.File;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.taurus.dic.PaymentType;
import com.gongsibao.entity.taurus.dic.WalletType;
import com.gongsibao.taurus.base.IUserService;

public class TaurusUserImportTest {

	IUserService userService = ServiceFactory.create(IUserService.class);

	@Test
	public void run() {

		importExcel("D:/user1.xls");
	}

	/**
	 * @param path
	 * @param customerSourceId
	 * @param customerCycleId
	 * @param customerQeueId
	 */
	public void importExcel(String path) {
		int i;
		int j=0;
		Sheet sheet;
		Workbook book;
		User dto = null;
		try {
			book = Workbook.getWorkbook(new File(path));
			sheet = book.getSheet(0);
			int rsRows = sheet.getRows();

			System.out.println(path + "：" + rsRows);
			for (i = 1; i < rsRows; i++) {

				dto = createEntity(sheet.getRow(0), sheet.getRow(i));
				if (dto != null) {

					System.out.println("手机号:" + dto.getMobile());
					User oldUser = isExit(dto.getMobile());
					if(oldUser == null){

						j++;
						dto.setCreateTime(new Date());
						userService.save(dto);
					}else{
						
						oldUser.setAmount(dto.getAmount());
						oldUser.setRemark(dto.getRemark());
						
						List<UserWalletLog> logs = dto.getWalletLogs();
						for(UserWalletLog log : logs){
							
							log.setUserId(oldUser.getId());
						}
						oldUser.setWalletLogs(logs);
						userService.save(oldUser);
						System.err.println("已存在:" + dto.getMobile());
					}
					System.err.println("合计:" + j);
				}
			}
			System.err.println("合计:" + i);
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误:" + e.getMessage());
		}
	}

	private User createEntity(Cell[] titleCell, Cell[] valueCell) {

		String title = "";
		User entity = new User();{
			
			entity.toNew();
			entity.setWalletLogs(new ArrayList<UserWalletLog>());
		}

		for (int i = 0; i < 6; i++) {

			if (titleCell[i].getContents() != null) {

				title = titleCell[i].getContents().trim();
			}

			System.out.print("-" + title + "-");
			String value = valueCell[i].getContents();

			
			if (title.equals("联系方式") && StringManager.isNullOrEmpty(value.trim())) {
				
				return null;
			}
			
			if (StringManager.isNullOrEmpty(value)) {

				value = null;
				continue;
			}
			
			if (title.equals("联系方式")) {
				
				if(!StringManager.isNullOrEmpty(value.trim())){

					entity.setMobile(value.trim());
				}
			} else if (title.equals("联系人")) {

//				String remark=entity.getRemark()+"，"+value;
				entity.setRemark(value);
				
			} else if (title.equals("公司名称")) {

				String remark=entity.getRemark()+"，"+value;
				entity.setRemark(remark);
			} else if (title.equals("金牛座充值金额")) {

				Integer price =Integer.parseInt(value)*100;
				UserWalletLog log = new UserWalletLog();{
					log.toNew();
					log.setPrice(price);
					log.setType(WalletType.RECHARGE);
					log.setPaymentType(PaymentType.UNKNOWN);
					log.setUndone(false);
				}
				
				entity.getWalletLogs().add(log);
				
			} else if (title.equals("返利金额")) {
				
				Integer price =Integer.parseInt(value)*100;
				UserWalletLog log = new UserWalletLog();{
					log.toNew();
					log.setPrice(price);
					log.setType(WalletType.DISCOUNT);
					log.setPaymentType(PaymentType.UNKNOWN);
					log.setUndone(false);
				}
				entity.getWalletLogs().add(log);
				
			} 
			else if (title.equals("总金额")) {

				int amount =Integer.parseInt(value)*100;
				entity.setAmount(amount);
			}
		}

		return entity;
	}

	/**
	 * @param phone
	 * @return
	 */
	public User isExit(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(User.class);
			oql.setSelects("*");
			oql.setFilter("mobile = ?");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("@mobile", mobile, Types.VARCHAR, true);
		oql.setParameters(qps);
		return userService.queryFirst(oql);
	}

}

package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.json.EnumResultJson;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.u8.base.ISetOfBooksService;

public class OrderPayController {

	public List<EnumResultJson> querySetOfBooksList() {

		Oql oql = new Oql();
		{
			oql.setType(SetOfBooks.class);
			oql.setSelects("id,name");
		}

		ISetOfBooksService setOfBooksService = ServiceFactory.create(ISetOfBooksService.class);
		List<SetOfBooks> list = setOfBooksService.queryList(oql);

		List<EnumResultJson> enumList = new ArrayList<EnumResultJson>();
		for (SetOfBooks sob : list) {

			EnumResultJson enumItem = new EnumResultJson();
			enumItem.setText(sob.getName());
			enumItem.setValue(sob.getId().toString());
			enumList.add(enumItem);
		}
		return enumList;
	}
}

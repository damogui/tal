package com.gongsibao.u8.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.u8.base.ISetOfBooksService;

@Service
public class SetOfBooksService extends PersistableService<SetOfBooks> implements ISetOfBooksService {

	public SetOfBooksService() {
		super();
		this.type = SetOfBooks.class;
	}
}

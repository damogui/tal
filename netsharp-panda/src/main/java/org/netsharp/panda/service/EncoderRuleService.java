package org.netsharp.panda.service;

import org.netsharp.autoencoder.entity.EncoderRule;
import org.netsharp.communication.Service;
import org.netsharp.panda.base.IEncoderRuleService;
import org.netsharp.service.PersistableService;

@Service
public class EncoderRuleService extends PersistableService<EncoderRule> implements IEncoderRuleService {

	public EncoderRuleService() {
		super();
		this.type = EncoderRule.class;
	}
}
package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.panda.entity.PReference;

public interface IPReferenceService extends IPersistableService<PReference> {
//	PReference byEntityId(String entityId);
	PReference byCode(String code);
}

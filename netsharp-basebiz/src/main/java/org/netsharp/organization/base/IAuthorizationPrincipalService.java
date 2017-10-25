package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.Position;

public interface IAuthorizationPrincipalService  extends IPersistableService<AuthorizationPrincipal> {
	
	void addByPost(Organization organization);
	void deleteByPost(Organization organization);

	/**
	 * <p>方法名称：addByPosition</p>
	 * <p>方法描述：根据职务增加授权主体</p>
	 * @param position
	 * @author gaomeng
	 * @since  2016年2月25日
	 * <p> history 2016年2月25日 gaomeng  创建   <p>
	 */
	void addByPosition(Position position);
	
	/**
	 * <p>方法名称：deletePosition</p>
	 * <p>方法描述：根据职务删除授权主体</p>
	 * @param position
	 * @author gaomeng
	 * @since  2016年2月25日
	 * <p> history 2016年2月25日 gaomeng  创建   <p>
	 */
	void deleteByPosition(Position position);
}

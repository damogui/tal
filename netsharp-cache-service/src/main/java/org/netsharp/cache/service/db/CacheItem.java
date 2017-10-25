package org.netsharp.cache.service.db;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

/*数据库缓存时的缓存对象*/
@Table(name = "sys_cache_item")
public class CacheItem extends Persistable {

	private static final long serialVersionUID = 1365000849669061565L;

	@Id
	@Column(size = 200)
	private String id;// 缓存key

	@Column(size = 300)
	private String type;// 缓存对象类型，序列化使用

	@Column(size = 4000)
	// 暂时只有微信的token需要缓存，使用varchar，根据需要可以改造成Text或者Blocb类型
	private String value;// 缓存值

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

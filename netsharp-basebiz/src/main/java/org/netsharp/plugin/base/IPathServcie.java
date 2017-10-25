package org.netsharp.plugin.base;

import org.netsharp.plugin.entity.Pathable;

public interface IPathServcie {
	Pathable byPath(String path,String typeName);
}

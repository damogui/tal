package org.netsharp.persistence;

import org.netsharp.core.Oql;

public interface IQuery {
    TSet query(Oql oql);
}

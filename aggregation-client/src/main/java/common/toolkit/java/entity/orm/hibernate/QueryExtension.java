package common.toolkit.java.entity.orm.hibernate;

import org.hibernate.SQLQuery;

public interface QueryExtension<T> {
    void doExtend(SQLQuery sq);
}

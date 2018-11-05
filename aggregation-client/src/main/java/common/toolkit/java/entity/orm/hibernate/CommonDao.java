package common.toolkit.java.entity.orm.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface CommonDao<T, PK extends Serializable> {
    PK save(final T entity);

    void update(final T entity);

    void saveOrUpdate(final T entity);

    void saveOrUpdate(final Collection<T> entities);

    void delete(final T entity);

    void deleteAll(final Collection<T> entities);

    void deleteById(final PK id);

    T get(final PK id);

    List<T> getAll();

    T load(final PK id);

    void refresh(final T entity);

    void merge(final T entity);

    void merge(String entityName, T entity);

    void evict(final T entity);

    boolean contains(Object entity);

    List<T> findBy(final String propertyName, final Object value);

    T findUniqueBy(final String propertyName, final Object value);

    boolean tableColumnIsExist(String tableName, String columnName);

    // 与Hibernate等具体orm框架相关的将不对外暴露，包括sql的
    /*
     * T load(final PK id, final LockOptions lockOptions);
     * void refresh(T entity, LockOptions lockOptions);
     * <X> List<X> find(final String hql, final Object... values);
     * <X> List<X> find(final String hql, final Map<String, ?> values);
     * <X> X findUnique(final String hql, final Object... values);
     * <X> X findUnique(final String hql, final Map<String, ?> values);
     * int batchExecute(final String hql, final Object... values);
     * int batchExecute(final String hql, final Map<String, ?> values);
     * List<T> find(final Criterion... criterions);
     * T findUnique(final Criterion... criterions);
     */

}

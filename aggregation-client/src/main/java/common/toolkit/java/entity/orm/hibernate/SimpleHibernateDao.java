package common.toolkit.java.entity.orm.hibernate;

import common.toolkit.java.entity.entity.PageEntity;
import common.toolkit.java.entity.exception.DaoException;
import common.toolkit.java.entity.util.ReflectUtils;
import common.toolkit.java.entity.util.StringUtil;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * ClassName: SimpleHibernateDao. <br/>
 * Function: 封装Hibernate原生API的DAO泛型基类.
 * 可在Service层直接使用, 也可以扩展泛型DAO子类使用, 见两个构造函数的注释.
 * 参考Spring2.5自带的Petlinc例子, 取消了HibernateTemplate, 直接使用Hibernate原生API.
 * date: 2014年3月5日 下午5:21:49 <br/>
 * @author Ghlin
 * @version
 * @since JDK 1.6
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateDao<T, PK extends Serializable> implements CommonDao<T, PK> {
    private static final Logger  LOGGER        = Logger.getLogger(SimpleHibernateDao.class);

    private static final Pattern FROM_PATTERN  = Pattern.compile("[Ff][Rr][Oo][Mm]");
    private static final Pattern ORDER_PATTERN = Pattern.compile("[Oo][Rr][Dd][Ee][Rr]\\s*?[Bb][Yy].*");

    protected SessionFactory     sessionFactory;
    protected Class<T>           entityClass;

    /**
     * 用于Dao层子类使用的构造函数.
     * 通过子类的泛型定义取得对象类型Class.
     * eg.
     * public class UserDao extends SimpleHibernateDao<User, Long>
     */
    public SimpleHibernateDao() {
        this.entityClass = ReflectUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 用于用于省略Dao层, 在Service层直接使用通用SimpleHibernateDao的构造函数.
     * 在构造函数中定义对象类型Class.
     * eg.
     * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
     */
    public SimpleHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    /**
     * 取得sessionFactory.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 采用@Autowired按类型注入SessionFactory, 当有多个SesionFactory的时候在子类重载本函数.
     */
    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 获取当前session. <br/>
     * @return Session
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 这里用一句话描述这个方法的作用. <br/>
     * @return Connection
     * @throws DaoException
     */
    public Connection getConnection() throws DaoException {
        try {
            return SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
        } catch (SQLException e) {
            throw new DaoException("获取数据库连接错误", e);
        }
    }

    /**
     * 保存新增对象. <br/>
     * @param entity
     */
    public PK save(final T entity) {
        Assert.notNull(entity, "对象不能为空");
        LOGGER.debug("Save entity: " + entity);
        return (PK) getSession().save(entity);
    }

    /**
     * 保存修改对象. <br/>
     * @param entity
     */
    public void update(final T entity) {
        Assert.notNull(entity, "对象不能为空");
        getSession().update(entity);
        LOGGER.debug("Update entity: " + entity);
    }

    /**
     * 保存或更新实体. <br/>
     * @param entity
     */
    public void saveOrUpdate(final T entity) {
        Assert.notNull(entity, "对象不能为空");
        getSession().saveOrUpdate(entity);
        LOGGER.debug("saveOrUpdate entity: " + entity);
    }

    /**
     * 保存新增或修改的对象. <br/>
     * @param entities
     */
    public void saveOrUpdate(final Collection<T> entities) {
        Assert.notNull(entities, "对象集合不能为空");
        for (T entity : entities) {
            this.saveOrUpdate(entity);
        }
    }

    /**
     * 删除对象. <br/>
     * @param entity
     */
    public void delete(final T entity) {
        Assert.notNull(entity, "对象不能为空");
        getSession().delete(entity);
        LOGGER.debug("delete entity: " + entity);
    }

    /**
     * 删除所有对象. <br/>
     * @param entities
     */
    public void deleteAll(final Collection<T> entities) {
        for (Object entity : entities) {
            getSession().delete(entity);
            getSession().flush();
        }
    }

    /**
     * 按id删除对象.
     */
    public void deleteById(final PK id) {
        Assert.notNull(id, "id不能为空");
        delete(get(id));
        LOGGER.debug("delete entity " + entityClass.getSimpleName() + ", id is " + id);
    }

    /**
     * 按id获取对象(直接返回实体类).
     */
    public T get(final PK id) {
        Assert.notNull(id, "id不能为空");
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 按id获取对象(实体的代理类实例,延迟缓存).
     */
    public T load(final PK id) {
        Assert.notNull(id, "id不能为空");
        return (T) getSession().load(entityClass, id);
    }

    /**
     * 按id用给定的lockOptions获取对象. <br/>
     * @param id
     * @param lockOptions
     * @return
     */
    protected T load(final PK id, final LockOptions lockOptions) {
        Assert.notNull(id, "id不能为空");
        T entity = null;
        if (lockOptions != null) {
            entity = (T) getSession().load(entityClass, id, lockOptions);
        } else {
            entity = (T) getSession().load(entityClass, id);
        }
        return entity;
    }

    /**
     * 刷新对象. <br/>
     * @param entity
     */
    public void refresh(final T entity) {
        Assert.notNull(entity, "实体不能为空");
        getSession().refresh(entity);
        LOGGER.debug("refresh entity: " + entity);
    }

    /**
     * 刷新对象. <br>
     * 参数lockOptions可为null
     * @param entity 操作对象
     * @param lockOptions Hibernate LockOptions
     */
    protected void refresh(T entity, LockOptions lockOptions) {
        if (lockOptions == null) {
            refresh(entity);
        } else {
            getSession().refresh(entity, lockOptions);
        }
    }

    /**
     * 合并修改的对象.
     */
    public void merge(final T entity) {
        Assert.notNull(entity, "entity不能为空");
        getSession().merge(entity);
        LOGGER.debug("merge entity: " + entity);
    }

    /**
     * 如果session中存在相同持久化识别的实例，用给出的对象的状态覆盖持久化实例
     * @param entityName 持久化对象名称
     * @param entity 持久化实例
     */
    public void merge(String entityName, T entity) {
        getSession().merge(entityName, entity);
    }

    /**
     * 将对象变为游离状态.
     */
    public void evict(final T entity) {
        Assert.notNull(entity, "entity不能为空");
        getSession().evict(entity);
        LOGGER.debug("evict entity: " + entity);
    }

    /**
     * clear当前Session.
     */
    public void clear() {
        getSession().clear();
    }

    /**
     * 获取全部对象.
     */
    public List<T> getAll() {
        return find();
    }

    /**
     * 获取全部对象, 支持按属性行序.
     */
    public List<T> getAll(String orderByProperty, boolean isAsc) {
        Criteria c = createCriteria();
        if (isAsc) {
            c.addOrder(Order.asc(orderByProperty));
        } else {
            c.addOrder(Order.desc(orderByProperty));
        }
        return c.list();
    }

    /**
     * 判断entity实例是否已经与session缓存关联,是返回true,否则返回false
     * @param entity 实例
     * @return boolean
     */
    public boolean contains(Object entity) {
        return getSession().contains(entity);
    }

    /**
     * 按属性查找对象列表, 匹配方式为相等.
     */
    public List<T> findBy(final String propertyName, final Object value) {
        Assert.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return find(criterion);
    }

    /**
     * 按属性查找唯一对象, 匹配方式为相等.
     */
    public T findUniqueBy(final String propertyName, final Object value) {
        Assert.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T) createCriteria(criterion).uniqueResult();
    }

    /**
     * 按HQL查询对象列表.
     * @param values 数量可变的参数,按顺序绑定.
     */
    protected <X> List<X> find(final String hql, final boolean isHql, final Object... values) {
        return createQuery(hql, isHql, values).list();
    }

    /**
     * 按HQL查询对象列表.
     * 使用方式： xxxDao.<X> findUnique(hql, values);
     * @param values 命名参数,按名称绑定.
     */
    protected <X> List<X> find(final String hql, final boolean isHql, final Map<String, ?> values) {
        return createQuery(hql, isHql, values).list();
    }

    /**
     * 按HQL查询唯一对象.
     * @param values 数量可变的参数,按顺序绑定.
     */
    protected <X> X findUnique(final String hql, final boolean isHql, final Object... values) {
        return (X) createQuery(hql, isHql, values).uniqueResult();
    }

    /**
     * 按HQL查询唯一对象.
     * @param values 命名参数,按名称绑定.
     */
    protected <X> X findUnique(final String hql, final boolean isHql, final Map<String, ?> values) {
        return (X) createQuery(hql, isHql, values).uniqueResult();
    }

    /**
     * 执行HQL进行批量修改/删除操作.
     * @param values 数量可变的参数,按顺序绑定.
     * @return 更新记录数.
     */
    protected int batchExecute(final String hql, final boolean isHql, final Object... values) {
        return createQuery(hql, isHql, values).executeUpdate();
    }

    /**
     * 执行HQL进行批量修改/删除操作.
     * @param values 命名参数,按名称绑定.
     * @return 更新记录数.
     */
    protected int batchExecute(final String hql, final boolean isHql, final Map<String, ?> values) {
        return createQuery(hql, isHql, values).executeUpdate();
    }

    /**
     * 按Criteria查询对象列表.
     * @param criterions 数量可变的Criterion.
     */
    protected List<T> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    /**
     * 按Criteria查询唯一对象.
     * @param criterions 数量可变的Criterion.
     */
    protected T findUnique(final Criterion... criterions) {
        return (T) createCriteria(criterions).uniqueResult();
    }

    /**
     * 根据Criterion条件创建Criteria.
     * 与find()函数可进行更加灵活的操作.
     * @param criterions 数量可变的Criterion.
     */
    protected Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 与find()函数可进行更加灵活的操作.
     * @param values 数量可变的参数,按顺序绑定.
     */
    protected Query createQuery(final String queryString, final boolean isHql, final Object... values) {
        Assert.hasText(queryString, "queryString不能为空");
        Query query = isHql ? getSession().createQuery(queryString) : getSession().createSQLQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 与find()函数可进行更加灵活的操作.
     * @param values 命名参数,按名称绑定.
     */
    protected Query createQuery(final String queryString, final boolean isHql, final Map<String, ?> values) {
        Assert.hasText(queryString, "queryString不能为空");
        Query query = isHql ? getSession().createQuery(queryString) : getSession().createSQLQuery(queryString);
        // if (values != null) {
        // query.setProperties(values);
        // }
        fillParams(query, values);
        return query;
    }

    /**
     * 根据查询Hql与参数列表创建Query对象.
     * @param hql
     * @param values 命名参数,按名称绑定.
     * @return
     */
    protected Query createHqlQuery(final String hql, final Object... values) {
        return createQuery(hql, true, values);
    }

    /**
     * 根据查询Hql与参数列表创建Query对象.
     * @param hql
     * @param values 命名参数,按名称绑定.
     * @return
     */
    protected Query createHqlQuery(final String hql, final Map<String, ?> values) {
        return createQuery(hql, true, values);
    }

    // 原生SQL
    /**
     * 根据查询SQL与参数列表创建SQLQuery对象.
     * @param values 数量可变的参数,按顺序绑定.
     */
    protected SQLQuery createSQLQuery(final String sql, final Object... values) {
        Assert.hasText(sql, "sql不能为空");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                sqlQuery.setParameter(i, values[i]);
            }
        }
        return sqlQuery;
    }

    /**
     * 根据查询SQL与参数列表创建SQLQuery对象.
     * @param sql
     * @param values 命名参数,按名称绑定.
     * @return
     */
    protected SQLQuery createSQLQuery(final String sql, final Map<String, ?> values) {
        Assert.hasText(sql, "sql不能为空");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        fillParams(sqlQuery, values);
        return sqlQuery;
    }

    /**
     * 执行原生sql.
     */
    protected void executeJdbcUpdate(String sql) throws DaoException {
        Assert.hasText(sql, "sql不能为空");
        try {
            getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * 执行原生sql查询.
     */
    protected ResultSet executeJdbcQuery(String sql) throws DaoException {
        Assert.hasText(sql, "sql不能为空");
        try {
            return getConnection().prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * 查询分页, 每次都重新计算记录总数.
     * 使用方式: xxxDao.<Object> pageByHsql(hql, page, params, true)
     * @author ghlin
     * @param hql hql
     * @param params 条件
     * @param page page
     * //@param isHql 是否是hql
     * @param <X> 泛型
     * @return
     */
    protected final <X> PageEntity<X> pageByHql(final String hql, final PageEntity<X> page,
            final Map<String, Object> params) {
        return pageByHql(hql, page, params, true);
    }

    /**
     * 查询分页.
     * 使用方式: xxxDao.<Object> pageByHsql(hql, page, params, true)
     * @author ghlin
     * @param hql
     * @param page
     * @param params
     * @param recount 是否重现统计记录总数
     * @return
     */
    protected final <X> PageEntity<X> pageByHql(final String hql, final PageEntity<X> page,
            final Map<String, Object> params, boolean recount) {
        checkArgument(!StringUtil.isEmpty(hql), "sql can not be empty");
        Query countQuery = this.createQuery(generateCountHql(hql), true, params);
        Query query = this.createQuery(hql, true, params);

        return doPage(page, countQuery, query, recount);
    }

    /**
     * 查询分页, 每次都重新计算记录数量.
     * 使用方式: xxxDao.<Object> pageByHsql(hql, page, params, true)
     * @author ghlin
     * @param hql hql
     * @param params 条件
     * @param page page
     * @param //isHql 是否是hql
     * @param <X> 泛型
     * @return
     */
    protected final <X> PageEntity<X> pageBySql(final String hql, final PageEntity<X> page, QueryExtension<X> qe,
                                                final Map<String, Object> params) {
        return pageBySql(hql, page, qe, params, true);
    }

    /**
     * 这里用一句话描述这个方法的作用. <br/>
     * @author ghlin
     * @param hql
     * @param page
     * @param qe
     * @param params
     * @param recount 是否重新计算记录总数
     * @return
     */
    protected final <X> PageEntity<X> pageBySql(final String hql, final PageEntity<X> page, QueryExtension<X> qe,
            final Map<String, Object> params, boolean recount) {
        checkArgument(!StringUtil.isEmpty(hql), "sql can not be empty");
        Query countQuery = this.createQuery(generateCountHql(hql), false, params);
        SQLQuery query = (SQLQuery) this.createQuery(hql, false, params);
        qe.doExtend(query);

        return doPage(page, countQuery, query, recount);
    }

    /**
     * 重载查询分页, 每次都重新计算记录数量.
     * 使用方式: xxxDao.<Object> pageByHql(hql, page, params)
     * @author ghlin
     * @param hql hql
     * @param params 条件
     * @param page page
     * @param //isHql 是否是hql
     * @param <X> 泛型
     * @return
     */
    protected final <X> PageEntity<X> pageByHql(final String hql, final PageEntity<X> page, final Object... params) {
        checkArgument(!StringUtil.isEmpty(hql), "sql can not be empty");
        Query countQuery = this.createQuery(generateCountHql(hql), true, params);
        Query query = this.createQuery(hql, true, params);

        return doPage(page, countQuery, query, true);
    }

    /**
     * 增加分页查询扩展. <br/>
     * @author ghlin
     * @param sql
     * @param page
     * @param qe
     * @param params
     * @return
     */
    protected final <X> PageEntity<X> pageBySql(final String sql, final PageEntity<X> page, QueryExtension<X> qe,
            final Object... params) {
        checkArgument(!StringUtil.isEmpty(sql), "sql can not be empty");
        Query countQuery = this.createQuery(generateCountHql(sql), false, params);
        SQLQuery query = (SQLQuery) this.createQuery(sql, false, params);

        qe.doExtend(query);

        return doPage(page, countQuery, query, true);
    }

    public boolean tableColumnIsExist(String tableName, String columnName) {
        String sql = "SELECT COUNT (*) count FROM USER_TAB_COLUMNS WHERE TABLE_NAME = ? AND column_name = ?";
        Object obj = this.createSQLQuery(sql, tableName, columnName).uniqueResult();
        if (BigDecimal.ZERO.equals(new BigDecimal(obj + ""))) {
            return false;
        }
        return true;
    }

    /**
     * 获取实体名称
     * @return String
     */
    protected String getEntityName() {
        ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);
        return meta.getEntityName();
    }

    /**
     * 填充参数. <br/>
     * @author ghlin
     * @param query query
     * @param params 条件
     */
    private void fillParams(final Query query, final Map<String, ?> params) {
        if (null == query || null == params) {
            return;
        }
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            if (null == entry.getValue() || StringUtil.isEmpty(entry.getValue().toString().trim())) {
                continue;
            }
            if (entry.getValue() instanceof Collection<?>) {
                query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
            } else if (entry.getValue() instanceof Object[]) {
                query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
            } else {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 根据sql/hql生成对应的count语句. <br/>
     * @author ghlin
     * @param querySql querySql
     * @return String
     */
    private String generateCountHql(final String querySql) {
        // 去掉order by
        Matcher orderMatcher = ORDER_PATTERN.matcher(querySql);
        final String newquerySql = orderMatcher.replaceAll("");

        StringBuffer sb = new StringBuffer("select count(*) ");
        Matcher matcher = FROM_PATTERN.matcher(newquerySql);
        if (matcher.find()) {
            sb.append(newquerySql.substring(matcher.start()));
            return sb.toString();
        }

        return newquerySql;
    }

    /**
     * 进行分页查询. <br/>
     * @author ghlin
     * @param page
     * @param countQuery
     * @param query
     * @param recount 是否重现计算记录总数
     * @return
     */
    private <X> PageEntity<X> doPage(final PageEntity<X> page, Query countQuery, Query query, boolean recount) {
        if (recount) {
            Number count = (Number) countQuery.uniqueResult();
            page.setTotalResults(count == null ? 0 : count.longValue()); // 总记录数
        }

        query.setFirstResult(page.getPageNum());
        query.setMaxResults(page.getPageSize());

        List<X> results = query.list();
        page.setPageSize(results.size());// 当前页面记录数
        page.setTotalPages(); // 总页数
        page.setResults(results); // 分页数据

        return page;
    }
}

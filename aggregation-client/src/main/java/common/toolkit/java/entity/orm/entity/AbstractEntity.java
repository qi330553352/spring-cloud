package common.toolkit.java.entity.orm.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 抽象实体基类.
 * @author 尔演&Eryan eryanwcp@gmail.com
 * @date 2013-6-5 下午5:05:56
 * @version 1.0
 * @param <ID>
 */
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    public abstract ID getId();

    /**
     * 设置主键ID.
     * @param id 主键ID
     */
    public abstract void setId(final ID id);

    /**
     * 是否是新创建的对象.
     * @return
     */
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> that = (AbstractEntity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
package common.toolkit.java.entity.orm.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 统一定义entity基类. <br>
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. <br>
 * 子类可重载getId()函数重定义id的列名映射和生成策略. <br>
 * @author : 尔演&Eryan eryanwcp@gmail.com
 * @date : 2012-12-21 上午11:12:07
 */
@MappedSuperclass
public abstract class UUIDEntity extends AbstractEntity<String> {

    /**
     * 主键ID
     */
    public String  id;

    /**
     * 操作版本(乐观锁,用于并发控制)
     */
    public Integer version;

    public UUIDEntity() {
    }

    /**
     * Hibernate3.6以后,UUIDHexGenerator(uuid)已不推荐使用，改用UUIDGenerator(org.hibernate
     * .id.UUIDGenerator)
     */
    @Id
    @Column(name = "ID", updatable = false, length = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
    public String getId() {
        return id;
    }

    /**
     * 设置 主键ID
     * @param id
     *            主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 版本号(乐观锁)
     */
    @Version
    @Column(name = "VER")
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置 版本号(乐观锁)
     * @param version
     *            版本号(乐观锁)
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

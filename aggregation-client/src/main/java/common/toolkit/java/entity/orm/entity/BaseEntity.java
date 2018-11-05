package common.toolkit.java.entity.orm.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * 统一定义entity基类. <br>
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. <br>
 * 子类可重载getId()函数重定义id的列名映射和生成策略. <br>
 * 2012-12-15 wencp:新加并发控制(乐观锁,用于并发控制)、数据更新时间、操作用户ID.
 * @author : 尔演&Eryan eryanwcp@gmail.com
 * @date : 2012-12-21 上午11:12:07
 */
// @Column(name="...") 该属性对应表中的字段是什么，没有name表示一样
// @Table 对象与表映射
// @UniqueConstraint 唯一约束
// @Version 方法和字段级，乐观锁用法，返回数字和timestamp，数字为首选
// @Transient 暂态属性，表示不需要处理
// @Basic 最基本的注释。有两个属性：fetch是否延迟加载，optional是否允许null
// @Enumerated 枚举类型
// @Temporal 日期转换。默认转换Timestamp
// @Lob 通常与@Basic同时使用，提高访问速度。
// @Embeddable 类级，表可嵌入的
// @Embedded 方法字段级，表被嵌入的对象和@Embeddable一起使用
// @AttributeOverrides 属性重写
// @AttributeOverride 属性重写的内容和@AttributeOverrides一起嵌套使用
// @SecondaryTables 多个表格映射
// @SecondaryTable 定义辅助表格映射和@SecondaryTables一起嵌套使用
// @GeneratedValue 标识符生成策略，默认Auto
// JPA 基类的标识
// @AttributeOverride(name = "id", column = @Column(name = "base_id"))
@MappedSuperclass
public class BaseEntity extends AutoEntity implements Serializable, Cloneable {
    private static final long serialVersionUID = 2142201445199112425L;

    /**
     * 操作版本(乐观锁,用于并发控制)
     */
    protected Integer         version;

    /**
     * 记录更新时间
     */
    protected Date            updateTime;

    public BaseEntity() {
        super();
    }

    /**
     * 版本号(乐观锁)
     */
    @Version
    @Column(name = "VERSION")
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

    /**
     * 最后更新时间.
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间. <br/>
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BaseEntity clone() {
        BaseEntity o = null;
        try {
            o = (BaseEntity) super.clone();// Object中的clone()识别出你要复制的是哪一个对象。
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

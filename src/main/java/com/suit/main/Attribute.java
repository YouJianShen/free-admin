package com.suit.main;

import com.suit.base.domain.BaseModel;
import com.suit.model.core.common.EnumConstants.AttributeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attribute")
public class Attribute extends BaseModel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 拥有类id
     */
    @Column(name="belongs_id")
    private String belongsId;

    /**
     * 属性名称
     */
    @Column(name="name",nullable = false)
    private String name;

    /**
     * 属性类型
     */
    @Column(name="type")
    private AttributeType type = AttributeType.STRING;

    /**
     * 字段长度
     */
    @Column(name="length")
    private int length;

    /**
     * 是否允许为空
     */
    @Column(name="allow_null")
    private boolean allowNull = true;

    /**
     * 默认值
     */
    @Column(name="default_value",nullable = false)
    private String defaultValue;

    public String getBelongsId() {
        return belongsId;
    }

    public void setBelongsId(String belongsId) {
        this.belongsId = belongsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}

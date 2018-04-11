package com.suit.main;

import javax.persistence.*;

import com.suit.base.domain.BaseModel;

import java.util.List;

@Entity
@Table(name = "class")
public class Class extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 类名称
     */
    @Column(name="name",length = 40)
    private String name;

    /**
     * 类描述
     */
    @Column(name="class_desc",length = 100)
    private String ClassDesc;

    /**
     * 父类id
     */
    @Column(name="parent_id")
    private long parentId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Attribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassDesc() {
        return ClassDesc;
    }

    public void setClassDesc(String classDesc) {
        ClassDesc = classDesc;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}

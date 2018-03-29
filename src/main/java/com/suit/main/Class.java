package com.suit.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.suit.base.domain.BaseModel;

@Entity
@Table(name = "class")
public class Class extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 栏目名称
     */
    @Column(name="class_name",length = 40)
    private static String ClassName;

    /**
     * 父级id
     */
    @Column(name="parent_id")
    private static long parentId;

}

package com.suit.main.service.impl;

import com.suit.base.domain.BaseModel;
import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Attribute;
import com.suit.main.Class;
import com.suit.main.dao.ClassDao;
import com.suit.main.service.AttributeService;
import com.suit.main.service.ClassService;
import com.suit.model.core.common.EnumConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ClassService")
@Transactional(readOnly = true)
public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addDBClass(Class classInfo) throws CoreException {
        if (findUniqueBy("name", classInfo.getName()) == null) {
            classInfo = classDao.save(classInfo);
            attributeService.createAttributes(classInfo.getAttributes(), classInfo.getId());
            createDB(classInfo);
        } else {
            throw new CoreException("0","已存在名称为" + classInfo.getName() + "的栏目");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void createDB(Class classInfo) throws CoreException {
        String sql = "create table if not exists model_" + classInfo.getName() + "(";

        List<Attribute> listAttr = classInfo.getAttributes();

        //添加主键
        sql += "id varchar(255) primary key,";

        for (int i = 0; i < listAttr.size(); i++) {
            Attribute item = listAttr.get(i);
            sql += "" + item.getName() + " ";
            switch (item.getType()) {
                case INT:
                    sql += EnumConstants.AttributeType.INT.getName() + "(" + EnumConstants.AttributeType.INT.getLength() + ")";
                    break;
                case DATE:
                    sql += EnumConstants.AttributeType.DATE.getName();
                    break;
                case TIME:
                    sql += EnumConstants.AttributeType.TIME.getName();
                    break;
                case DATETIME:
                    sql += EnumConstants.AttributeType.DATETIME.getName();
                    break;
                case FLOAT:
                    sql += EnumConstants.AttributeType.FLOAT.getName();
                    break;
                case DOUBLE:
                    sql += EnumConstants.AttributeType.DOUBLE.getName();
                    break;
                case STRING:
                    sql += EnumConstants.AttributeType.STRING.getName() + "(" + item.getLength() == null ? EnumConstants.AttributeType.STRING.getLength() : item.getLength();
                    break;
                case BOOLEAN:
                    sql += EnumConstants.AttributeType.BOOLEAN.getName();
                    break;
                default:
                    sql += "varchar(20) default ''";
                    break;
            }

            if (!item.isAllowNull()) {
                sql += " not null";
            }

            sql += " default " + item.getDefaultValue() + "),";
        }

        sql = sql.substring(0, sql.length() - 1) + ";";

        jdbcTemplate.execute(sql);
    }

    @Override
    protected BaseDao<Class> getBaseDao() {
        return classDao;
    }

    @Override
    protected java.lang.Class getEntityClass() {
        return Class.class;
    }
}

package com.suit.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.suit.base.domain.BaseModel;
import com.suit.core.enquiry.Enquiry;
import com.suit.core.exception.CoreException;
import com.suit.core.exception.ECodeUtil;
import com.suit.core.exception.ErrorConstant;
import com.suit.core.service.BaseService;
import com.suit.jpa.core.BaseDao;

public abstract class BaseServiceImpl<T extends BaseModel> implements
		BaseService<T> {
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType, String direction) {

		Sort sort = null;
		if (StringUtils.isEmpty(sortType) || "auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("asc".equals(direction)) {
			sort = new Sort(Direction.ASC, sortType);
		} else if ("desc".equals(direction)) {
			sort = new Sort(Direction.DESC, sortType);
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	private Specification<T> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), this.getEntityClass());
		return spec;
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void delete(String id) throws CoreException {
		if (StringUtils.isEmpty(id)) {
			throw new CoreException(
					ECodeUtil.getCommError(ErrorConstant.ERROR_PARAM_IS_NULL));
		}

		T dbentity = getBaseDao().findOne(id);
		if (dbentity == null) {
			throw new CoreException(
					ECodeUtil.getCommError(ErrorConstant.ERROR_ENTITY_IS_NULL));
		}

		getBaseDao().delete(id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void delete(T entity) throws CoreException {
		if (entity == null || entity.getId() == null) {
			throw new CoreException(
					ECodeUtil.getCommError(ErrorConstant.ERROR_PARAM_IS_NULL));
		}

		T dbentity = getBaseDao().findOne(entity.getId());
		if (dbentity == null) {
			throw new CoreException(
					ECodeUtil.getCommError(ErrorConstant.ERROR_ENTITY_IS_NULL));
		}
		getBaseDao().delete(entity.getId());
	}

	public List<T> findBy(String propertyName, Object value)
			throws CoreException {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_" + propertyName, value);
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), getEntityClass());
		return this.getBaseDao().findAll(spec);
	}

	public List<T> findBy(String[] propertyNames, Object[] values)
			throws CoreException {
		try {
			Map<String, Object> searchParams = new HashMap<String, Object>();
			for (int i = 0; i < propertyNames.length; i++) {
				String propertyName = propertyNames[i];
				Object value = values[i];
				searchParams.put("EQ_" + propertyName, value);
			}

			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			Specification<T> spec = DynamicSpecifications.bySearchFilter(
					filters.values(), getEntityClass());
			return this.getBaseDao().findAll(spec);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException(
					ECodeUtil.getCommError(ErrorConstant.ERROR_OPERATE));
		}
	}

	public T findUniqueBy(String propertyName, Object value)
			throws CoreException {
		try {
			Map<String, Object> searchParams = new HashMap<String, Object>();
			searchParams.put("EQ_" + propertyName, value);
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			Specification<T> spec = DynamicSpecifications.bySearchFilter(
					filters.values(), getEntityClass());
			return this.getBaseDao().findOne(spec);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException(
					ECodeUtil.getCommError(ErrorConstant.ERROR_OPERATE));
		}
	}

	public T findUniqueBy(String[] propertyNames, Object[] values)
			throws CoreException {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			Object value = values[i];
			searchParams.put("EQ_" + propertyName, value);
		}

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), getEntityClass());
		return this.getBaseDao().findOne(spec);
	}

	public List<T> getAll() throws CoreException {
		List<T> resList = new ArrayList<T>();
		for (T entity : this.getBaseDao().findAll()) {
			resList.add(entity);
		}
		return resList;
	}

	public List<T> getAll(Map<String, Object> map) throws CoreException {
		Map<String, SearchFilter> filters = SearchFilter.parse(map);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), this.getEntityClass());
		return this.getBaseDao().findAll(spec);
	}

	abstract protected BaseDao<T> getBaseDao();

	public T getById(String id) throws CoreException {
		return this.getBaseDao().findOne(id);
	}

	abstract protected Class getEntityClass();

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void multiDelete(String[] ids) throws CoreException {
		List<String> idList = Arrays.asList(ids);
		Iterable<T> iterable = getBaseDao().findAll(idList);
		if (iterable != null) {
			this.getBaseDao().delete(iterable);
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public T save(T entity) throws CoreException {
		Date nowDate = new Date();
		if (StringUtils.isNotEmpty(entity.getId())) {// 更新：以当前时间作为更新时间
			entity.setLastUpdateTime(nowDate);
		} else {// 保存：以当前时间作为创建时间和更新时间
			entity.setCreateTime(nowDate);
			entity.setLastUpdateTime(nowDate);
		}
		return getBaseDao().save(entity);
	}

	public void save(List<T> entityList) throws CoreException {
		Date nowDate = new Date();
		for (T entity : entityList) {
			if (entity.getId() == null) {// 更新：以当前时间作为更新时间
				entity.setLastUpdateTime(nowDate);
			} else {// 保存：以当前时间作为创建时间和更新时间
				entity.setCreateTime(nowDate);
				entity.setLastUpdateTime(nowDate);
			}
		}

		getBaseDao().save(entityList);
	}

	public Page<T> search(Enquiry info) throws CoreException {
		PageRequest pageRequest = this.buildPageRequest(info.getPageNumber(),
				info.getPagzSize(), info.getSortType(), info.getDirection());
		Specification<T> spec = this.buildSpecification(info.getSearchParams());

		return getBaseDao().findAll(spec, pageRequest);
	}

	public List<T> search(Enquiry info, Sort sort) throws CoreException {
		// PageRequest pageRequest = new PageRequest(info.getPageNumber() - 1,
		// info.getPagzSize(), sort);
		Specification<T> spec = this.buildSpecification(info.getSearchParams());
		return getBaseDao().findAll(spec, sort);
	}

}

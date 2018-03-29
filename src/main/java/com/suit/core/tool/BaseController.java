package com.suit.core.tool;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import oracle.sql.RAW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springside.modules.web.Servlets;

import com.suit.base.domain.BaseModel;
import com.suit.core.constant.WebConstant;
import com.suit.core.enquiry.Enquiry;
import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.core.web.vo.JsonPageData;
import com.suit.core.web.vo.OperateStatus;
import com.suit.util.DateJsonValueProcessor;
import com.suit.util.JsonUtil;

public abstract class BaseController<T extends BaseModel> {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	/**
	 * 获得EntityManager类，必须在子类实现
	 */
	abstract protected BaseService<T> getBaseService();

	protected String create(T entity) {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			entity = getBaseService().save(entity);
			status.setData(entity);
		} catch (CoreException e) {
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg(e.getMessage());
			status.setCode(e.getCode());
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg("类型已经选择,请不要重复创建!");
		}catch(Exception e){
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg(WebConstant.COMMON_FAIL_MSG);
		}
		return JsonUtil.getJSONSerializer(getIncludeRefProperties(),
				getExcludeProperties(), WebConstant.DATEFORMAT_TIME).serialize(
				status);
	}

	protected String update(T entity) {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			getBaseService().save(entity);
			status.setData(entity);
		} catch (CoreException e) {
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg(e.getMessage());
			status.setCode(e.getCode());
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg("类型已经选择,请不要重复创建!");
		}catch(Exception e){
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg(WebConstant.COMMON_FAIL_MSG);
		}
		return JsonUtil.getJSONSerializer(getIncludeRefProperties(),
				getExcludeProperties(), WebConstant.DATEFORMAT_TIME).serialize(
				status);
	}

	protected String delete(String id) {
		OperateStatus status = new OperateStatus(false,
				WebConstant.COMMON_FAIL_MSG);
		try {
			getBaseService().delete(id);
			status.setSuccess(true);
			status.setMsg(WebConstant.COMMON_SUCCESS_MSG);
		} catch (CoreException e) {
			logger.error(e.getMessage());
			status.setSuccess(false);
			status.setMsg(WebConstant.COMMON_FAIL_MSG);
			status.setCode(e.getCode());
		}
		return JsonUtil.getJSONSerializer(getIncludeRefProperties(),
				getExcludeProperties(), WebConstant.DATEFORMAT_TIME).serialize(
				status);
	}

	public static void main(String[] args) throws SQLException {
		String id = "4028FE81518087F90151808803EB0000";
		RAW rawid = RAW.newRAW(id);
		System.out.println(rawid);
	}

	protected String list(int pageNumber, int pageSize, String sortType,
			String direction, ServletRequest request) {
		try {
			Map<String, Object> searchParams = Servlets
					.getParametersStartingWith(request, "search_");
			// 封装查询条件
			Enquiry info = new Enquiry();
			info.setSearchParams(searchParams);
			info.setPageNumber(pageNumber);
			info.setPagzSize(pageSize);
			info.setSortType(sortType);
			info.setDirection(direction);

			// 分页查询并排序
			Page<T> page = getBaseService().search(info);
			List<T> entitylist = page.getContent();

			// 封装响应数据
			JsonPageData<T> data = new JsonPageData<T>(page.getNumber() + 1,
					page.getSize(), page.getTotalElements(), entitylist,
					page.getTotalPages());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(getExcludeProperties());
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonConfig.registerJsonValueProcessor(Date.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			return JSONObject.fromObject(data, jsonConfig).toString();
		} catch (CoreException e) {
			logger.error(e.getMessage());
		}
		return "[]";
	}

	protected JsonPageData<T> listForJsonPageData(int pageNumber, int pageSize,
			String sortType, String direction, ServletRequest request) {
		try {
			Map<String, Object> searchParams = Servlets
					.getParametersStartingWith(request, "search_");
			// 封装查询条件
			Enquiry info = new Enquiry();
			info.setSearchParams(searchParams);
			info.setPageNumber(pageNumber);
			info.setPagzSize(pageSize);
			info.setSortType(sortType);
			info.setDirection(direction);

			// 分页查询并排序
			Page<T> page = getBaseService().search(info);
			List<T> entitylist = page.getContent();

			// 封装响应数据
			JsonPageData<T> data = new JsonPageData<T>(page.getNumber() + 1,
					page.getSize(), page.getTotalElements(), entitylist,
					page.getTotalPages());

			return data;
		} catch (CoreException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	protected String list(int pageNumber, int pageSize, String sortType,
			String direction, ServletRequest request,
			Map<String, Object> myParams) {
		try {
			Map<String, Object> searchParams = Servlets
					.getParametersStartingWith(request, "search_");
			searchParams.putAll(myParams);

			// 封装查询条件
			Enquiry info = new Enquiry();
			info.setSearchParams(searchParams);
			info.setPageNumber(pageNumber);
			info.setPagzSize(pageSize);
			info.setSortType(sortType);
			info.setDirection(direction);

			// 分页查询并排序
			Page<T> page = getBaseService().search(info);
			List<T> entitylist = page.getContent();

			// 封装响应数据
			JsonPageData<T> data = new JsonPageData<T>(page.getNumber() + 1,
					page.getSize(), page.getTotalElements(), entitylist,
					page.getTotalPages());

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(getExcludeProperties());
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonConfig.registerJsonValueProcessor(Date.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			return JSONObject.fromObject(data, jsonConfig).toString();
		} catch (CoreException e) {
			logger.error(e.getMessage());
		}
		return "[]";
	}

	protected JsonPageData<T> listForJsonPageData(int pageNumber, int pageSize,
			String sortType, String direction, ServletRequest request,
			Map<String, Object> myParams) {
		try {
			Map<String, Object> searchParams = Servlets
					.getParametersStartingWith(request, "search_");
			searchParams.putAll(myParams);

			// 封装查询条件
			Enquiry info = new Enquiry();
			info.setSearchParams(searchParams);
			info.setPageNumber(pageNumber);
			info.setPagzSize(pageSize);
			info.setSortType(sortType);
			info.setDirection(direction);

			// 分页查询并排序
			Page<T> page = getBaseService().search(info);
			List<T> entitylist = page.getContent();

			// 封装响应数据
			JsonPageData<T> data = new JsonPageData<T>(page.getNumber() + 1,
					page.getSize(), page.getTotalElements(), entitylist,
					page.getTotalPages());

			return data;
		} catch (CoreException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 包含字段数组，使用时在子类重写
	 * 
	 * @return
	 */
	protected String[] getIncludeRefProperties() {
		return new String[] { "" };
	}

	/**
	 * 排除字段数组，使用时在子类重写
	 * 
	 * @return
	 */
	protected String[] getExcludeProperties() {
		return new String[] { "createTime", "createCompanyId",
				"lastUpdateTime", "createUserId","rows.status" };
	}

}

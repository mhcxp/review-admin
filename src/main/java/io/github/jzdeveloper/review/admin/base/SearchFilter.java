package io.github.jzdeveloper.review.admin.base;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

/**
 * 代码来源于springside4
 * 
 * 实现前台查询内容 转换成 后台hibernate可识别的
 * 
 * @author mazhyb
 */
public class SearchFilter {

	public enum Operator{
		EQ,LIKE,GT,LT,GTE,LTE
	}
	
	private String fieldName;
	private Object value;
	private Operator operator;
	
	public SearchFilter(String fieldName,Operator operator,Object value){
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public Object getValue() {
		return value;
	}
	
	
	/**
	 * key的格式要为  OPERATOR_FIELDNAME的格式
	 * @param searchParams
	 * @return
	 */
	public static Map<String,SearchFilter> parse(Map<String,Object> searchParams){
		Map<String,SearchFilter> filters = Maps.newHashMap();
		for(Entry<String,Object> entry : searchParams.entrySet()){
			//过滤空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if(StringUtils.isBlank((String)value)){
				continue;
			}
			//拆分operator  field
			String[] names = StringUtils.split(key, "_");
			if(names.length!=2){
				throw new IllegalArgumentException("参数："+key+"格式不正确！");
			}
			String fieldName = names[1];
			Operator operator = Operator.valueOf(names[0]);
			
			//创建searchFilter
			SearchFilter filter = new SearchFilter(fieldName,operator,value);
			filters.put(key, filter);
		}
		return filters;
	}
	
	
}

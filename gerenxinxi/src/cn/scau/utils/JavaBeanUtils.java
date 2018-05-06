package cn.scau.utils;

import java.sql.Date;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

//增强commonutils 的toBean方法
public class JavaBeanUtils {
	public static Object toBean(Map<String, String[]> map, Class<?> clazz) {
		Object bean;
		try {
			bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

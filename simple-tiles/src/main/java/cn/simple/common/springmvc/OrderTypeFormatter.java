package cn.simple.common.springmvc;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import cn.simple.common.utils.EasyuiPage.OrderType;


/**
 * 用于Spring MVC在进行数据绑定时正确转化OrderType类型数据
 * @author 
 *
 */
public class OrderTypeFormatter implements Formatter<OrderType> {

	public String print(OrderType orderType, Locale arg1) {
		switch (orderType) {
		case ASC:
			return "asc";
		case DESC:
			return "desc";	
		}

		throw new IllegalArgumentException("无法转化orderType: " + orderType);
	}

	public OrderType parse(String orderType, Locale arg1) throws ParseException {

		if("asc".equals(orderType.trim().toLowerCase())){
			return OrderType.ASC;
		}else if("desc".equals(orderType.trim().toLowerCase())){
			return OrderType.DESC;
		}
		
		throw new IllegalArgumentException("orderType字段格式错误: " + orderType);
	}

}

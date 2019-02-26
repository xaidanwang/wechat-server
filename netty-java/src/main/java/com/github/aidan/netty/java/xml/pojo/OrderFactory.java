package com.github.aidan.netty.java.xml.pojo;

/**
 * @author Administrator
 * @date 2014年3月1日
 * @version 1.0
 */
public class OrderFactory {

	public static Order create(long orderID) {
		Order order = new Order();
		order.setOrderNumber(orderID);
		order.setTotal(9999.999f);
		Address address = new Address();
		address.setCity("杭州");
		address.setCountry("中国");
		address.setPostCode("330000");
		address.setState("浙江省");
		address.setStreet1("水陆寺巷");
		order.setBillTo(address);
		Customer customer = new Customer();
		customer.setCustomerNumber(orderID);
		customer.setFirstName("挖坑");
		customer.setLastName("埋你");
		order.setCustomer(customer);
		order.setShipping(Shipping.INTERNATIONAL_MAIL);
		order.setShipTo(address);
		return order;
	}
}

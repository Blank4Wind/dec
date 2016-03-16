package com.peach.dec.util.constant;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		System.out.println("系统初始化...");
		// 读取web.xml里面的配置
		ServletContext ctx = e.getServletContext();
		String appName = ctx.getInitParameter("app_name");
		// 读取jdbc.properties里面的配置
		// ResourceBundle resourceBundle = ResourceBundle.getBundle("c3p0");
		// String jdbcDriver = resourceBundle.getString("c3p0.driverClass");
		// String jdbcURL = resourceBundle.getString("c3p0.jdbcUrl");
		// String jdbcUser = resourceBundle.getString("c3p0.user");
		// String jdbcPassword = resourceBundle.getString("c3p0.password");

		// 把获取到的参数放到内存
		 SystemVariable.setAppName(appName);
		// SystemVariable.setC3p0Driver(jdbcDriver);
		// SystemVariable.setC3p0URL(jdbcURL);
		// SystemVariable.setC3p0User(jdbcUser);
		// SystemVariable.setC3p0Password(jdbcPassword);

		System.out.println("项目名：" + SystemVariable.getAppName());
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// System.out.println("服务器关闭...");
	}
}

package com.filtertest.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListenerTest implements ServletContextListener{
	//톰캣 실행 시 context객체가 생성되고 종료 시 소멸된다.
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//Tomcat에서 context객체가 소멸될 때 실행(톰캣 종료시)
		System.out.println("서버 종료");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//Tomcat에서 context객체가 생성될 때 실행(톰캣 실행시)
		System.out.println("서버 실행");
	}
	
}

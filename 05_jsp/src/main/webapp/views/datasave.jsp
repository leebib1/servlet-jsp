<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,com.data.model.vo.Animal" %>
<%
	/* jsp에 import 안 된 클래스는 에러난다. */
	List<Animal> animals=List.of(
			new Animal("뽀송이",2,"여",4.7),
			new Animal("뽀삐",2,"남",7.2),
			new Animal("유미",5,"여",10),
			new Animal("톰캣",2,"남",8.5)
			);
	request.setAttribute("animals",animals);
	session.setAttribute("loginId","admin");
	application.setAttribute("contextData","data");
	
	//request.getRequestDispatcher("/views/datacheck.jsp").forward(request,response);
	response.sendRedirect(request.getContextPath()+"/views/datacheck.jsp");
	
%>
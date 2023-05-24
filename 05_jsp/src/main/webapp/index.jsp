<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="views/error/500error.jsp"%>
    <!-- 해당 지시자 태그가 존재해야 jsp파일을 사용할 수 있다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 첫 jsp페이지</title>
</head>
<body>
	<h2>나의 첫 JSP 페이지</h2>
	<h3>JSP가 제공하는 태그</h3>
    <ul>
        <li>
            지시자 : <%-- <%@ 태그명 속성 설정(속성명="속성값") %> --%><br>
            page : 페이지에 대한 설정을 하는 태그. 응답contexttype, import정보, 언어 정보 등 설정<br>
            include : 페이지(JSP) 내에 다른 페이지(JSP)를 불러올 때 사용하는 태그<br>
            taglib : jsp에 적용할 외부라이브러리 등록(JSTL, springform)<br>
        </li>
        <li>
        	선언문 : <%--<%! 자바코드 %> --%><br>
        	자바 클래스 구현부에 작성하는 코드 클래스 중괄호 부분에 들어갈 코드를 작성할 때 사용한다.<br>
        	메소드 선언, 멤버변수 선언 이용할 때 사용한다. 자주 사용하지 않음<br>
        	*조건문, 반복문 등은 사용이 불가능하다.
        </li>
        <li>
        	스크립트릿 : <%-- <% 자바코드 %> --%><br>
        	java의 메소드 내부에 들어갈 코드를 작성한다.<br>
        	대부분 해당 태그를 사용한다. 지역변수, 반복문, 조건문 등에 사용된다.<br>
        </li>
        <li>
        	표현식 : <%-- <%= 출력할 문구||변수명||메소드 호출(반환값 있어야함) %> --%><br>
        	html 화면에 변수, 메소드 실행 결과를 출력할 때 사용한다.<br>
        	표현식 내부에서는 세미콜론(;)을 사용하지 않는다.
        </li>
    </ul>

    <h3>선언문 활용하기</h3>
    <%!
    	//멤버 변수, 멤버 메소드를 선언할 때 사용
    	String testData;
    	public int age=19;
    	public String getMsg(){
    		return "안녕하세요";
    	}
    	
    	//조건문, 반복문 등이 사용 불가능하다
    	//if(test.equals("test")){}
    	//for(int i=0;i<10;i++){}
    %>
    <h3>선언문에서 작성한 내용 이용하기</h3>
    <ol>
    	<!-- 세미콜론 사용 안 함 -->
    	<li>testData : <%= testData %></li>
    	<li>age : <%= age %></li>
    	<li>getMsg() : <%= getMsg() %></li>
    </ol>

    <h3>스크립트릿 이용하기</h3>
    <% 
    	//자바 코드 작성 부분
    	//_jspservice() 내부에 작성된다.
    	String msg="이제 곧 점심!";
    	//public double height=180.3;
    	int rndNum=(int)(Math.random()*10+1);
    	if(rndNum>3){
    		/* 브라우저 화면에 출력하는 메소드 */
    		out.print("3보다 크다!");
    	}
    	for(int i=0;i<10;i++){
    		/* println해도 개행처리 안 함 */
    		out.println("출력 : "+i+"<br>");
    	}
    %>
    
    <h3><%= msg %></h3>
    <h3>랜덤 수 : <%= rndNum %></h3>
    <%
    	String[] names={"유병승","최주영","이은지","김현영","허성현","김찬은"};
    %>
    <ul>
    	<% for(String name:names){ %>
    	<li><%= name %></li>
    	<% } %>
    </ul>
    <% if(msg.contains("점심")){ %>
    	<h1>점심 맛있게 드세요!</h1>
    <% } %>
    
    <%
    	String[] hobby={"코딩","독서","게임","등산","낮잠"};
     	for(String h:hobby){
    %>
    	<label><input type="checkbox" name="hobby" value="<%= h %>" <%= h.equals("코딩")?"checked":"" %>><%= h %></label>
    <% } %>
    
    <h3>jsp 내장 객체</h3>
    <p>
    	서블릿에서 데이터를 저장하거나 정보를 가져왔던 객체를 지역변수로 가지고 있다.<br>
    	HttpServletRequest : request<br>
    	HttpServletResponse : response<br>
    	HttpSession : session<br>
    	ServletContext : application<br>
    	Cookie : request.getCookies()<br>
    	Header : request.getHeader()<br>
    	PrintWriter(JSPWriter) : out<br>
    </p>
    <h3>request : <%= request %></h3>
    <h3>response : <%= response %></h3>
    <h3>session : <%= session %></h3>
    <h3>ServletContext(application) : <%= application %></h3>
    <h3>JSPout : <%= out %></h3>
    
    <h3>contextRoot : <%= request.getContextPath() %></h3>
    <h3>요청 주소 : <%=request.getRequestURI() %></h3>
    
    <h3>내장 객체에 저장된 데이터 활용</h3>
    <h4><a href="<%= request.getContextPath() %>/views/datasave.jsp">데이터 저장</a></h4>
    
    <h3>지시자 태그 이용</h3>
    <h4>include 태그 이용</h4>
    <p>
    	include 태그는 다른 jsp 내용을 합쳐서 출력해준다.<br>
    	공통 페이지(header, footer, aside 등)를 반영할 때 사용한다.<br>
    </p>
    <h3><a href="<%=request.getContextPath()%>/views/main.jsp">메인화면</a></h3>
    
    <h4>page 태그 속성</h4>
    <p>
        import : 외부 패키지에 있는 클래스를 이용할 때 import하기 위해서 사용하는 속성.<br>
        import="클래스1, 클래스2". ""안에 작성하고 여러 클래스를 구분할 때 ,로 구분한다.<br>
        errorPage : 페이지에서 에러가 발생했을 때(500에러:서버측에서 발생하는 에러) 연결될 페이지를 지정하기 위해서 사용하는 속성.<br>
        isErrorPage : 에러를 출력하는 페이지에 설정한다. 설정하면 exception 객체를 이용할 수 있다.<br>
        session : session의 자동 생성 여부를 지정하는 속성<br>
    </p>

    <h3>에러 페이지 설정하기</h3>
    <h3><a href="<%=request.getContextPath()%>/views/errortest.jsp">JSP 에러페이지</a></h3>
    <h3><a href="<%=request.getContextPath()%>/errorServlet.do">서블릿 에러페이지</a></h3>
</body>
</html>
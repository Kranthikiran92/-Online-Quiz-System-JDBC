
<%
 try{
 session.removeAttribute("username");
 session.invalidate();
 request.getRequestDispatcher("login.jsp").include(request,response);
 }catch(Exception e){}
%>
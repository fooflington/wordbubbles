<html>
<head><title>Wordbubbles solver</title></head>
<%@ page import="uk.org.mafoo.wordbubbles.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.*" %>
<body>
<h1>Wordbubbles or Boggle solver</h1>
<h2>Input grid</h2>
<form action="solver.jsp">
<%
	File directory = new File(application.getRealPath("WEB-INF/dicts"));
	File[] fList = directory.listFiles();
	boolean first = true;
	List<String> filelist = new ArrayList<String>();
	for (File file : fList) {
		if(file.getName().matches("^[a-z\\-_]+$")) {
			filelist.add(file.getName());
		}
	}
	Collections.sort(filelist);
	for(String file : filelist) {
%>
  <input type="radio" name="dict" value="<%= file %>" <% if(first) { %>checked<%}%>><%= file %></input>
<%
		first = false;
	}
%>
  <br />
  <textarea name="grid" rows="10" cols="10" style="font-family: monospace;">
som e
te xt
g oes
 here
  </textarea>
  <br />
  <input type="number" name="desired" min="3" value="5" />
  <input type="submit" value="Submit" />
</form>


</body>
</html>

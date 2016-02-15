<html>
<head><title>Wordbubbles solver</title></head>
<%@ page import="uk.org.mafoo.wordbubbles.*" %>
<body>
<h1>Wordbubbles or Boggle solver</h1>
<h2>Input grid</h2>
<form action="solver.jsp" method="post">
  <input type="radio" name="dictionary" checked>twl06</input><br />

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

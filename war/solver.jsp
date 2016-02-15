<html>
<head><title>Wordbubbles solver</title></head>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="uk.org.mafoo.wordbubbles.*" %>
<%@ page errorPage="error.jsp" %>
<%

// String dict = request.getParameter("dictionary");
// if(dict != "twl06") throw new Exception("failed to get dictionary");
String dict_file = "words";
if(request.getParameter("dict") != null) {
        if(request.getParameter("dict").matches("^[a-z\\-_]+$")) {
                 dict_file = request.getParameter("dict");
        } else {
                throw new Exception ("invalict dict paramter");
        }
}

String dict = application.getRealPath("WEB-INF/dicts") + "/" + dict_file;

uk.org.mafoo.wordbubbles.Lexicon words = uk.org.mafoo.wordbubbles.Lexicon.loadLexiconFromFile(dict);
ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
HashSet<Integer> desired = new HashSet<Integer>();
boolean desired_active = false;

if(request.getParameterValues("desired") != null) {
	desired_active = true;
	for( String p : request.getParameterValues("desired") )  {
		desired.add(new Integer(p));
	}
}

if (request.getParameter("grid").length() > 2048) { throw new Exception("Input too large"); }

for ( String line : request.getParameter("grid").split("\r\n")) {
  ArrayList<Character> row = new ArrayList<Character>();
  for ( char c : line.toLowerCase().toCharArray() ) {
    if( (c >= 'a' && c <= 'z') || c == ' ' || c == '.' || c == '_') {
	    if(c == '.' || c == '_') c = ' ';
	    row.add(new Character(c));
	} else {
		throw new Exception("invalid char");
	}
  }
  grid.add(row);
}

uk.org.mafoo.wordbubbles.Prison prison = new uk.org.mafoo.wordbubbles.Prison(grid);
ArrayList<LinkedHashSet<Cell>> found = prison.search(words);
HashSet<String> foundwords = new HashSet<String>();
for ( LinkedHashSet<Cell> w : found ) {
	String str = "";
	for ( Cell letter : w ) {
		str += letter;
	} 
	if(desired_active) {
		if(desired.contains(new Integer(str.length()))) { foundwords.add(str); }
	} else {
		foundwords.add(str);
	}
}
%>
<body>
<h1>Wordbubble Solver</h1>
<h2>Input</h2>
<table style="font-family: monospace;">
<% for (ArrayList<Character> row : grid) { %>
  <tr>
<%    for(Character c : row) { %>
    <td><%= c %></td>
<%      }
   }
%>
</table>

<% if(desired_active) { %> Looking for words of length(s): <%= desired %> <% } %><br />
Using dictionary: <%= dict_file %>

<%
   List<String> output = new ArrayList<String>(foundwords);
   Collections.sort(output);
%>

<h2>Results</h2>
Count: <%= output.size() %>
<ul style="font-family: monospace;">
<% 
   for (String word : output) { %>
<li><%= word %></li>
<% } %>
</ul>
</body>
</html>

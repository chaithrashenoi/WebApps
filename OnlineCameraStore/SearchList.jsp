<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>SNAPiT</title>
	<link href="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet">
	<link href="/jadrn026/servlets/proj3/post_ajax3.css" rel="stylesheet">
	<script src="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  <script src="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
	<script>
	$(function() {
	 $( "#menu1" ).menu();
	 });
	$(function() {
	 $( "#menu2" ).menu();
	 });
	</script>
	<style>
	body{
		font: 62.5% "Trebuchet MS", sans-serif;
		margin: 50px;
	}
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay {
		position: absolute;
	}
	</style>
</head>
<body>
</head>
<body>
<div id="header">
<h2 align="center">SNAP<i>i</i>T</h1>
<h3>SAME DAY SHIPPING<br> on most orders till 8pm</h2>
<table id="table2"  >
<tr>
<td>
<form method="post" 
      action="../../servlet/proj3/SearchKey"
			name="search">
<input id="key" type="text" name="key" size="40" placeholder="search by #sku, name, brand or category " >
<input name="send"  type="submit"  value="SEARCH">
</form>
</td>
</tr>
</table>
</div>

<nav id="tabs_nav">
  <ul>
		<li><a href="/jadrn026/proj3.html">Home</a></li>
		<li><a href="/jadrn026/servlets/proj3/Contact.html">Contact Us</a></li>
		<li><a href="/jadrn026/servlets/proj3/About.html">About Us</a></li>
		<li><a href="/jadrn026/servlet/proj3/ShowCart">Show Cart</a></li>
  </ul>
</nav>


<div id="mainContainer">

<div id="table_div1" style="float: right; min-width: 900px;" >
	
<h1 style="text-align: center; size: 40px; left: 500px"><%= request.getParameter("count") %> Items found</h1>
	
<table id="table_pr" class="no_border">
<%
	int product_count = Integer.parseInt(request.getParameter("count"));
  for(int i=0;i<product_count;i++) {
	boolean row_open = (i%3)==0;
	boolean row_close = (i%3)==2;
	%>
<% if (row_open) { %>
<tr class="tr_border">
<% } %>
<td class="td_border" >
<div id="div_tile">
<a  href="/jadrn026/servlet/proj3/ShowProduct?sku=<%= request.getAttribute("sku"+i) %>">
	  <img id="img_tile" src="http://jadran.sdsu.edu/~jadrn026/proj1/_uploadDIR_/<%= request.getAttribute("image_name"+i) %>">
	  <br><%= request.getAttribute("vendor"+i) %> <%= request.getAttribute("man_id"+i) %></a><br>
		<%= request.getAttribute("sku"+i) %><br>
		$<%= request.getAttribute("price"+i) %><br>
		<%= request.getAttribute("stock"+i) %>
</div> 
</td >
<% if (row_close) { %>
</tr >
<% } %>
<% } %>

</table>
</div>

<div id="menu_pane">
	<div id="menuLeft1">	
		<h1>By Category</h1>
			<ul id="menu1">
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Digital SLR">Digital SLR</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Lenses">Lenses</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Point and Shoot">Point and Shoot</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Underwater">Underwater</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Digital camera">Digital camera</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Film SLR">Film SLR</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Mirror-less">Mirror-less</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Memory Cards">Memory Cards</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowCategory?category=Tripods">Tripods</a></li>
		</ul>
	</div>

	<div id="menuLeft2">	
		<h1>By Brand</h1>
		<ul id="menu2">
			<li><a href="/jadrn026/servlet/proj3/ShowBrand?vendor=Nikon">Nikon</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowBrand?vendor=Canon">Canon</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowBrand?vendor=Sony">Sony</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowBrand?vendor=panasonic">panasonic</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowBrand?vendor=olympus">olympus</a></li>
			<li><a href="/jadrn026/servlet/proj3/ShowBrand?vendor=samsung">samsung</a></li>
		</ul>
	</div>

<div id="footerPr" style="clear: both;">
<p align="center" >481 EL CAMINO REAL,Santa Clara, CA, 95050. 2013 SNAPiT, Inc. All rights reserved.<br>
Encountered problems with this site? Contact:(000)000 000.
</p>
</div>
</div>
</body>
</html>

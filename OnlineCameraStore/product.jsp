<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>SNAPiT</title>
	<link href="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet">
	<link href="/jadrn026/servlets/proj3/post_ajax3.css" rel="stylesheet">
	<script src="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  <script src="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
  <script src="/jadrn026/servlets/proj3/Valid.js"></script>
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

<div align="left" style="background-color:#8EBAC9; float: right; box-shadow: 4px 4px 2px; border-radius:10px; font-size:14px; color: #0C2F3B; width: 78%; min-height: 600px; padding:50px;">
  <div  style="left: 800px; margin-right: 150px;  padding-top: 100px; position: absolute;">
		<b><%= request.getAttribute("vendor") %> 
		<%= request.getAttribute("man_id") %></b> <br><br>
		<b>SKU:</b><%= request.getAttribute("sku") %><br><br>
		<input type="hidden" name="sku_product" value=<%= request.getAttribute("sku") %> />
		<b>Price:</b><font color="red"> $<%= request.getAttribute("price") %></font><br><br>
		<%= request.getAttribute("stock") %><br><br>
		<label for="quantity">Quantity:</label>
		<input type="number" name="quantity_product"  size="3" /><br/><br/>
		<input type="submit" name="add_cart" value="Add To Cart" class="highlight1"
					 style="font-size: 15px; border-radius: 10px;"/><br/><br/>
		<div style="color: green;" id="answer1_product"></div>
		<div id="error1_product"></div>
		<div id="message_line_product"></div>
	</div>
 <div style="float: left; ">
		<img src="http://jadran.sdsu.edu/~jadrn026/proj1/_uploadDIR_/<%= request.getAttribute("image_name") %>" width="480" height="auto"
		style=" box-shadow: 4px 4px 2px #333329;border-radius:10px;"> <br><br><br>
	</div>
 <div style="clear: both; ">
   	<b>Description:</b> <%= request.getAttribute("description") %><br><br>
		<b>Features:</b> <%= request.getAttribute("features") %><br><br>
 </div>
	
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

</div>

<div id="footerPr" style="clear: both;">
<p align="center" >481 EL CAMINO REAL,Santa Clara, CA, 95050. 2013 SNAPiT, Inc. All rights reserved.<br>
Encountered problems with this site? Contact:(000)000 000.
</p>
</div>
</div>
</body>
</html>

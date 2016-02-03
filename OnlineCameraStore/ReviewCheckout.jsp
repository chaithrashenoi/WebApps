<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>SNAPiT</title>
	<link href="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet">
	<link href="/jadrn026/servlets/proj3/post_ajax3.css" rel="stylesheet">
	<script src="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
  <script src="/jadrn026/servlets/proj3/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js"></script>
	  <script src="/jadrn026/servlets/proj3/Cart.js"></script>
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


<div  style="float: right; padding-top: 50px; min-width: 700px; margin-right: 150px; text-align:center;font-size: 14px; " >
<table border="1" cellspacing="0"  cellpadding="0" >
<th id="thead"><b>Product</b></th>	
<th id="thead"><b>Quantity</b></th>	
<th id="thead"><b>UnitPrice</b></th>	
<th id="thead"><b>TotalPrice</b></th>	
<%
	int product_count = Integer.parseInt(request.getParameter("count"));
  for(int i=0;i<product_count;i++) {
	String Sku = (String)request.getAttribute("sku"+i);
	%>
<tr width="700px" height="120px">
		<td id="tdata"><a  href="/jadrn026/servlet/proj3/ShowProduct?sku=<%=Sku%>">
	  <img align=left id="img_cart" src="http://jadran.sdsu.edu/~jadrn026/proj1/_uploadDIR_/<%= request.getAttribute("image_name"+i) %>">
	  <%= request.getAttribute("vendor"+i) %> <%= request.getAttribute("man_id"+i) %></a></td>
		<td id="tdata"><%= request.getAttribute("quantity"+i) %></br></br>
		<br/><br/><div class="answer1_cart" id="answer1_cart<%=Sku%>"></div>
		<div class="error1_cart"  id="error1_cart<%=Sku%>"></div>
		<div class="message_line_cart"  id="message_line_cart<%=Sku%>"></div></td>
		<td id="tdata">$<%= request.getAttribute("price"+i) %></td>
		<td id="tdata">$<%= request.getAttribute("totalprice"+i) %></td>
</tr >

<% } %>

</table>
	<div style="text-align:right; min-width: 400px; font-size: 14px; color: red;">		
			<label for="shipping"> <font color=#424242>Shipping charge on each:</font> $<%= request.getAttribute("shipping") %></label>
			<br><label for="Tax"><font color=#424242>8% tax:</font> $<%= request.getAttribute("tax") %></label>
			<br><label for="Final"><font color=#424242>Grand Total:</font> $<%= request.getAttribute("grandtotal") %></label>
			<input type="hidden" name="CartSummary" value=<%= request.getAttribute("CartSummary") %> />
			<input type="hidden" name="CartCount" value=<%= request.getParameter("count") %> />
	</div>
	<div align="left">
				<label for="shipping_adress"> <font size=4px color=#424242 ><b>Shipping Address:</b></font><br> <%= request.getAttribute("Address") %></label>

	</div>
	<div>
		<form method="post"
					name="shopcheck">
		<input   type="button" name="order_cancel" value="Cancel"  style="background-color:#424242; color: #D0ECF9;
		font-size: 15px; border-radius: 10px; "/>
		
		<input type="button" name="order_submit" value="Order Now"  style="background-color:#424242; color: #D0ECF9; font-size: 15px; border-radius: 10px;"/>
		</form>

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

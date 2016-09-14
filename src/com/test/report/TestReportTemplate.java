package com.test.report;
/**
 * File name   :TestReportTemplate.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class TestReportTemplate {
	/**
	 * 
	 * Method name  : getStepsDetailTemplate
	 * Return types : String
	 * Description  :
	 */
	public static String getStepsDetailTemplate(){
		String stepsDetailTemplate="<html lang=\"en\">\r\n" + 
				"  <head>\r\n" + 
				"    <!-- Required meta tags always come first -->\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
				"    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
				"    <!-- Bootstrap CSS -->\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css\" integrity=\"sha384-MIwDKRSSImVFAZCVLtU0LMDdON6KVCrZHyVQQj6e8wIEJkW4tvwqXrbMIya1vriY\" crossorigin=\"anonymous\">\r\n" + 
				"	</head>\r\n" + 
				"  <body>\r\n" + 
				"REPLACE THIS WITH MODEL CONTENT\r\n" + 
				"<table class=\"table table-striped\">\r\n" + 
				"  <thead>\r\n" + 
				"    <tr>\r\n" + 
				"      <th>Step No</th>\r\n" + 
				"      <th>Step Description</th>\r\n" + 
				"      <th>Status</th>\r\n" + 
				"    </tr>\r\n" + 
				"  </thead>\r\n" + 
				"  <tbody>\r\n" +
				"	 REPLACE THIS WITH ROWS CONTENT\r\n" + 				
				"  </tbody>\r\n" + 
				"</table>\r\n" + 
				"    <!-- jQuery first, then Tether, then Bootstrap JS. -->\r\n" + 
				"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js\" integrity=\"sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY\" crossorigin=\"anonymous\"></script>\r\n" + 
				"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js\" integrity=\"sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB\" crossorigin=\"anonymous\"></script>\r\n" + 
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js\" integrity=\"sha384-ux8v3A6CPtOTqOzMKiuo3d/DomGaaClxFYdCu2HPMBEkf6x2xiDyJ7gkXU0MWwaD\" crossorigin=\"anonymous\"></script>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		return stepsDetailTemplate;
	}
	/**
	 * Method name  : getResponseHTMLTemplate
	 * Return types : String
	 * Description  :
	 */
	public static String getResponseHTMLTemplate(){
		String responseHTMLTemplate="<html>\r\n" + 
				"  <head>\r\n" + 
				"    <!--Import Bootstrap CSS-->\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://s3-eu-west-1.amazonaws.com/effractarius-resources/bootstrap.min.css\" />\r\n" + 
				"    <style type='text/css'>  \r\n" + 
				"    .header {\r\n" + 
				"        margin: 30px;\r\n" + 
				"        padding: 30px;\r\n" + 
				"        width: 250px;\r\n" + 
				"        color: #fff;\r\n" + 
				"        background-color: aquamarine;\r\n" + 
				"        font-weight: bold;\r\n" + 
				"    }\r\n" + 
				"    @-moz-document url-prefix() {\r\n" + 
				"    fieldset { display: table-cell; }\r\n" + 
				"    }  \r\n" + 
				"   .table-custom {\r\n" + 
				"     margin-top: 40px;\r\n" + 
				"    }\r\n" + 
				"    </style>\r\n" + 
				"  </head>\r\n" + 
				"  <body class=\"container-fluid\">	\r\n" + 
				"    <div class=\"table-responsive\">   \r\n" + 
				"     <table id=\"veera-template\" class=\"table table-bordered table-custom\">\r\n" + 
				"	  <th colspan=\"4\" background-color=\"aquamarine\"><center>REPLACE THIS WITH DESCRIPTION</center></th>\r\n" + 
				"      <tr class=\"header\">\r\n" + 
				"        <td>Actual Response</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td><pre><code>REPLACE THIS WITH RESPONSE\r\n" + 
				"</pre></code></td></tr></table>\r\n" + 
				"</div>\r\n" + 
				"<script>\r\n" + 
				"   //format json when the page loads\r\n" + 
				"   //parent is an id - I've given the table the id veera-template\r\n" + 
				"   //tagname is the element that you want to find, eg: h1, code, div\r\n" + 
				"   function formatJSON(parent, tagname){\r\n" + 
				"\r\n" + 
				"   parent = document.getElementById(parent);\r\n" + 
				"   var descendants = parent.getElementsByTagName(tagname);\r\n" + 
				"   if (descendants.length){\r\n" + 
				"    var i = 0;\r\n" + 
				"      for (var descendant in descendants){\r\n" + 
				"        while (i <= descendants.length){\r\n" + 
				"         jsonObj= JSON.parse(descendants[i].innerHTML);\r\n" + 
				"         descendants[i].innerHTML = JSON.stringify(jsonObj, null,2);\r\n" + 
				"         i++;\r\n" + 
				"        }         \r\n" + 
				"      }\r\n" + 
				"   }\r\n" + 
				"   else{\r\n" + 
				"   return null;\r\n" + 
				"   }\r\n" + 
				"}\r\n" + 
				"formatJSON(\"veera-template\", \"code\");\r\n" + 
				"console.log(header);\r\n" + 
				"</script>\r\n" + 
				"     <!--Import JQuery (required by bootstrap) and bootstrap js-->\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://s3-eu-west-1.amazonaws.com/effractarius-resources/jquery.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://s3-eu-west-1.amazonaws.com/effractarius-resources/bootstrap.min.js\"></script>\r\n" + 
				"    \r\n" + 
				"  </body>\r\n" + 
				"</html>\r\n" + 
				"\r\n";
		return responseHTMLTemplate;
	}
}

package com.test.report;
/**
 * File name    :ReportTemplate.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class ReportTemplate {
	/**
	 * Method name  : getReportTemplate
	 * Return types : String
	 * Description  :
	 */
	public static String getReportTemplate(){
		String htmlSource = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">\r\n" + 
				"	<title>Test Reports</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"./report-resources/report.css\">\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"./report-resources/tablesorter.css\">\r\n" + 
				"	<script src=\"./report-resources/jquery-1.11.2.min.js\"></script>\r\n" + 
				"	<script src=\"./report-resources/handlebars-v2.0.0.js\"></script>\r\n" + 
				"	<script src=\"./report-resources/tablesorter.js\"></script>\r\n" + 
				"	<script src=\"./report-resources/report.js\"></script>\r\n" + 
				"	<script>\r\n" + 
				"	var reportData = REPLACE THIS WITH TEST RESULTS" + 
				"	</script>\r\n" + 
				"	<script>\r\n" + 
				"	$(function () {\r\n" + 
				"		var Report = createGalenTestOverview();\r\n" + 
				"		Report.renderTestsTable(\"tests-table\", reportData);\r\n" + 
				"		window.onhashchange = function () {\r\n" + 
				"			Report.handleHash(window.location.hash.substr(1));\r\n" + 
				"		};\r\n" + 
				"		Report.handleHash(window.location.hash.substr(1));\r\n" + 
				"	});\r\n" + 
				"	</script>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"	<div class=\"tests-overview\">\r\n" + 
				"	<h2>Test Report</h2>\r\n" + 
				"	<div class=\"tabs\">\r\n" + 
				"	<a class=\"tab tab-tests tab-selected\" href=\"http://galenframework.com/public/samples/reports/report.html#tests\">Tests</a>\r\n" + 
				"	</div>\r\n" + 
				"	<div id=\"tests-table\" style=\"display: block;\">\r\n" + 
				"	</div>\r\n" + 
				"	<script id=\"tests-table-tpl\" type=\"text/x-handlebars-template\">\r\n" + 
				"	<table class=\"tests tablesorter\">\r\n" + 
				"	<thead>\r\n" + 
				"	<tr>\r\n" + 
				"	<th>Test Description</th>\r\n" + 
				"	<th>Test ID</th>\r\n" + 				
				"	<th>Passed</th>\r\n" + 
				"	<th>Failed</th>\r\n" + 
				"	<th>Info</th>\r\n" + 
				"	<th>Total</th>\r\n" + 
				"	<th>Started</th>\r\n" + 
				"	<th>Duration</th>\r\n" + 
				"	<th>Progress Bar</th>\r\n" + 
				"	</tr>\r\n" + 
				"	</thead>\r\n" + 
				"	<tbody>\r\n" + 
				"	{{#each tests}}\r\n" + 
				"	<tr data-groups=\"{{groups}}\">\r\n" + 
				"	<td class=\"suite-link\">\r\n" + 
				"	<a href=\"./report-resources/report-details/{{testId}}.html\">{{name}}</a>\r\n" + 
				"	</td>\r\n" +
				"	<td class=\"test id\">{{testId}}</td>\r\n" +
				"	<td class=\"status passed\">{{statistic.passed}}</td>\r\n" + 
				"	<td class=\"status failed\">{{statistic.errors}}</td>\r\n" + 
				"	<td class=\"status warnings\">{{statistic.warnings}}</td>\r\n" + 
				"	<td class=\"status total\">{{statistic.total}}</td>\r\n" + 
				"	<td class=\"time\">{{formatDateTime startedAt}}</td>\r\n" + 
				"	<td class=\"time\">{{formatDurationHumanReadable duration}}</td>\r\n" + 
				"	<td class=\"progressbar\">\r\n" + 
				"	{{renderProgressBar statistic}}\r\n" + 
				"	</td>\r\n" + 
				"	</tr>\r\n" + 
				"	{{/each}}\r\n" + 
				"	</tbody>\r\n" + 
				"	</table>\r\n" + 
				"	</script>\r\n" + 
				"	<script id=\"groups-table-tpl\" type=\"text/x-handlebars-template\">\r\n" + 
				"	</script>\r\n" + 
				"	</div>\r\n" + 
				"	</body></html>";
		return htmlSource;
	}
}

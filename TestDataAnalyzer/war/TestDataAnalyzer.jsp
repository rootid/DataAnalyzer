<!doctype html>
<!-- The DOCTYPE declaration above will set the    -->
<!-- browser's rendering engine into               -->
<!-- "Standards Mode". Replacing this declaration  -->
<!-- with a "Quirks Mode" doctype may lead to some -->
<!-- differences in layout.                        -->

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!--                                                               -->
<!-- Consider inlining CSS to reduce the number of requested files -->
<!--                                                               -->
<link type="text/css" rel="stylesheet" href="TestDataAnalyzer.css">

<!--                                           -->
<!-- Any title is fine                         -->
<!--                                           -->
<title>Data Analyzer Project</title>

<!--                                           -->
<!-- This script loads your compiled module.   -->
<!-- If you add any GWT meta tags, they must   -->
<!-- be added before this line.                -->
<!--                                           -->
<script type="text/javascript" language="javascript"
	src="testdataanalyzer/testdataanalyzer.nocache.js"></script>
</head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic UI.        -->
<!--                                           -->
<body>

	<!-- OPTIONAL: include this if you want history support -->
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
		<div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.</div>
	</noscript>
	<table>
		<!-- Header declaration -->
		<thead>
			<tr>
				<td width="10%"></td>
				<td class="background"><jsp:include page="header.jsp"></jsp:include>
				</td>
				<td width="10%"></td>
			</tr>
		</thead>

		<!-- Content decalaration -->
		<tbody>
			<tr>
				<td id="stackPanelContainer" width="20%" height="280%"></td>
				<td id="contentPanelContainer" width="80%" height="280%"></td>
				<!--<td id="resultPanelContainer" width="80%" height="280%"></td>-->
			</tr>
		</tbody>

		<!-- Footer decalration -->
		<tfoot>
			<tr>
			</tr>
		</tfoot>



	</table>
</body>
</html>

function createChart(chart_json) {

	var chart = AmCharts.makeChart("chartdiv", {
		"type" : "serial",
		"theme" : "light",
		"dataProvider" : chart_json,
		"valueAxes" : [ {
			"gridColor" : "#FFFFFF",
			"gridAlpha" : 0.2,
			"dashLength" : 0
		} ],
		"gridAboveGraphs" : true,
		"startDuration" : 1,
		"graphs" : [ {
			"balloonText" : "[[category]]: <b>[[value]]</b>",
			"fillAlphas" : 0.8,
			"lineAlpha" : 0.2,
			"type" : "column",
			"valueField" : "value"
		} ],
		"chartCursor" : {
			"categoryBalloonEnabled" : false,
			"cursorAlpha" : 0,
			"zoomable" : false
		},
		"categoryField" : "label",
		"categoryAxis" : {
			"gridPosition" : "start",
			"gridAlpha" : 0,
			"tickPosition" : "start",
			"tickLength" : 20
		},
		"export" : {
			"enabled" : true
		}
	});
}
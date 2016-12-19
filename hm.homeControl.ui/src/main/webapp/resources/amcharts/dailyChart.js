function generateChartData() {
	var chartData = [];
	var firstDate = new Date(2012, 0, 1);
	firstDate.setDate(firstDate.getDate() - 1000);
	firstDate.setHours(0, 0, 0, 0);

	for (var i = 0; i < 1000; i++) {
		var newDate = new Date(firstDate);
		newDate.setHours(0, i, 0, 0);

		var a = Math.round(Math.random() * (40 + i)) + 100 + i;
		var b = Math.round(Math.random() * 100000000);
		console.log(newDate);

		chartData.push({
			"date" : newDate,
			"value" : a,
			"volume" : b
		});
	}
	return chartData;
}

function createChart(chartData) {

	var chart = AmCharts.makeChart("chartdiv", {
		"type" : "stock",
		"theme" : "light",
		"categoryAxesSettings" : {
			"minPeriod" : "mm"
		},

		"dataSets" : [ {
			"color" : "#b0de09",
			"fieldMappings" : [ {
				"fromField" : "preassure",
				"toField" : "preassure"
			}, {
				"fromField" : "temperature2",
				"toField" : "temperature2"
			}, {
				"fromField" : "luminous",
				"toField" : "luminous"
			}, {
				"fromField" : "humidity1",
				"toField" : "humidity1"
			}, {
				"fromField" : "humidity2",
				"toField" : "humidity2"
			} ],

			"dataProvider" : chartData,
			"categoryField" : "date"
		} ],

		"panels" : [ {
			"showCategoryAxis" : false,
			"title" : "tlak vzduchu",
			"percentHeight" : 100,

			"stockGraphs" : [ {
				"id" : "g1",
				"valueField" : "preassure",
				"type" : "smoothedLine",
				"lineThickness" : 2,
				"bullet" : "round"
			} ],

			"stockLegend" : {
				"valueTextRegular" : " ",
				"markerType" : "none"
			}
		}, {
			"showCategoryAxis" : false,
			"title" : "teplota okolia",
			"percentHeight" : 100,

			"stockGraphs" : [ {
				"id" : "g1",
				"valueField" : "temperature2",
				"type" : "smoothedLine",
				"lineThickness" : 2,
				"bullet" : "round"
			} ],

			"stockLegend" : {
				"valueTextRegular" : " ",
				"markerType" : "none"
			}
		},{
			"showCategoryAxis" : false,
			"title" : "vlhosť vzduchu",
			"percentHeight" : 100,

			"stockGraphs" : [ {
				"id" : "g1",
				"valueField" : "humidity2",
				"type" : "smoothedLine",
				"lineThickness" : 2,
				"bullet" : "round"
			} ],

			"stockLegend" : {
				"valueTextRegular" : " ",
				"markerType" : "none"
			}
		},  {
			"title" : "vlhosť pôdy",
			"percentHeight" : 100,
			"stockGraphs" : [ {
				"valueField" : "humidity1",
				"type" : "column",
				"cornerRadiusTop" : 2,
				"fillAlphas" : 1
			} ],

			"stockLegend" : {
				"valueTextRegular" : " ",
				"markerType" : "none"
			}
		}, {
			"title" : "svietivosť",
			"percentHeight" : 100,
			"stockGraphs" : [ {
				"valueField" : "luminous",
				"type" : "column",
				"cornerRadiusTop" : 2,
				"fillAlphas" : 1
			} ],

			"stockLegend" : {
				"valueTextRegular" : " ",
				"markerType" : "none"
			}
		}],

		"chartScrollbarSettings" : {
			"graph" : "g1",
			"usePeriod" : "10mm",
			"position" : "top"
		},

		"chartCursorSettings" : {
			"valueBalloonsEnabled" : true
		},

		"periodSelector" : {
			"position" : "top",
			"dateFormat" : "YYYY-MM-DD JJ:NN",
			"inputFieldWidth" : 150,
			"periods" : [ {
				"period" : "hh",
				"count" : 1,
				"label" : "1 hour"
			}, {
				"period" : "hh",
				"count" : 2,
				"label" : "2 hours"
			}, {
				"period" : "hh",
				"count" : 5,
				"label" : "5 hour"
			}, {
				"period" : "hh",
				"count" : 12,
				"label" : "12 hours"
			}, {
				"period" : "MAX",
				"label" : "MAX",
		        "selected" : true
			} ]
		},

		"panelsSettings" : {
			"usePrefixes" : true
		},

		"export" : {
			"enabled" : true,
			"position" : "bottom-right"
		}
	});
}
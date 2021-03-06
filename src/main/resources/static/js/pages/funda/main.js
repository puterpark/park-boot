(function (){
	var interest = JSON.parse($('#interest').val()),
		principal = JSON.parse($('#principal').val()),
		yield = JSON.parse($('#yield').val()),
		total = JSON.parse($('#total').val());
	
	var data = {
		"widget5"	  : {
			"title"	 : "Funda Status",
			"ranges"	: {
				"TW": "This Week"
			},
			"mainChart" : {
				"TW": [
					{
						"key"   : "Interest",
						"values": interest
					},
					{
						"key"   : "Principal",
						"values": principal
					}
				]
			},
			"supporting": {
				"total"   : {
					"label": "Total",
					"count": {
						"TW": 54
					},
					"chart": {
						"TW": [
							{
								"key"   : "Total",
								"values": total
							}
						]
					}
				},
				"interest"   : {
					"label": "Interest",
					"count": {
						"TW": 54
					},
					"chart": {
						"TW": [
							{
								"key"   : "Interest",
								"values": interest
							}
						]
					}
				},"yield"   : {
					"label": "Yield",
					"count": {
						"TW": 54
					},
					"chart": {
						"TW": [
							{
								"key"   : "Yield",
								"values": yield
							}
						]
					}
				}
			}
		}
	};

	/**
	 * Widget 5
	 */
	var widget5Option = 'TW';
	// Main Chart
	nv.addGraph(function ()
	{
		var chart = nv.models.multiBarChart()
			.options(
				{
					color	   : ['#03a9f4', '#b3e5fc'],
					margin	  : {
						top   : 48,
						right : 16,
						bottom: 16,
						left  : 82
					},
					clipEdge	: true,
					groupSpacing: 0.3,
					reduceXTicks: true,
					stacked	 : true,
					duration	: 250,
					x		   : function (d){
						return d.x;
					},
					y		   : function (d){
						return d.y;
					},
					yTickFormat : function (d){
						return d;
					}
				}
			);
		
		chart.yAxis.tickFormat(d3.format(',d'));

		var chartd3 = d3.select('#widget5-main-chart svg')
		var chartData;

		initChart();

		nv.utils.windowResize(chart.update);

		$(window).bind('update:widget5', function (){
			initChart();
		})

		function initChart(){
			chartData = data.widget5.mainChart[widget5Option];
			chartd3.datum(chartData).call(chart);
		}

		return chart;
	});

	// Supporting Charts
	$.each(['total', 'interest', 'yield'], function (index, id) {
		nv.addGraph(function () {
			var chart = nv.models.lineChart()
				.options({
						color				  : ['#03A9F4'],
						height				 : 50,
						margin				 : {
							top   : 8,
							right : 0,
							bottom: 0,
							left  : 0
						},
						isArea				 : true,
						interpolate			: 'cardinal',
						clipEdge			   : true,
						duration			   : 500,
						showXAxis			  : false,
						showYAxis			  : false,
						showLegend			 : false,
						useInteractiveGuideline: true,
						x					  : function (d){
							return d.x;
						},
						y					  : function (d){
							return d.y;
						}
					}
				);
			
			if (id != 'yield') {
				chart.yAxis.tickFormat(d3.format(',d'));
			}

			var chartd3 = d3.select('#widget5-' + id + '-chart svg');
			var chartData;

			initChart();

			nv.utils.windowResize(chart.update);

			function initChart(){
				chartData = data.widget5.supporting[id].chart[widget5Option];
				chartd3.datum(chartData).call(chart);
			}

			return chart;
		});
	});

	$('.widget5-option-change-btn').on('click', function (ev){
		console.log($(ev.target).data('interval'));
		widget5Option = $(ev.target).data('interval');
		$(window).trigger('update:widget5');
	});
})();
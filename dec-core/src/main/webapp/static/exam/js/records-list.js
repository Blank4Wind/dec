var arr = eval('(' + recordsJson + ')');
$(function() {
	$('#container').highcharts({
		chart: {  
                renderTo: name,  
                backgroundColor: 'rgba(255, 255, 255, 0)',  
                plotBorderColor : null,  
                plotBackgroundColor: null,  
                plotBackgroundImage:null,  
                plotBorderWidth: null,  
                plotShadow: false,
       },
		colors : [
			'#00A65A',
			'#DD4B39',
			'#00C0EF'
		],
		title : {
			text : '考试记录统计'
		},
		tooltip : {
			pointFormat : '<b>{point.percentage:.1f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					format : '<b>{point.name}</b>: {point.percentage:.1f} %'
				}
			}
		},
		series : [{
			type : 'pie',
			name : 'Browser share',
			data : arr
		}]
	});
});

$(function () {
//    $('#histogram1').highcharts({
//        chart: {
//            type: 'column'
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        xAxis: {
//            categories: [
//                '定时任务',
//                '实时任务',
//                '离线任务'
//            ]
//        },
//        yAxis: {
//            min: 0,
//            title: {
//                text: '数量（个）'
//            }
//        },
//        tooltip: {
//            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//                '<td style="padding:0"><b>{point.y}</b></td></tr>',
//            footerFormat: '</table>',
//            shared: true,
//            useHTML: true
//        },
//        plotOptions: {
//            column: {
//                pointPadding: 0.2,
//                borderWidth: 0
//            }
//        },
//        series: [{
//            name: '正常状态',
//        }, {
//            name: '异常状态',
//        }]
//    });
//    
//    $('#pieChart1').highcharts({
//        chart: {
//            plotBackgroundColor: null,
//            plotBorderWidth: null,
//            plotShadow: false
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        tooltip: {
//    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//        },
//        plotOptions: {
//            pie: {
//                allowPointSelect: true,
//                cursor: 'pointer',
//                dataLabels: {
//                    enabled: true,
//                    color: '#000000',
//                    connectorColor: '#000000',
//                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
//                }
//            }
//        },
//        series: [{
//            type: 'pie',
//            name: '个数',
//        }]
//    });
//    
//    $('#histogram2').highcharts({
//        chart: {
//            type: 'column'
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        xAxis: {
//            categories: [
//                '定时任务',
//                '实时任务',
//                '离线任务'
//            ]
//        },
//        yAxis: {
//            min: 0,
//            title: {
//                text: '数量（个）'
//            }
//        },
//        tooltip: {
//            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//                '<td style="padding:0"><b>{point.y}</b></td></tr>',
//            footerFormat: '</table>',
//            shared: true,
//            useHTML: true
//        },
//        plotOptions: {
//            column: {
//                pointPadding: 0.2,
//                borderWidth: 0
//            }
//        },
//        series: [{
//            name: '正常状态',
//        }, {
//            name: '异常状态',
//        }]
//    });
//    
//    $('#pieChart2').highcharts({
//        chart: {
//            plotBackgroundColor: null,
//            plotBorderWidth: null,
//            plotShadow: false
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        tooltip: {
//    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//        },
//        plotOptions: {
//            pie: {
//                allowPointSelect: true,
//                cursor: 'pointer',
//                dataLabels: {
//                    enabled: true,
//                    color: '#000000',
//                    connectorColor: '#000000',
//                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
//                }
//            }
//        },
//        series: [{
//            type: 'pie',
//            name: '个数',
//        }]
//    });
//    
//    $('#histogram3').highcharts({
//        chart: {
//            type: 'column'
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        xAxis: {
//            categories: [
//                '定时任务',
//                '实时任务',
//                '离线任务'
//            ]
//        },
//        yAxis: {
//            min: 0,
//            title: {
//                text: '数量（个）'
//            }
//        },
//        tooltip: {
//            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//                '<td style="padding:0"><b>{point.y}</b></td></tr>',
//            footerFormat: '</table>',
//            shared: true,
//            useHTML: true
//        },
//        plotOptions: {
//            column: {
//                pointPadding: 0.2,
//                borderWidth: 0
//            }
//        },
//        series: [{
//            name: '正常状态',
//        }, {
//            name: '异常状态',
//        }]
//    });
//    
//    $('#pieChart3').highcharts({
//        chart: {
//            plotBackgroundColor: null,
//            plotBorderWidth: null,
//            plotShadow: false
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        tooltip: {
//    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//        },
//        plotOptions: {
//            pie: {
//                allowPointSelect: true,
//                cursor: 'pointer',
//                dataLabels: {
//                    enabled: true,
//                    color: '#000000',
//                    connectorColor: '#000000',
//                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
//                }
//            }
//        },
//        series: [{
//            type: 'pie',
//            name: '个数',
//        }]
//    });
//    
//    $('#histogram4').highcharts({
//        chart: {
//            type: 'column'
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        xAxis: {
//            categories: [
//                '定时任务',
//                '实时任务',
//                '离线任务'
//            ]
//        },
//        yAxis: {
//            min: 0,
//            title: {
//                text: '数量（个）'
//            }
//        },
//        tooltip: {
//            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//                '<td style="padding:0"><b>{point.y}</b></td></tr>',
//            footerFormat: '</table>',
//            shared: true,
//            useHTML: true
//        },
//        plotOptions: {
//            column: {
//                pointPadding: 0.2,
//                borderWidth: 0
//            }
//        },
//        series: [{
//            name: '正常状态',
//
//        }, {
//            name: '异常状态',
//
//        }]
//    });
//    
//    $('#pieChart4').highcharts({
//        chart: {
//            plotBackgroundColor: null,
//            plotBorderWidth: null,
//            plotShadow: false
//        },
//        credits: {
//        	enabled: false
//        },
//        title: {
//            text: ''
//        },
//        tooltip: {
//    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//        },
//        plotOptions: {
//            pie: {
//                allowPointSelect: true,
//                cursor: 'pointer',
//                dataLabels: {
//                    enabled: true,
//                    color: '#000000',
//                    connectorColor: '#000000',
//                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
//                }
//            }
//        },
//        series: [{
//            type: 'pie',
//            name: '个数',
//        }]
//    });
//});	
//
//$(document).ready(function(){  
//	  queryData(1);
//	  queryData(2);
//	  queryData(3);
//	  queryData(4);  
//	
////    for(var i=0;i<4;i++){
////    	eval("var index" + (i+1) + "=" + (i + 1))
////    	console.log(eval("index" + (i+1)));
////	    setTimeout(function(){
////	    	setInterval("queryData(" + eval("index"+i) + ")",4000);
////	    },i * 1000)
////	    
////    }
//   
//	setTimeout(function(){
//    	setInterval("queryData(1)",4000);
//    },1000)
//    setTimeout(function(){
//    	setInterval("queryData(2)",4000);
//    },2000)
//    setTimeout(function(){
//    	setInterval("queryData(3)",4000);
//    },3000)
//    setTimeout(function(){
//    	setInterval("queryData(4)",4000);
//    },4000)
//    
//});  
//
//function queryData(businessType){
//	console.log(businessType);
//	$.ajax({  
//        url :"/json/indexMonitorStatus.action?businessType=" + businessType,  //后台处理程序  
//        type:"get",    //数据接收方式  
//        async:false,  
//        dataType:"json",   //接受数据格式             
//        success: function(json){  
//        	var histogram = $('#histogram' + businessType).highcharts();
//        	while(histogram.series.length > 0) {
//        		histogram.series[0].remove();
//        	}   	
//        	histogram.addSeries({
//        		name: '正常状态',
//        		color: "#4DB361",
//                data: [json.fixedtimeTaskNormalNum, json.realtimeTaskNormalNum, json.offlineTaskNormalNum]
//        	},false);
//        	histogram.addSeries({
//        		name: '异常状态',
//        		color: "#CC3333",
//                data: [json.fixedtimeTaskExceptionNum, json.realtimeTaskExceptionNum, json.offlineTaskExceptionNum]
//        	},false);        	
//        	histogram.redraw();
//        	
//        	
//        	var piechart = $('#pieChart' + businessType).highcharts();
//        	while(piechart.series.length > 0) { 
//        		piechart.series[0].remove();
//        	}
//        	piechart.addSeries({
//        		name: '个数',
//        		type: 'pie',
//        		data:[
//					{
//					    name: '正常总数',
//					    y: json.totalNormalNum,
//					    color: '#4DB361'
//					},
//					{
//						name: '异常总数',
//						y: json.totalExceptionNum,
//						color: '#CC3333'
//					},
//                ]
//        	},false);
//        	piechart.redraw();
//        	
//        	$('#totalMonitorNum'+ businessType).html(json.totalNum);
//        	$('#normalMonitorNum'+ businessType).html(json.totalNormalNum);
//        	$('#exceptionMonitorNum'+ businessType).html(json.totalExceptionNum);
//        	
//        },
//		error: function(){  
//			alert("服务器没有返回数据，可能服务器忙，请重试");  
//		}
//     
//	});           
	
})

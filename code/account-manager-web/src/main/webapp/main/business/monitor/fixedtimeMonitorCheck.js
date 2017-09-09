function testCheck(id,obj){
	$.ajax({
		url: "/monitor/site_validateFixedtimeUrl.action?id=" + id,
		type: "get",
		cache: false,
		beforeSend: function(){
			$(obj).attr("disabled","true").html('<span class="glyphicon glyphicon-eye-open"></span>加载中...');
		},
		success: function(d){
			$(obj).removeAttr("disabled").html('<span class="glyphicon glyphicon-eye-open"></span>验证');
			$.sticky(d, {autoclose : 5000, position: "top-right", type: "st-success" });
		},
		error: function(){
			$(obj).removeAttr("disabled").html('<span class="glyphicon glyphicon-eye-open"></span>验证');
			$.sticky("ajax error", {autoclose : 5000, position: "top-right", type: "st-error" });
		},
		complete: function(){
		}
	});
}
function switchCheck(id,obj){
	$.ajax({
		url: "/monitor/site_switchFixedtime.action?id=" + id,
		type: "get",
		cache: false,
		beforeSend: function(){
		},
		success: function(d){
			if(d > 0){
				$.sticky("关闭开关", {autoclose : 5000, position: "top-right", type: "st-error" });
				$(obj).parent().prev().prev().html("关");
			}else{
				$.sticky("打开开关", {autoclose : 5000, position: "top-right", type: "st-success" });
				$(obj).parent().prev().prev().html("开");
			}
		},
		error: function(){
			$.sticky("ajax error", {autoclose : 5000, position: "top-right", type: "st-error" });
		},
		complete: function(){
		}
	});
}
$(function(){
	setInterval(function(){
		$.ajax({
			url: "/json/fixedListStatus.action",
			type: "get",
			cache: false,
			dataType: "json",
			beforeSend: function(){
			},
			success: function(data){
				var d = data.fixedtimeServiceTaskList;
				for(var index in d){
					var stat = d[index].status;
					var id = d[index].id;
					var oldClass = stat == 0 ? "danger" : "success";
					var trClass = stat == 0 ? "success" : "danger";
					$("#t"+id).removeClass(oldClass).addClass(trClass);
				}
			},
			error: function(){
				$.sticky("ajax error", {autoclose : 5000, position: "top-right", type: "st-error" });
			},
			complete: function(){
			}
		})
	},3000)
})
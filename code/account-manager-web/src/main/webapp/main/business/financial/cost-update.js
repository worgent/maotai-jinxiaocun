    
$(function(){
	$(".datetimepicker").datetimepicker({
		language: "zh-CN"
	})

	$('#btnSave').click(function () {
		var postData = {
			id:$('input[id=id]').val(),
			businessDate: $('input[id=business_date]').val(),
			amount: $('input[id=amount]').val(),
			handler: $("#handler").val(),
			type: $('select[id=type]').val(),
			remark: $('textarea[id=remark]').val()
		}
		var option =
		{
			type: 'POST',
			data: JSON.stringify(postData),
			url: "/financial/cost/ajax/update.html",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			success: function (result) {
				if (result == "1") {
					$.sticky("成功.", {autoclose : 5000, position: "top-right", type: "st-success" });
					window.location.href = "/financial/cost/list.html";

				} else {
					$.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-success" });
				}
			}
		};
		$.ajax(option);
	});

})
    
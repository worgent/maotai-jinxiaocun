/* [ ---- Gebo Admin Panel - validation ---- ] */


$(function() {
	$(".datetimepicker").datetimepicker({
		language: "zh-CN"
	})
	$('#btnSave').click(function () {
		var postData = {
			customerId: $("#customerId").val(),
			businessDate: $('input[id=businessDate]').val(),
			amount: $('input[id=amount]').val(),
			handler: $("#handler").val(),
			type: $('select[id=type]').val(),
			remark: $('textarea[id=remark]').val()
		}
		var option =
		{
			type: 'POST',
			data: JSON.stringify(postData),
			url: "/financial/receive/ajax/add.html",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			success: function (result) {
				if (result == "1") {
					$.sticky("成功.", {autoclose: 5000, position: "top-right", type: "st-success"});
					window.location.href = "/financial/receive/history.html";
				} else {
					$.sticky("失败.", {autoclose: 5000, position: "top-right", type: "st-success"});
				}
			}
		};
		$.ajax(option);
	});
})
	
    
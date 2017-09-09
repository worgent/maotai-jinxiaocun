/* [ ---- MyApp View - edittable ---- ] */

/* [ ---- Gebo Admin Panel - validation ---- ] */

$.fn.serializeObject = function(){
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

//* validation
gebo_validation = {
	ttip: function() {
		var ttip_validator = $('.form_validation_ttip').validate({
			onkeyup: false,
			errorClass: 'error',
			validClass: 'valid',
			highlight: function(element) {
				$(element).closest('div').addClass("f_error");
			},
			unhighlight: function(element) {
				$(element).closest('div').removeClass("f_error");
			},
			rules: {
				"serailNumber": { required: true},
				"name": { required: true},
				"officePhone": { digits: true},
				"contactPhone": {  digits: true },
				"email": {  email: true },
				"qq": {  number: true },
			},
			submitHandler: function(form, validator){
				$.ajax({
					type: 'post',
					url: form.action,
					dataType: "text",
					data: $("form").serializeObject(),
					success: function(data) {
						if(data == "1"){
							$.sticky("成功.", {autoclose : 5000, position: "top-right", type: "st-success" });
							window.location.href = window.location.origin + "/sale/customer/list.html";
						}else{
							$.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-success" });
						}
					},
					error: function(){
						$.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-error" });
					}
				});
				return;
			},
			invalidHandler: function(form, validator) {

			},
			errorPlacement: function(error, element) {
				// Set positioning based on the elements position in the form
				var elem = $(element);

				// Check we have a valid error message
				if(!error.is(':empty')) {
					if( (elem.is(':checkbox')) || (elem.is(':radio')) ) {
						// Apply the tooltip only if it isn't valid
						elem.filter(':not(.valid)').parent('label').parent('div').find('.error_placement').qtip({
							overwrite: false,
							content: error,
							position: {
								my: 'left bottom',
								at: 'center right',
								viewport: $(window),
								adjust: {
									x: 6
								}
							},
							show: {
								event: false,
								ready: true
							},
							hide: false,
							style: {
								classes: 'ui-tooltip-red ui-tooltip-rounded' // Make it red... the classic error colour!
							}
						})
							// If we have a tooltip on this element already, just update its content
							.qtip('option', 'content.text', error);
					} else {
						// Apply the tooltip only if it isn't valid
						elem.filter(':not(.valid)').qtip({
							overwrite: false,
							content: error,
							position: {
								my: 'bottom left',
								at: 'top right',
								viewport: $(window),
								adjust: { x: -8, y: 6 }
							},
							show: {
								event: false,
								ready: true
							},
							hide: false,
							style: {
								classes: 'ui-tooltip-red ui-tooltip-rounded' // Make it red... the classic error colour!
							}
						})
							// If we have a tooltip on this element already, just update its content
							.qtip('option', 'content.text', error);
					};

				}
				// If the error is empty, remove the qTip
				else {
					if( (elem.is(':checkbox')) || (elem.is(':radio')) ) {
						elem.parent('label').parent('div').find('.error_placement').qtip('destroy');
					} else {
						elem.qtip('destroy');
					}
				}
			},
			//$.noop // Odd workaround for errorPlacement not firing!
			success: $.noop
		})
	}

};



$(function() {
	// * myapp with edittable
	myapp_edittable.init();
	gebo_validation.ttip();
	$(".datetimepicker").datetimepicker({
		language: "zh-CN"
	})
	$("#order_save").click(function(){
		myapp_edittable.send();
	})
	$("#discountRate").change(function(){
		myapp_edittable._calAccount();
	})
//	myapp_edittable.load();
});


// * validation
myapp_edittable = {
	_eTable : {},
	init : function() {
		Gobal_Commodity_Select = $.trim($("#commodity_info").remove().html());
		_eTable = $("#edittable").editTable({
			data : [ [] ],
			// maxRows: 3,
			first_row : false,
			headerCols : ['序号','商品名称/规格', '商品编号', '属性', '单位', '数量' ,'单价', '金额', '备注'],
			row_template : ['text','select','text','text','text','text','text','text','text'],
			field_templates : {
				select : {
					html : Gobal_Commodity_Select,
//					html : '<select><option value="1">是</option><option value="0">否</option></select>',
					setValue : function(fieldHtml,content){
						//console.log("index:" + content);
//						if(content == 1){
//							return $('<select><option value="1" selected>是</option><option value="0">否</option></select>');
//						}else{
//							return $('<select><option value="1">是</option><option value="0" selected>否</option></select>');
//						}
						return $(Gobal_Commodity_Select);
					},
					getValue : function(el){
						return el.val();
					}
				}
			}
		});
//		$("#editor").click(function() {
//			myapp_edittable.send();
//		});
//		$("table").on("click", "a.delrow", function() {
//			var v = $(this).closest("tr").find("input").eq(0).val();
//			myapp_edittable.del(v);
//		})
		$(".inputtable").on("change","select",function(){
			var storeId = $(this).val();
			var $obj = $(this);
			$.ajax({
				type: 'get',
				url: window.location.origin + "/commodity/ajax/get.html",
				dataType: "text",
				data: {
					"id": storeId
				},
				success: function(data) {
					var d = $.parseJSON(data);
					
					$tr = $obj.closest("tr");
					$tr.find("td:eq(0) input").attr("disabled","true").val(d.id);
					$tr.find("td:eq(2) input").attr("disabled","true").val(d.serailNumber);
					$tr.find("td:eq(3) input").attr("disabled","true").val(d.specification);
					$tr.find("td:eq(4) input").attr("disabled","true").val(d.unit);
					$tr.find("td:eq(5) input").addClass("cal").val(1);
					$tr.find("td:eq(6) input").attr("disabled","true").val(d.retailPrice / 100);
					$tr.find("td:eq(7) input").addClass("cal_sum").attr("disabled","true").val(1 * d.retailPrice / 100);
					
					myapp_edittable._calAccount();
					
				},
				error: function(){
					
				}
			});
			
		})
		$("#edittable").on("change","input.cal",function(){
			$td = $(this).parent();
			$td_n1_input = $td.next().find("input");
			$td_n2_input = $td.next().next().find("input");
			var sum = $(this).val() * $td_n1_input.val()
			$td_n2_input.val(sum);
			
			myapp_edittable._calAccount();
			
		})
	},
	_calAccount: function(){
		var temp = 0;
		$("#edittable input.cal_sum").each(function(){
			temp += parseFloat($(this).val());
		})
		
		$("#p_cal_sum").html(temp);
		var discountRate = $("#discountRate").val() != "" ? parseFloat($("#discountRate").val()) : 100;
		$("#n_cal_sum").html(temp * discountRate / 100);
	},
	load : function() {
		$.ajax({
			url : $("#edittable_load_url").text(),
			type : 'get',
			// dataType : 'json',
			cache : false,
			async : false,
			beforeSend : function() {
				// _eTable.loadJsonData([["正在加载中..."]]);
			},
			success : function(data) {
				_eTable.loadJsonData(data);
				if(!$("tr td a.addrow").hasClass("disabled")){
					$("tr td a.addrow").addClass("disabled");
				};
				$('table.inputtable tbody tr').each(function(){
					$(this).find("td:eq(0) input").prop("readonly",true)
					.keydown(function(e){
						var keyEvent; 
					    if(e.keyCode==8){ 
					        var d=e.srcElement||e.target; 
					        if(d.tagName.toUpperCase()=='INPUT'||d.tagName.toUpperCase()=='TEXTAREA'){ 
					            keyEvent=d.readOnly||d.disabled; 
					        }else{ 
					            keyEvent=true; 
					        } 
					    }else{ 
					        keyEvent=false; 
					    } 
					    if(keyEvent){ 
					        e.preventDefault(); 
					    } 
					});
					//.css("background","#F1F1F1").end().find("td:eq(0)").css("background","#F1F1F1");
				});
			},
			complete : function(result) {
			}
		});
	},
	send : function() {
//		$.ajax({
//			url : $("#edittable_send_url").text(),
//			type : 'post',
//			dataType : 'json',
//			cache : false,
//			data : {
//				data : _eTable.getJsonData()
//			},
//			beforeSend : function() {
//				$("#editor").prop("disabled", true);
//			},
//			success : function(data) {
//				_eTable.loadJsonData(data);
//			},
//			complete : function(result) {
//				$("#editor").prop("disabled", false);
//			}
//		});
		var storeId = $("#storeId").val();
		var storeName = $("#storeId option:selected").text();
		var customerId = $("#customerId").val();
		var customerName = $("#customerId option:selected").text();
		var handler = $("#handler").val();
		var handlerName = $("#handle option:selected").text();
		var businessDate = $("#businessDate").val();
		var discountRate = $("#discountRate").val();
		
		var eData = _eTable.getData();
		
		for(var i in eData){
			eData[i][6] = eData[i][6] * 100;
			eData[i][7] = eData[i][7] * 100;
		}
		
		var data = {
			"storeId": storeId,
			"storeName": storeName,
			"customerId": customerId,
			"customerName": customerName,
			"handler": handler,
			"handlerName": handlerName,
			"businessDate": businessDate,
			"discountRate": discountRate,
			"eData" : eData
		} 
		
		var dataStr = JSON.stringify(data);
		
		$.ajax({
			type: 'post',
			url: window.location.origin + "/sale/order/addResult.html",
			dataType: "text",
			data: {
				"data": dataStr
			},
			success: function(data) {
				if(data == "1"){
					$.sticky("成功.", {autoclose : 5000, position: "top-right", type: "st-success" });
					window.location.href = window.location.origin + "/sale/order/list.html";
				}else{
					$.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-error" });
				}
			},
			error: function(){
				$.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-error" });
			}
		});
		
	},
	del : function(id) {
		$.ajax({
			url : $("#edittable_del_url").text(),
			type : 'get',
			data : {
				machineId : id
			},
			cache : false,
			beforeSend : function() {
				$("#editor").prop("disabled", true);
			},
			success : function(data) {

			},
			complete : function(result) {
				$("#editor").prop("disabled", false);
			}
		});
	}
};
    
    
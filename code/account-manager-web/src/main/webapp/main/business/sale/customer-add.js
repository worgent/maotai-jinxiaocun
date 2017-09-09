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

	$(document).ready(function() {
		
		//* validation with tooltips
		gebo_validation.ttip();
		//* regular validation
//		gebo_validation.reg();
	});
	
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
	
    
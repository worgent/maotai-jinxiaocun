/* [ ---- Gebo Admin Panel - validation ---- ] */

	$(document).ready(function() {
		//* validation with tooltips
		gebo_validation.ttip();
		//* regular validation
		gebo_validation.reg();
		// uniform style
		gebo_uniform.init();
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
                	"offlineServiceTask.name": { required: true},
                	"offlineServiceTask.url": { required: true},
                	"offlineServiceTask.monitorType": { required: true},
                	"offlineServiceTask.businessType": { required: true},
                	"offlineServiceTask.monitorRule": { required: true},
                	"offlineServiceTask.exceptionAlarmInterval": { required: true},
                	"offlineServiceTask.requestContent": { required: true},
                	"offlineServiceTask.memo": { required: true}
				},
				invalidHandler: function(form, validator) {
					$.sticky("页面上有一些错误. 请更正这些错误后再提交", {autoclose : 5000, position: "top-right", type: "st-error" });
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
				success: $.noop // Odd workaround for errorPlacement not firing!
			})
		},
        reg: function() {
            reg_validator = $('.form_validation_reg').validate({
				onkeyup: false,
				errorClass: 'error',
				validClass: 'valid',
				highlight: function(element) {
					$(element).closest('div').addClass("f_error");
				},
				unhighlight: function(element) {
					$(element).closest('div').removeClass("f_error");
				},
                errorPlacement: function(error, element) {
                    $(element).closest('div').append(error);
                },
                rules: {
                	"offlineServiceTask.name": { required: true},
                	"offlineServiceTask.url": { required: true},
                	"offlineServiceTask.monitorType": { required: true},
                	"offlineServiceTask.businessType": { required: true},
                	"offlineServiceTask.monitorRule": { required: true},
                	"offlineServiceTask.exceptionAlarmInterval": { required: true},
                	"offlineServiceTask.requestContent": { required: true},
                	"offlineServiceTask.memo": { required: true}
				},
                invalidHandler: function(form, validator) {
					$.sticky("页面上有一些错误. 请更正这些错误后再提交", {autoclose : 5000, position: "top-right", type: "st-error" });
				}
            })
        },
	};
	//* uniform
    gebo_uniform = {
		init: function() {
            $(".uni_style").uniform();
        }
    };
 
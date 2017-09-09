/* [ ---- Gebo Admin Panel - extended form elements ---- ] */

	$(document).ready(function() {
		gebo_chosen.init();
	})
	
	//* enhanced select elements
	gebo_chosen = {
		init: function(){
			$(".chzn_a").chosen({
				allow_single_deselect: true
			});
			$(".chzn_b").chosen();
		}
	};
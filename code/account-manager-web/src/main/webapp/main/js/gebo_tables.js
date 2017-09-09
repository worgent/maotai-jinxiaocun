/* [ ---- Gebo Admin Panel - tables ---- ] */
	var delete_config = true;
	$(document).ready(function() {
		
		//* actions for tables, datatables
        gebo_select_row.init();
		
		//* datatable must be rendered first
		gebo_galery_table.init();
        gebo_galery_column.init();
        
		// gebo_delete_rows.simple();
		gebo_delete_rows.dt();
        gebo_delete_rows.dt_d();
        
	});

	//* select all rows
	gebo_select_row = {
		init: function() {
			$('.select_rows').click(function () {
				var tableid = $(this).data('tableid');
                $('#'+tableid).find('input[name=row_sel]').attr('checked', this.checked);
			});
			$('.delete_rows').click(function (e) {
				e.preventDefault();
				var oTr = $(this);
				var tableid = oTr.data('delid');
				var _url = oTr.data('delurl');
				if(delete_config){
					if(!confirm("确定要删除吗？")){
						return false;
					}
				}
				delete_config = true;
				$.ajax({
					url: _url,
					data: {id: tableid},
					type: "get",
					success: function(d){
						if(d && d > 0){
							oTr.closest('tr').fadeTo(300, 0, function () { 
                                $(this).remove();
                            });
						}
					}
				})
			});
		}
	};
	
	//* delete rows
	gebo_delete_rows = {
		simple: function() {
			$(".delete_rows_simple").on('click',function (e) {
				e.preventDefault();
				var tableid = $(this).data('tableid');
                if($('input[name=row_sel]:checked', '#'+tableid).length) {
                    $.colorbox({
                        initialHeight: '0',
                        initialWidth: '0',
                        href: "#confirm_dialog",
                        inline: true,
                        opacity: '0.3',
                        onComplete: function(){
                            $('.confirm_yes').click(function(e){
                                e.preventDefault();
                                $('input[name=row_sel]:checked', '#'+tableid).closest('tr').fadeTo(300, 0, function () {
                                    $(this).remove();
                                    $('.select_rows','#'+tableid).attr('checked',false);
                                });
                                $.colorbox.close();
                            });
                            $('.confirm_no').click(function(e){
                                e.preventDefault();
                                $.colorbox.close(); 
                            });
                        }
                    });
                }
			});
		},
        dt_d: function() {
        
            function fnShowHide( iCol,oTable ) {
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                //alert(oTable);
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis( iCol, bVis ? false : true );
            };
            
            $('#dt_d_nav').on('click','li input',function(){
                var tableid = $(this).closest("#dt_d_nav").data('tableid'),
                    oTable = $('#'+tableid).dataTable();
                fnShowHide( $(this).val(),oTable );
            });
        },
        dt: function() {
			$(".delete_rows_dt").on('click',function (e) {
				e.preventDefault();
				var tableid = $(this).data('tableid'),
                    oTable = $('#'+tableid).dataTable();
                if($('input[name=row_sel]:checked', '#'+tableid).length) {
                    $.colorbox({
                        initialHeight: '0',
                        initialWidth: '0',
                        href: "#confirm_dialog",
                        inline: true,
                        opacity: '0.3',
                        onComplete: function(){
                            $('.confirm_yes').click(function(e){
                                e.preventDefault();
                                $('input[name=row_sel]:checked', oTable.fnGetNodes()).closest('tr').fadeTo(300, 0, function () {
                                	delete_config = false;
                                	$(this).find("a.delete_rows").trigger("click");
//                                  $(this).remove();
//									oTable.fnDeleteRow( this );
                                    $('.select_rows','#'+tableid).attr('checked',false);
                                });
                                $.colorbox.close();
                            });
                            $('.confirm_no').click(function(e){
                                e.preventDefault();
                                $.colorbox.close(); 
                            });
                        }
                    });
                }    
			});
		}
	};

    //* column select mouseover
    gebo_galery_column = {
        init: function() {
//            $('.sepH_b div.btn-group').mouseenter(function() {
//                $(this).addClass('navHover')
//            }).mouseleave(function() {
//                $(this).removeClass('navHover open')
//            });
            $("#dt_d_nav").click(function(event){
            	event.stopPropagation();
            });
        }
    	
    };
	

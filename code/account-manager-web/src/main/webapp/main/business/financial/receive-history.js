    //* gallery table view
    gebo_galery_table = {
        init: function() {
            $('#dt_gal').dataTable({
                "sDom": "<'row'<'col-sm-6'<'dt_actions'><'dt_actions_add'>l><'col-sm-6'>r>t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            	"sPaginationType": "bootstrap",
                "aaSorting": [[ 8, "desc" ]],
                "oLanguage" : {
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "对不起，查询不到任何相关数据",
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "sInfoEmpty": "对不起，查询不到任何相关数据",
                    "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
//	                "sZeroRecords": "对不起，查询不到任何相关数据",
                    "sSearch": "搜索:",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                    }
                }
            });
//            $('.dt_actions').html($('.dt_gal_actions').html());
//            $('.dt_actions_add').html($('.dt_gal_actions_add').html());
        },
        bindDel: function(){
            $("#dt_gal").on("click","a.delete_rows",function(){
                var id = $(this).data("delid");
                layer.confirm('确认要删除不', function(index){
                    $.ajax({
                        type: 'get',
                        url:  "/financial/receive/ajax/delete.html",
                        dataType: "text",
                        data: {
                            "id": id
                        },
                        success: function(data) {
                            if(data == "1"){
                                $.sticky("成功.", {autoclose : 5000, position: "top-right", type: "st-success" });
                                dataTableIndex.fnDeleteRow($("#" + id).get(0))
                            }else{
                                $.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-success" });
                            }
                        },
                        error: function(){
                            $.sticky("失败.", {autoclose : 5000, position: "top-right", type: "st-error" });
                        }
                    });
                    layer.close(index);
                });

            })
        }

    };
    
    $(function(){
    	gebo_galery_table.init();
        gebo_galery_table.bindDel();

        $(".datetimepicker").datetimepicker({
    		language: "zh-CN"
    	})
    })
    
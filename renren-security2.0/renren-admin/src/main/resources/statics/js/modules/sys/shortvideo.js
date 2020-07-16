$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'short-video/page',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 40, key: true },
			{ label: '短视频发布者id', name: 'userId', index: 'user_id', width: 80 },
			{ label: '书店名', name: 'deptName', index: 'dept_name', width: 80 },
			{ label: '参赛人', name: 'competitor', index: 'competitor', width: 80 }, 			
			{ label: '标题', name: 'name', index: 'name', width: 80 }, 			
			{ label: '简介', name: 'description', index: 'description', width: 80 }, 			
			// { label: '视频封面URL', name: 'posterUrl', index: 'poster_url', width: 80 },
			{ label: '视频url', name: 'url', index: 'url',width: 80,
                formatter:function(cellvalue, options, rowObject){return "<a href= '"+ cellvalue +"' target=\"_blank\">参赛作品</a>";} },
			{ label: '播放量', name: 'viewNumber', index: 'view_number', width: 50 },
			{ label: '点赞数', name: 'likeNumber', index: 'like_number', width: 50 },
            { label: '评选阶段', name: 'stage', index: 'stage',width: 60, formatter: function(value, options, row){
                    if (value == "preliminary")
                        return '<span class="label label-default">初选作品</span>';
                    else if(value == "semi-final")
                        return '<span class="label label-primary">入围作品</span>';
                    else if(value == "final")
                        return '<span class="label label-success">获奖作品</span>';
                }},
			{ label: '视频状态更新时间', name: 'updateTime', index: 'update_time', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            competitor: null
        },
		showList: true,
		title: null,
		shortVideo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.shortVideo = {};
            this.allowInput();
		},

        update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
            this.allowInput();
		},
        //允许表单输入
        allowInput: function () {
            document
                .getElementById('competitor')
                .removeAttribute('disabled',)
            document
                .getElementById('name')
                .removeAttribute('disabled');
            document
                .getElementById('description')
                .removeAttribute('disabled',);
            document
                .getElementById('posterUrl')
                .removeAttribute('disabled');
            document
                .getElementById('url')
                .removeAttribute('disabled');


        },
        //更改评选阶段时 禁止更改其他数据
        banInput: function () {
            document
                .getElementById('competitor')
                .setAttribute('disabled', 'disabled');
            document
                .getElementById('name')
                .setAttribute('disabled', 'disabled');
            document
                .getElementById('description')
                .setAttribute('disabled', 'disabled');
            document
                .getElementById('posterUrl')
                .setAttribute('disabled', 'disabled');
            document
                .getElementById('url')
                .setAttribute('disabled', 'disabled');
        },
        updateStage: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改参赛者状态";
            vm.getInfo(id)
            this.banInput();

        },
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.shortVideo.id == null ? "short-video/save" : "short-video/updateStage";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.shortVideo),
                    success: function(r){
                        if(r.code === 201){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "short-video/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 200){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "short-video/info?id="+id, function(r){
                vm.shortVideo = r.shortVideo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                competitor:{'competitor':vm.q.competitor},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

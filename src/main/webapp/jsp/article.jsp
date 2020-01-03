<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<style>

.page-header {
padding-bottom: 9px;
margin: -27px -3px 3px;
border-bottom: 1px solid #eee;
}
</style>
<script>
    var editor1;
    $(function () {

        $("#bannerList").jqGrid({
            url:"${pageContext.request.contextPath}/Article/queryall",
            datatype:"json",
            styleUI:"Bootstrap",
            colNames:["id","title","author","content","guru_id","create_date","status","操作"],
            pager:"#bannerPager",
            rowNum:2,
            autowidth:true,
            multiselect:true,
            height:'400px',
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"author",editable:true},
                {name:"content",editable:true},
                {name:"author",editable:true},
                {name:"create_date",edittype:'date',formatter:"date",formatoptions: {srcformat:'u',newformat:'Y-m-d H:i:s'}},
                {name:"status",editable:true,edittype:'select',editoptions: {value:"激活:激活;未激活:未激活"}},
                {name:"操作",editable:true,formatter:function (cellvalue, options, rowObject){
                        return "<button class='btn btn-danger' id='upd' onclick=\"xianshis('"+rowObject.id+"')\">修改 <span class='glyphicon glyphicon-play'></span></button>"
                        +"<button class='btn btn-primary' id='show' onclick=\"chakan('"+rowObject.id+"')\">查看<span class='glyphicon glyphicon-play'></span></button>"
                    }}
            ]
        }).jqGrid("navGrid","#articlePager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"})
    })


        $("#profile-tab").click(function() {
            $('#myModal').modal('show');
        })

        var editor = KindEditor.create("textarea[name='content']",{
                width:'100px',  //宽  类型是字符串
                minHeight:400,
                minWidth:800,
                resizeType:0,
                allowFileManager:true,    //是否展示 图片空间
                filePostName:'img',       //上传是后台接收的名字
                uploadJson:'${pageContext.request.contextPath}/kindeditor/uploadImg', //上传后台的路径
                fileManagerJson:"${pageContext.request.contextPath}/kindeditor/getimgs"
            });
         editor1 = KindEditor.create("textarea[name='content2']",{
            width:'100px',  //宽  类型是字符串
            minHeight:400,
            minWidth:800,
            resizeType:0,
            allowFileManager:true,    //是否展示 图片空间
            filePostName:'img',       //上传是后台接收的名字
            uploadJson:'${pageContext.request.contextPath}/kindeditor/uploadImg', //上传后台的路径
            fileManagerJson:"${pageContext.request.contextPath}/kindeditor/getimgs",

        });
                html = editor.html();

            $("#add").click(function () {
                editor.sync();
                var text = $("#addInfo").serialize();
                $.ajax({
                    url:"${pageContext.request.contextPath}/kindeditor/addtext",
                    data:text,
                    datatype:"text",
                    success:function (ss) {
                        $('#myModal').modal('hide')
                        $("bannerList").trigger('reloadGrid')
                    }
                })
            })


    function xianshis(data){
        $.ajax({
            url:"${pageContext.request.contextPath}/kindeditor/querybyid",
            datatype:"json",
            data:{"id":data},
            success:function (result) {
                $("#id2").val(result.id)
                $("#title2").val(result.title)
                $("#author2").val(result.author)
                editor1.html(result.content)
                $("#guru_id2").val(result.guru_id)
                $("#status2").val(result.status)
                $("#myModal2").modal("show")
            }

        })
    }
    function chakan(data){
        $.ajax({
            url:"${pageContext.request.contextPath}/kindeditor/querybyid",
            datatype:"json",
            data:{"id":data},
            success:function (result) {
                location.href="${pageContext.request.contextPath}/jsp/show.jsp"
            }

        })
    }
    $("#add2").click(function () {
        editor1.sync();
        var text = $("#addInfo2").serialize();
        $.ajax({
            url:"${pageContext.request.contextPath}/kindeditor/update",
            data:text,
            datatype:"text",
            success:function () {
                $('#myModal2').modal('hide')
                $("#bannerList").trigger('reloadGrid')
            }
        })
    })
</script>
<div class="page-header">
    <h3>文章相关</h3>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <ul class="nav nav-tabs" id="myTabs" role="tablist">
            <li class="active" role="presentation"><a id="home-tab" role="tab" aria-expanded="true" aria-controls="home" href="#home" data-toggle="tab">文章查看</a></li>
            <li role="presentation"><a id="profile-tab" role="tab" aria-controls="profile" href="#profile" data-toggle="tab">添加文章</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <table id="bannerList"></table>
        <div id="articlePager" style="height: 50px"></div>
    </div>

</div>


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form id="addInfo" class="form-horizontal">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label col-sm-offset-2">姓名</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label col-sm-offset-2">作者</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="author" name="author">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="guru_id" class="col-sm-2 control-label col-sm-offset-2">所属上师</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="guru_id" name="guru_id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label col-sm-offset-2">激活状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="status" name="status">
                                <option value="激活">激活</option>
                                <option value="未激活">未激活</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editor_id" class="col-sm-2 control-label col-sm-offset-2"></label>
                        <textarea id="editor_id" name="content" style="width:570px;height:300px;">
                            请输入内容
                        </textarea>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="add">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="myModal2">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form id="addInfo2" class="form-horizontal">
                    <input type="hidden" name="id" id="id2"/>
                    <div class="form-group">
                        <label for="title2" class="col-sm-2 control-label col-sm-offset-2">姓名</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="title2" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author2" class="col-sm-2 control-label col-sm-offset-2">作者</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="author2" name="author">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="guru_id2" class="col-sm-2 control-label col-sm-offset-2">所属上师</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="guru_id2" name="guru_id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status2" class="col-sm-2 control-label col-sm-offset-2">激活状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="status2" name="status">
                                <option value="激活">激活</option>
                                <option value="未激活">未激活</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editor_id2" class="col-sm-2 control-label col-sm-offset-2"></label>
                        <textarea id="editor_id2" name="content2" style="width:570px;height:300px;">

                        </textarea>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="add2">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
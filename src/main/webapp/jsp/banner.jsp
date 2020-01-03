<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url:"${pageContext.request.contextPath}/banner/queryall",
            editurl:"${pageContext.request.contextPath}/banner/edit",
            datatype:"json",
            styleUI:"Bootstrap",
            colNames:["id","title","img","create_time","status"],
            pager:"#bannerPager",
            rowNum:2,
            autowidth:true,
            multiselect:true,
            height:'400px',
            colModel:[
                {name:"id"},
                {name:"title",'editable':true},
                {name:"img",editable:true,edittype:'file',
                    formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>";
                    }
                },
                {name:"create_date",edittype:'date',formatter:"date",formatoptions: {srcformat:'u',newformat:'Y-m-d H:i:s'}},
                {name:"status",editable:true,edittype:'select',editoptions: {value:"激活:激活;未激活:未激活"}}
            ]
        }).jqGrid("navGrid","#bannerPager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},{
            /**
             *  修改
             * */
            afterSubmit:function (response) {
                var id = response.responseJSON.id;
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/banner/motifyUpload",
                    fileElementId:'img',
                    data:{id:id},
                    type:"post",
                    success:function () {
                        $("#bannerList").trigger('reloadGrid')
                        $("#updDiv").show();
                        setTimeout(function () {
                            $("#updDiv").hide();
                        },3000)
                    }
                })
                return response;
            },
                closeAfterAdd:true,
            trigger:("reloadGrid")
        },
            {
                /*
                    * 添加
                    * */
                afterSubmit:function (response) {
                    var id = response.responseJSON.id;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/bannerUpload",
                        fileElementId:'img',
                        data:{id:id},
                        type:"post",
                        success:function () {
                            $("#bannerList").trigger('reloadGrid')
                            $("#msgDiv").show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            },3000)
                        }
                    })
                    return response;
        },
                closeAfterAdd:true
            },
            {
             /**
              * 删除
              * */

            }
            );
    })

</script>

<div class="panel panel-default">
    <div class="panel-heading">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图查看</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <table id="bannerList"></table>
    </div>
</div>

<div id="bannerPager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv">
    添加成功
</div>
<div class="alert alert-success" style="display:none" id="updDiv">
    修改成功
</div>
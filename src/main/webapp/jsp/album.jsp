<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<style>
    .ui-jqgrid .ui-userdata {
        padding: 13px 94px;
        overflow: hidden;
        min-height: 32px;
    }
</style>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url:"${app}/Album/queryallmain",
            styleUI:"Bootstrap",
            datatype:"json",
            autowidth:true,
            records:true,
            rowNum:3,
            height:400,
            caption: "专辑",
            pager:"#albumPager",
            colNames:[
                "id","title","img","score","author","broadcaster","count","brief","create_date","status"
            ],
            colModel:[
                {name:"id"},
                {name:"title"},
                {name:"img",
                        formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>";
                        }
                    },
                {name:"score"},
                {name:"author"},
                {name:"broadcaster"},
                {name:"count"},
                {name:"brief"},
                {name:"create_date"},
                {name:"status"}
            ],
            subGrid:true,         //开启子表格
            subGridRowExpanded:function (subGridId,albumId) {
                //添加子表格的方法
                addSubGrid(subGridId,albumId);
            }
        })
    })

    //添加子表格
    function addSubGrid(subGridId,albumId) {
        //动态table  id
        var subGridTableId = subGridId + "table";
        //动态div id
        var subGridDivId = subGridId + "div";
        //动态添加子表格
        $("#"+subGridId).html("<table id='"+subGridTableId+"'></table>"+
                               "<div id='"+subGridDivId+"' style='height: 50px'></div>"
                              )
        $("#"+subGridTableId).jqGrid({
            url:"${app}/Album/queryallson?album_id="+albumId,
            editurl:"${app}/Album/music?album_id="+albumId,
            styleUI:"Bootstrap",
            datatype:"json",
            autowidth:true,
            records:true,
            rowNum:3,
            caption:"章节",
            multiselect:true,
            toolbar:[true,"top"],
            pager:"#"+subGridDivId,
            colNames: [
                "ID","TITLE","ALBUM_ID","SIZE","DURATION","SRC","STATUS"
            ],
            colModel: [
                {name:"id"},
                {name:"title",editable: true},
                {name:"album_id"},
                {name:"size"},
                {name:"duration"},
                {name:"src",
                    editable: true,edittype:'file'/*,formatter:function (value,option,rows) {
                        return "<audio controls loop preload='auto' id='song'>\n" +
                            "<source src='{pageContext.request.contextPath}/music/"+value+"' type='audio/mpeg'>\n"+
                            "</audio>"+
                            "";
                    }*/},
                {name:"status",editable:true,edittype:'select',editoptions: {value:"正常:正常;下架:下架"}}
            ]
        }).jqGrid("navGrid","#"+subGridDivId,{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},{
                /**
                 *  修改
                 * */
                afterSubmit:function (response) {
                    var id = response.responseJSON.id;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/Album/fileedit",
                        fileElementId:'src',
                        data:{id:id},
                        type:"post",
                        success:function () {
                            $("#"+subGridTableId).trigger('reloadGrid')
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
                        url:"${pageContext.request.contextPath}/Album/AlbumUpload",
                        fileElementId:'src',
                        data:{id:id},
                        type:"post",
                        success:function () {
                            $("#"+subGridTableId).trigger('reloadGrid')
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


        //添加按钮
        $("#t_"+subGridTableId).html("<button class='btn btn-danger' onclick=\"play('"+subGridTableId+"')\">播放 <span class='glyphicon glyphicon-play'></span></button>"+
                                       "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                       "<button class='btn btn-danger'onclick=\"download('"+subGridTableId+"')\">下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"
                                     )
    }
    function play(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要播放的音频");
        }else{
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
            console.log(data.src)
            $('#myModal').modal('show');
            $("#myAudio").attr("src","${app}/music/"+data.src);
        }
    }


    //播放
   /* function play(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要播放的音频");
        }else{
            //1.请求后台

            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
            console.log(data);
        }
    }*/
    function download(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要播放的音频");
        }else{
            //1.请求后台

            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
            var id =data.id;
            location.href="${app}/Album/download?id="+id
        }
    }
</script>

<table id="albumList"></table>
<div id="albumPager" style="height: 50px"></div>

<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">播放器</h4>
            </div>
            <div class="modal-body">
                <audio autoplay controls src="" id="myAudio"></audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>资源列表</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left1.jsp"%>
        <div class="content">
          <div class="condition">
            <form id="condition" role="form" action="<c:url value="/user/resourceInfo"/>"
              method="post">
              <div class="form-inline" style="margin: 10px;">
                <div style="float: right">
                  <a href="<c:url value="/user/resourceInfoAddView"/>">
                  <img src="<c:url value="/image/addresource.png"/>" /><b> 上传资源</b></a>
                </div>
                <label for="name">资源名称：</label> <input type="text" class="form-control" id="name"
                  name="name" value="${domain.name }" /> <input type="hidden" id="curPage"
                  name="curPage" value="${result.curPage }" />
                  <input type="hidden" id="totalPageCount"
                  name="totalPageCount" value="${result.totalPageCount }" />
                <button type="submit" class="btn btn-success">查询</button>
              </div>
            </form>
          </div>
          <div class="info">
            <table class="table table-striped table-hover" style="text-align: center;">
              <thead>
                <tr>
                  <th style="text-align: center;">序号</th>
                  <th style="text-align: left;">资源名称</th>
                  <th style="text-align: center;">创建时间</th>
                  <th style="text-align: center;">操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="resource" items="${result.resourceList }" varStatus="status">
                  <tr>
                    <td>${(result.curPage-1)*result.pageCount+(status.index+1) }</td>
                    <td style="text-align: left;width:70%;">
                      <img  src="<c:url value="/image/filetype/${fn:toLowerCase(resource.suffix) }.png"/>" /> 
                      <span><strong>&nbsp;&nbsp;<a href="${basePath }/download/resourceInfo/${resource.resourceId}" target="_blank">${resource.name }</a></strong><br /> 
                      <small>专业：<span style="color: red">${resource.majorName }</span>&nbsp;&nbsp;</small> 
                      <small>上传者：<span style="color: red">${resource.userName }</span>&nbsp;&nbsp;</small> 
                      <small>文件大小：<span style="color: red">${resource.fileSize }MB</span>&nbsp;&nbsp;</small> 
                      <small>收藏：<span style="color: red">${resource.saveCnt }</span>次&nbsp;&nbsp;</small> 
                      <small>下载：<span style="color: red">${resource.loadCnt }</span>次&nbsp;&nbsp;</small>
                      <small>评价：<span style="color: red">${resource.scoreCnt }</span>次&nbsp;&nbsp;</small>
                      <small>评分：<span style="color: red">${resource.score }</span>分&nbsp;&nbsp;</small>
                      <span class="resourceStar" data-score="${resource.score }"></span><br /> 
                      <small>资源简介：${resource.note }</small>
                    </span></td>
                    <td><small><fmt:formatDate value="${resource.createTime }"
                          pattern="yyyy-MM-dd HH:mm" /></small></td>
                    <td><a href="javascript:downloadFile(${resource.resourceId });" />下载</a> <a
                      href="javascript:deleteFile(${resource.resourceId });" />删除</a></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <div class="pull-right" style="margin-right: 5px;">
            <ul class="pagination">
              <li><a id="prev" href="javascript:prev();">上一页</a></li>
              <li><a href="javascript:first();">首页</a></li>
              <li><a href="#">第${result.curPage }页/共${result.totalPageCount }页</a></li>
              <li><a href="javascript:last();">末页</a></li>
              <li><a id="next" href="javascript:next();">下一页</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <%@include file="../../common/foot.jsp"%>
  </div>
</body>
<%@include file="../../common/tail.jsp"%>
<script type="text/javascript" src="<c:url value="/js/star/jquery.raty.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$("#userCenter").addClass("active");
		$("#resourceInfo").addClass("onSelect");
		$("#currentMenu").html($(".onSelect").html());
		$("#currentPage").html($("title").html());
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			$("#resourceInfo").addClass("onSelect");
		});
		//评分详情
        $(".resourceStar").raty({
        	starOff:'${basePath}/image/star/star-off.png',
        	starOn:'${basePath}/image/star/star-on.png',
        	score:function(){
        		//alert($(this).attr("data-score"));
        		return $(this).attr("data-score");
        	},
        	readOnly:true
        });
	});
	function prev() {
		var prevPage = parseInt($("#curPage").val())-1;
		//alert(prevPage);
		if (prevPage < 1)
			return;
		$("#curPage").val(prevPage);
		$("#condition").submit();
	}
	function first() {
		var curPage = $("#curPage").val();
		if (curPage == 1) {
			return;
		}
		$("#curPage").val(1);
		$("#condition").submit();
	}
	function last() {
		var curPage = $("#curPage").val();
		var lastPage = $("#totalPageCount").val();
		if (curPage >= lastPage)
			return;
		$("#curPage").val(lastPage);
		$("#condition").submit();
	}
	function next() {
		var nextPage = parseInt($("#curPage").val())+1;
		var totalPageCount = $("#totalPageCount").val();
		if (nextPage > totalPageCount)
			return;
		$("#curPage").val(nextPage);
		$("#condition").submit();
	}
	function deleteFile(id) {
		var ret = window.confirm('是否确定删除？');
		if (!ret)
			return false;
		$.ajax({
			url : "${basePath}/user/resourceInfoDelete/" + id,
			type : "POST",
			success : function(result) {
				alert(result.msg);
				window.location.reload();
			},
			error : function(result) {
				alert("删除失败！");
			},
			processData : false,
			contentType : false,
		});
	}
	function downloadFile(id) {
		window.open("${basePath}/user/downloadResource/" + id);
		window.location.reload();
	}
</script>
</html>

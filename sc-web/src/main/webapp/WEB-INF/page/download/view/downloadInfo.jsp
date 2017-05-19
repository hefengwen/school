<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>资源中心</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left.jsp"%>
        <div class="content">
          <div class="condition">
            <form id="condition" role="form" action="<c:url value="/download/downloadInfo"/>" method="post">
              <div class="form-inline">
                <label for="name">专业名称：</label> 
                <select id="major" name="majorId" class="form-control">
                  <option value="">全部</option>
                  <c:forEach var="m" items="${majors}">
                    <option value="${m.majorId }">${m.name }</option>
                  </c:forEach>
                </select> 
                <label for="name">资源类型：</label> 
                <select id="dictId" name="type" class="form-control">
                  <option value="">全部</option>
                  <c:forEach var="r" items="${resourceTypes}">
                    <option value="${r.dictId }">${r.name }</option>
                  </c:forEach>
                </select> 
                <label for="name">资源名称：</label> 
                <input type="text" class="form-control" id="name" name="name" value="${domain.name }" /> 
                <input type="hidden" id="majorId" value="${domain.majorId }" />
                <input type="hidden" id="type" value="${domain.type }" />  
                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${domain.orderByColumn }" />  
                <input type="hidden" id="orderType" name="orderType" value="${domain.orderType }" />  
                <input type="hidden" id="curPage" name="curPage" value="${result.curPage }" /> 
                <input type="hidden" id="totalPageCount" name="totalPageCount" value="${result.totalPageCount }" />
                <button type="submit" class="btn btn-success">查询</button>
              </div>
            </form>
          </div>
          <br/>
          <div class="condition">
            <div class="form-inline">
                <a href="javascript:changeResourceType('');"><span id="typeAll" class="menu type"></span></a>
                <span style="float: right">
                  <label for="name">排序方式：</label> 
                   <select id="orderByColumnName" class="form-control" >
                      <c:forEach var="o" items="${orderByColumns}">
                        <option value="${o.name }">${o.detail }</option>
                      </c:forEach>
                    </select> 
                  <a href="javascript:changeOrderType('${domain.orderType }');">
                      <img src="<c:url value="/image/study/${domain.orderType }.png"/>" />
                  </a>
                </span>
            </div>
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
                    <td style="text-align: left;width: 75%;">
                      <img src="<c:url value="/image/filetype/${fn:toLowerCase(resource.suffix) }.png"/>" /> 
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
                      href="javascript:saveFile(${resource.resourceId });" />收藏</a></td>
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
		$("#downloadCenter").addClass("active");
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
		
		var majorId = $("#majorId").val();
		if (majorId)
			$("#" + majorId).addClass("onSelect");
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			if (majorId)
				$("#" + majorId).addClass("onSelect");
		});
		
		var majorId = $("#majorId").val();
		$("#major").val(majorId);
		
		var type = $("#type").val();
		$("#dictId").val(type);
		if(type==''){
			$("#typeAll").addClass('currentType');
		}else{
			$("#type"+type).addClass('currentType');
		}
		
		var orderByColumn = $("#orderByColumn").val();
		$("#orderByColumnName").val(orderByColumn);
		
		$("#orderByColumnName").change(function(){
			$("#orderByColumn").val($(this).val());
			$("#condition").submit();
		});
	});
	function prev() {
		var prevPage = parseInt($("#curPage").val()) - 1;
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
		if (curPage == lastPage)
			return;
		$("#curPage").val(lastPage);
		$("#condition").submit();
	}
	function next() {
		var nextPage = parseInt($("#curPage").val()) + 1;
		var totalPageCount = $("#totalPageCount").val();
		//alert(nextPage);
		if (nextPage > totalPageCount)
			return;
		$("#curPage").val(nextPage);
		$("#condition").submit();
	}
	function changeResourceType(type) {
		$("#dictId").val(type);
		$("#condition").submit();
	}
	function saveFile(id) {
		$.ajax({
			url : "${basePath}/user/saveResource/" + id,
			type : "POST",
			success : function(result) {
				alert(result.msg);
				window.location.reload();
			},
			error : function(result) {
				alert("收藏失败！");
			},
			processData : false,
			contentType : false,
		});
	}
	function downloadFile(id) {
		$.ajax({
			url : "${basePath}/user/queryDownloadResource/" + id,
			type : "POST",
			success : function(result) {
				if (result.status != "000000")
					alert(result.msg);
				else {
					window.open("${basePath}/user/" + result.data + "/" + id);
					window.location.reload();
				}
			},
			error : function(result) {
				alert("下载失败！");
			},
			processData : false,
			contentType : false,
		});
	}
	function changeOrderType(type) {
		if(type=='asc')
			$("#orderType").val('desc');
		else
			$("#orderType").val('asc');
		$("#condition").submit();
	}
	function queryDownload(majorId) {
		$("#major").val(majorId);
		$("#condition").submit();
	}
</script>
</html>

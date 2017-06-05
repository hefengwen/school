<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>问答中心</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <div>
          <div class="condition">
            <form id="condition" role="form" action="<c:url value="/qa/questionInfo"/>" method="post">
              <div class="form-inline">
                <label for="name">资源名称：</label> 
                <input type="text" class="form-control" id="title" name="title" value="${domain.title }" /> 
                <input type="hidden" id="curPage" name="curPage" value="${result.curPage }" /> 
                <input type="hidden" id="totalPageCount" name="totalPageCount" value="${result.totalPageCount }" />
                <button type="submit" class="btn btn-success">查询</button>
                <c:choose>
                    <c:when test="${empty currentUser }">
                    </c:when>
                    <c:otherwise>
                        <div style="float: right">
                          <a href="<c:url value="/qa/questionInfoAddView"/>">
                          <img src="<c:url value="/image/adduser.png"/>" /><b> 我要提问</b></a>
                        </div>
                    </c:otherwise>
                </c:choose>
              </div>
            </form>
          </div>
          <br/>
          <div class="info">
            <table class="table table-striped table-hover" style="text-align: center;">
              <tbody>
                <c:forEach var="question" items="${result.questionList }" varStatus="status">
                  <tr>
                    <td style="text-align: left;">
                      <span><strong>&nbsp;&nbsp;<a href="${basePath }/qa/questionDetailInfo/${question.quesId}" target="_blank">${question.title }</a></strong><br /> 
                      <small><span style="">${question.content }</span>&nbsp;&nbsp;</small><br /> 
                      <small>所属课程：<span style="">${question.course.name }</span>&nbsp;&nbsp;</small>
                      <small>提问者：<span style="">${question.quser.name }</span>&nbsp;&nbsp;</small>
                      <small>提问时间：<span style=""><fmt:formatDate value="${question.createTime }" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;</small> 
                      
                    </span></td>
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
		$("#qaCenter").addClass("active");
		
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
</script>
</html>

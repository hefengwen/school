<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>订阅列表</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left2.jsp"%>
        <div class="content">
          <div class="condition">
            <form id="condition" role="form" action="<c:url value="/user/courseSubscribe"/>"
              method="post">
              <div class="form-inline" style="margin: 10px;">
                <input type="hidden" id="curPage" name="curPage" value="${result.curPage }" /> <input
                  type="hidden" id="totalPageCount" name="totalPageCount"
                  value="${result.totalPageCount }" />
                <!-- <button type="submit" class="btn btn-success">查询</button> -->
              </div>
            </form>
          </div>
          <div class="info">
            <table class="table table-striped table-hover" style="text-align: center;">
              <thead>
                <tr>
                  <th style="text-align: center;">序号</th>
                  <th style="text-align: center;">课程名称</th>
                  <th style="text-align: center;">教材名称</th>
                  <th style="text-align: center;">创建时间</th>
                  <th style="text-align: center;">操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="save" items="${result.saveList }" varStatus="status">
                  <tr>
                    <td>${(result.curPage-1)*result.pageCount+(status.index+1) }</td>
                    <td><a href="<c:url value="/study/courseInfo/${save.course.courseId }"/>"  target="_blank">${save.course.name }</a></td>
                    <td><a href="<c:url value="/user/courseBookView/${save.book.bookId }"/>"
                      target="_blank"> ${fn:split(save.book.name,".")[0] }</a></td>
                    <td><fmt:formatDate value="${save.course.createTime }"
                        pattern="yyyy-MM-dd HH:mm" /></td>
                    <td><a href="javascript:deleteCourseSubscribe(${save.saveId });" />取消订阅</a></td>
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
<script type="text/javascript">
	$(function() {
		$("#userCenter").addClass("active");
		$("#courseSubscribe").addClass("onSelect");
		$("#currentMenu").html($(".onSelect").html());
		$("#currentPage").html($("title").html());
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			$("#courseSubscribe").addClass("onSelect");
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
		if (curPage >= lastPage)
			return;
		$("#curPage").val(lastPage);
		$("#condition").submit();
	}
	function next() {
		var nextPage = parseInt($("#curPage").val()) + 1;
		var totalPageCount = $("#totalPageCount").val();
		if (nextPage > totalPageCount)
			return;
		$("#curPage").val(nextPage);
		$("#condition").submit();
	}
	function deleteCourseSubscribe(id) {
		var ret = window.confirm('是否确定删除？');
		if (!ret)
			return false;
		$.ajax({
			url : "${basePath}/user/saveInfoDelete/" + id,
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
</script>
</html>

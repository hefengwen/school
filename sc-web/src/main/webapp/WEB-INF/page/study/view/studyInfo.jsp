<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>教学中心</title>
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
            <form id="condition" role="form" action="<c:url value="/study/studyInfo"/>"
              method="post">
              <div class="form-inline">
                <label for="name">专业名称：</label> <select id="major" name="majorId"
                  class="form-control">
                  <option value="">全部</option>
                  <c:forEach var="m" items="${majors}">
                    <option value="${m.majorId }">${m.name }</option>
                  </c:forEach>
                </select> <label for="type">课程类型：</label> <select id="dictId" name="type"
                  class="form-control">
                  <option value="">全部</option>
                  <c:forEach var="t" items="${courseTypes}">
                    <option value="${t.dictId }">${t.name }</option>
                  </c:forEach>
                </select> <label for="name">课程名称：</label> <input type="text" class="form-control" id="name"
                  name="name" value="${domain.name }" /> <input type="hidden" id="majorId"
                  value="${domain.majorId }" /> <input type="hidden" id="type"
                  value="${domain.type }" /> <input type="hidden" id="orderByColumn"
                  name="orderByColumn" value="${domain.orderByColumn }" /> <input type="hidden"
                  id="orderType" name="orderType" value="${domain.orderType }" /> <input
                  type="hidden" id="curPage" name="curPage" value="${result.curPage }" /> <input
                  type="hidden" id="totalPageCount" name="totalPageCount"
                  value="${result.totalPageCount }" />
                <button type="submit" class="btn btn-success">查询</button>
              </div>
            </form>
          </div>
          <br />
          <div class="condition">
            <div class="form-inline">
              <a href="javascript:changeCourseType('');"><span id="typeAll" class="menu type"></span></a>
              
              <span style="float: right"> 
                <label for="name">排序方式：</label> 
                    <select id="orderByColumnName" class="form-control">
                      <c:forEach var="o" items="${orderByColumns}">
                        <option value="${o.name }">${o.detail }</option>
                      </c:forEach>
                    </select> 
                <a href="javascript:changeOrderType('${domain.orderType }');"> <img
                  src="<c:url value="/image/study/${domain.orderType }.png"/>" />
              </a>
              </span>
            </div>
          </div>
          <div class="info">
            <table class="table course" style="text-align: center; border: 0px;">
              <tbody>
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <c:forEach var="course" items="${result.courseList }" varStatus="status">
                    <td><a href="<c:url value="/study/courseInfo/${course.courseId }"/>"
                      target="_blank"> <img id="picturePre" width="120" height="170"
                        src="<c:url value="/user/filePreView/${course.courseId }"/>" /><br /> <span>${course.name }</span>
                    </a></td>
                    <c:if test="${(status.index+1)%4==0 }">
                </tr>
                <tr>
                  </c:if>
                  </c:forEach>
                </tr>
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
<style type="text/css">
}
</style>
<script type="text/javascript">
	$(function() {
		$("#studyCenter").addClass("active");
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
		if (type == '') {
			$("#typeAll").addClass('currentType');
		} else {
			$("#type" + type).addClass('currentType');
		}

		var orderByColumn = $("#orderByColumn").val();
		$("#orderByColumnName").val(orderByColumn);

		$("#orderByColumnName").change(function() {
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
	function changeCourseType(type) {
		$("#dictId").val(type);
		$("#condition").submit();
	}
	function changeOrderType(type) {
		if (type == 'asc')
			$("#orderType").val('desc');
		else
			$("#orderType").val('asc');
		$("#condition").submit();
	}
	function queryCourse(majorId) {
		$("#major").val(majorId);
		$("#condition").submit();
	}
</script>
</html>

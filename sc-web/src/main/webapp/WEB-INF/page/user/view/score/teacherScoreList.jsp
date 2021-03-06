<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>用户积分列表</title>
<%@include file="../../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
          <div class="condition">
            <form id="condition" role="form" action="<c:url value="/user/teacherScoreList"/>" method="post">
              <div class="form-inline" style="margin:10px;">
                <label for="name">用户姓名：</label>
                <input type="text" class="form-control" id="name" name="name" value="${domain.name }"/>
                <input type="hidden" id="curPage" name="curPage" value="${result.curPage }"/>
                <input type="hidden" id="totalPageCount" name="totalPageCount" value="${result.totalPageCount }" />
                <button type="submit" class="btn btn-success">查询</button>
              </div>
            </form>
          </div>
          <div class="info">
            <table class="table table-striped table-hover" style="text-align: center;">
              <thead>
                <tr>
                  <th style="text-align: center;">名次</th>
                  <th style="text-align: center;">教师</th>
                  <th style="text-align: center;">积分</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="teacher" items="${result.userList }" varStatus="status">
                    <tr>
                      <td style="width:20%;"><span>${(result.curPage-1)*result.pageCount+(status.index+1) }</span></td>
                      <td><span>${teacher.name } </span></td>
                      <td style="width:20%;"><span>${teacher.score }</span></td>
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
    <%@include file="../../../common/foot.jsp"%>
  </div>
</body>
<%@include file="../../../common/tail.jsp"%>
<script type="text/javascript">
  $(function() {
    $("#index").addClass("active");
    $("#noticeCourseInfo").addClass("onSelect");
    $("#currentMenu").html($(".onSelect").html());
    $("#currentPage").html($("title").html());
    $(".menu").hover(function() {
      $(this).addClass("onSelect");
    }, function() {
      $(this).removeClass("onSelect");
      $("#noticeCourseInfo").addClass("onSelect");
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
</script>
</html>

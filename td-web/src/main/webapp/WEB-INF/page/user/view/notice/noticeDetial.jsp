<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>公告详情</title>
<%@include file="../../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <div class="notice">
            <div class="noticeTitle">
              <b>${result.title }</b>
            </div>
            <div class="noticeContent">
              ${result.content }
            </div>
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
  });
</script>
</html>

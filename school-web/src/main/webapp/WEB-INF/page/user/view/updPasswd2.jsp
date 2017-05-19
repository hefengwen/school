<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>密码修改</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">

      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left2.jsp"%>
        <div class="content">
          <div class="col-md-4 column"></div>
          <div class="col-md-4 column">
            <form class="form-horizontal" role="form" action="<c:url value="/user/updPasswd"/>"
              method="post">
              <div class="form-group">
                <label style="color: red;">${msg }</label>
              </div>
              <div class="form-group">
                <label for="oldPasswd" class="control-label">旧密码：</label><br /> <br />
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="oldPasswd" name="oldPasswd"
                    autofocus />
                </div>
              </div>
              <div class="form-group">
                <label for="newPasswd1" class="control-label">新密码：</label><br /> <br />
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="newPasswd1" name="newPasswd1" />
                </div>
              </div>
              <div class="form-group">
                <label for="newPasswd2" class="control-label">新密码：</label><br /> <br />
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="newPasswd2" name="newPasswd2" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                  <button type="submit" class="btn btn-success">修改</button>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-4 column"></div>
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
		$("#updPasswd").addClass("onSelect");
		$("#currentMenu").html($(".onSelect").html());
		$("#currentPage").html($("title").html());
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			$("#updPasswd").addClass("onSelect");
		});
	});
</script>
</html>

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>登录</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix" style="height: 660px;">
      <div class="col-md-4 column"></div>
      <div class="col-md-4 column">
        <div style="height: 150px;"></div>
        <form role="form" id="userinfo" action="<c:url value="/user/login"/>" method="post">
          <fieldset>
            <legend>登录</legend>
            <div class="form-group">
              <input id="userId" name="userId" type="text" class="form-control" placeholder="用户名"
                autofocus value="${userId }" />
            </div>
            <div class="form-group">
              <input id="passwd" name="passwd" type="password" class="form-control" placeholder="密码" />
            </div>
            <div class="form-group" style="text-align: center;">
              <button id="login" type="button" class="btn btn-success">登录</button>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <button id="reset" type="button" class="btn btn-warn">重置</button>
            </div>
            <div class="registration" style="text-align: center;">
              <span id="msg" style="color: red;">${msg }</span>
            </div>
          </fieldset>
        </form>
      </div>
      <div class="col-md-4 column"></div>
    </div>
    <%@include file="../../common/foot.jsp"%>
  </div>
</body>
<%@include file="../../common/tail.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#userCenter").addClass("active");
		$("#login").click(function() {
			var $userId = $("#userId").val();
			var $passwd = $("#passwd").val();
			//alert($userId);
			if ($.trim($userId) == "") {
				$("#msg").text("用户名不能为空!");
				//return false;
			}
			$("#msg").text("");
			if ($.trim($passwd) == "") {
				$("#msg").text("密码不能为空!");
				//return false;
			}
			$("#msg").text("");
			$("#userinfo").submit();
		});
		$("html").bind("keydown", function(event) {
			if (event.keyCode == 13)
				$("#login").click();
		});
	});
</script>
</html>

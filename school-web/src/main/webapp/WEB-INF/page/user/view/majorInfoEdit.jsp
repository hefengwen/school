<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>修改专业</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">

      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left0.jsp"%>
        <div class="content">
          <div class="col-md-4 column"></div>
          <div class="col-md-4 column" style="margin: 50px 0;">
            <form class="form-horizontal" role="form" action="<c:url value="/user/majorInfoEdit"/>"
              method="post" onsubmit="return check();">
              <div class="form-group">
                <label for="name" class="control-label">专业名称：</label><span id="nameMsg"></span><br />
                <br />
                <div class="col-sm-10">
                  <input type="hidden" id="majorId" name="majorId" value="${result.majorId }" /> <input
                    type="text" class="form-control" id="name" name="name" autofocus
                    value="${result.name }" />
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
		$("#majorInfo").addClass("onSelect");
		$("#currentMenu").html($(".onSelect").html());
		$("#currentPage").html($("title").html());
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			$("#majorInfo").addClass("onSelect");
		});
		$("#name").blur(function() {
			var name = $("#name").val();
			if ($.trim(name) == "") {
				$("#nameMsg").html("专业名称不能为空").css("color", "red");
				$("#name").focus();
				return false;
			}
			$("#nameMsg").html("");
			return true;
		});
	});
	function check(){
		var name = $("#name").val();
		if ($.trim(name) == "") {
			$("#name").blur();
			return false;
		}
		return true;
	}
</script>
</html>

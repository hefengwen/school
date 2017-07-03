<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>新增教师</title>
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
          <div class="col-md-4 column" style="margin-top: 20px;">
            <form class="form-horizontal" role="form" action="<c:url value="/user/teacherInfoAdd"/>"
              method="post" onsubmit="return check();">
              <div class="form-group">
                <label for="userId" class="control-label">教师登录ID：</label><span id="userIdMsg"></span><br />
                <br />
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="userId" name="userId" autofocus />
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="control-label">教师姓名：</label><span id="nameMsg"></span><br /><br /> <br />
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="name" name="name" />
                </div>
              </div>
              <div class="form-group">
                <label for="phone" class="control-label">联系方式：</label><span id="phoneMsg"></span><br /><br /> <br />
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="phone" name="phone" />
                </div>
              </div>
              <div class="form-group">
                <label for="teacherLevel" class="control-label">教师职称：</label><br /> <br />
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="teacherLevel" name="teacherLevel" />
                </div>
              </div>
              <div class="form-group">
                <label for="teacherDetail" class="control-label">教师简介：</label><br /> <br />
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="teacherDetail" name="teacherDetail" />
                </div>
              </div>
              <div class="form-group">
                <label for="majorId" class="control-label">所属专业：</label><br /> <br />
                <div class="col-sm-10">
                  <select id="majorId" name="majorId" class="form-control">
                    <c:forEach var="m" items="${result}">
                      <option value="${m.majorId }">${m.name }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                  <button type="submit" class="btn btn-success">新增</button>
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
		$("#teacherInfo").addClass("onSelect");
		$("#currentMenu").html($(".onSelect").html());
		$("#currentPage").html($("title").html());
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			$("#teacherInfo").addClass("onSelect");
		});

		$("#userId").blur(function() {
			var userId = $("#userId").val();
			if ($.trim(userId) == "") {
				$("#userIdMsg").html("用户ID不能为空").css("color", "red");
				$("#userId").focus();
				return;
			}
			$("#userIdMsg").html("");
			$.ajax({
				url : "${basePath}/user/queryUserById/" + $.trim(userId),
				type : "POST",
				success : function(result) {
					if (result.status != "000000") {
						$("#userIdMsg").html("ID已存在").css("color", "red");
						$("#userId").focus();
					} else {
						$("#userIdMsg").html("用户ID可用").css("color", "green");
					}
				},
				error : function(result) {

				},
				processData : false,
				contentType : false
			})
		});
		$("#name").blur(function() {
			var name = $("#name").val();
			if ($.trim(name) == "") {
				$("#nameMsg").html("用户名称不能为空").css("color", "red");
				return false;
			}
			$("#nameMsg").html("");
			return true;
		});
		$("#phone").blur(function() {
			var phone = $("#phone").val();
			if ($.trim(phone) == "") {
				$("#phoneMsg").html("联系方式不能为空").css("color", "red");
				return false;
			}
			$("#phoneMsg").html("");
			return true;
		});
	});
	function check(){
		var userId = $("#userId").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		if ($.trim(userId) == ""||$.trim(name) == ""||$.trim(phone) == "") {
			$("#userId").blur();
			$("#name").blur();
			$("#phone").blur();
			return false;
		}
		return true;
	}
</script>
</html>

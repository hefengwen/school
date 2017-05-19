<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>修改公告</title>
<%@include file="../../../common/head.jsp"%>

<link href="<c:url value="/js/editor/themes/default/default.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/editor/kindeditor-all-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/editor/lang/zh_CN.js"/>"></script>

<script>
  var editor;
  KindEditor.ready(function(K) {
    editor = K.create('textarea[name="content"]', {
      allowFileManager : true
    });
  });
</script>
</head>
<body>
  <div class="container">
    <%@include file="../../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <%@include file="../../left0.jsp"%>
        <div class="content">
          <div style="padding:20px;">
            <form class="form-horizontal" role="form" action="<c:url value="/user/noticeSystemInfoEdit"/>"
              method="post" enctype="multipart/form-data"  onsubmit="return check();">
              <div class="form-group">
                <label for="title" class="control-label">公告标题：</label><span id="titleMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <input type="text" class="form-control" id="title" name="title" autofocus value="${result.title }"/>
                  <input type="hidden" class="form-control" id="noticeId" name="noticeId" autofocus value="${result.noticeId }"/>
                </div>
              </div>
              <div class="form-group">
                <label for="content" class="control-label">公告内容：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea name="content" style="width: 100%; height: 600px; visibility: hidden;">${result.content }</textarea>
                  <br /> <br />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-5">
                  <button type="submit" class="btn btn-success">修改</button>
                </div>
              </div>
            </form>
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
    $("#userCenter").addClass("active");
    $("#noticeSystemInfo").addClass("onSelect");
    $("#currentMenu").html($(".onSelect").html());
    $("#currentPage").html($("title").html());
    $(".menu").hover(function() {
      $(this).addClass("onSelect");
    }, function() {
      $(this).removeClass("onSelect");
      $("#noticeSystemInfo").addClass("onSelect");
    });
    $("#title").blur(function() {
      var title = $("#title").val();
      if ($.trim(title) == "") {
        $("#titleMsg").html("公告标题不能为空").css("color", "red");
        $("#title").focus();
        return false;
      }
      $("#titleMsg").html("");
      return true;
    });
  });
  function check(){
    var title = $("#title").val();
    if ($.trim(title) == "") {
      $("#title").blur();
      return false;
    }
    return true;
  }
</script>

</html>

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>上传教材</title>
<%@include file="../../common/head.jsp"%>
<script type="text/javascript">
  var totalFileLength, totalUploaded, fileCount, filesUploaded;

  function debug(s) {
    var debug = $("#debug");
    if (debug)
      debug.html(debug.html() + "<br />" + s);
  }

  function onUploadComplete(e) {
    totalUploaded += document.getElementById("files").files[filesUploaded].size;
    filesUploaded++;
    if (filesUploaded < fileCount) {
      uploadNext();
    } else {
      $("#bar").width("99%");
      $("#bar").html("99% complete");
    }
  }

  function onFileSelect(e) {
    //alert("start onFileSelect ...");
    var files = e.target.files;//FileList object
    var output = [];
    fileCount = files.length;
    totalFileLength = 0;
    $("#debug").html("");
    $("#bar").width("0%");
    $("#bar").html("");
    for (var i = 0; i < fileCount; i++) {
      var file = files[i];
      //判断文件类型
      var extStart = file.name.lastIndexOf(".");
      var ext = file.name.substring(extStart, file.name.length).toUpperCase();
      if(ext != ".ZIP"){
    	  alert("文件类型限于zip格式！");
    	  $(this).val("");
    	  return false;
      }
      
      output.push(file.name, " (", file.size, " bytes, ",
          file.lastModifiedDate.toLocaleDateString(), ")");
      output.push("<br />");
      debug("add " + file.size);
      totalFileLength += file.size;
    }
    $("#selectedFiles").html(output.join(""));
    debug("totalFileLength:" + totalFileLength);
  }

  function onUploadProgress(e) {
    if (e.lengthComputable) {
      var percentComplete = parseInt((e.loaded + totalUploaded) * 100
          / totalFileLength);
      if (percentComplete > 99)
        percentComplete = 99;
      $("#bar").width(percentComplete + "%");
      $("#bar").html(percentComplete + " % complete");
    } else {
      debug("unable to complete");
    }
  }

  function onUploadFailed(e) {
    alert("Error uploading file");
    $("#bar").width("0%");
  }

  function onUploadSuccess(e) {
    alert("Finished uploading file(s)");
  }

  function uploadNext() {
    var formData = new FormData();
    //var file = document.getElementById("files").files[filesUploaded];
    //var file = $("#files").files[filesUploaded];有问题，待查
    var file = $('#files').get(0).files[filesUploaded];
    if (!file) {
      return;
    }
    formData.append("bookFile", file);
    formData.append("courseId", $("#courseId").val());
    //alert($("#categoreId").val());
    $.ajax({
      url : "${basePath}/user/courseBookAdd",
      type : "POST",
      xhr : function() { // custom xhr
        myXhr = $.ajaxSettings.xhr();
        if (myXhr.upload) { // check if upload property exists
          myXhr.upload.addEventListener('progress', onUploadProgress,
              false); // for handling the progress of the upload
        }
        myXhr.addEventListener('load', onUploadComplete, false);
        myXhr.addEventListener('error', onUploadFailed, false);
        return myXhr;
      },
      success : function(result) {
        //alert(data);
        if (result.status != '000000') {
          //onUploadFailed();
          alert(result.msg);
        } else {
          debug("complete " + filesUploaded + " of " + fileCount);
          debug("totalUploaded: " + totalUploaded);
          if (filesUploaded == fileCount) {
            $("#bar").width("100%");
            $("#bar").html("100% complete");
          }
          alert("上传成功！");
        }
      },
      data : formData,
      processData : false,
      contentType : false,
    });
  }

  function startUpload() {
    //alert("start startUpload ...");
    totalUploaded = filesUploaded = 0;
    uploadNext();
  }
</script>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left1.jsp"%>
        <div class="content">
          <div style="padding: 20px;">
            <div id="resourceCenterLink" style="height: 40px;">
              <div style="height: 100%; width: 100%">
                <img src="<c:url value="/image/resourceList.png"/>" /><a
                  href="<c:url value="/user/courseInfo"/>">课程列表</a>
              </div>
            </div>
            <div id="progressBar" style="height: 20px; border: 2px solid green">
              <div id="bar" style="height: 100%; background: #33dd33; width: 0%"></div>
            </div>
            <form id="fileForm">
              <br /> 
              <input type="file" id="files"/> <br /> 
              <input type="hidden" id="courseId" value="${courseId }"/>
              <output id="selectedFiles"></output>
              <input id="uploadButton" type="button" value="开始上传" />
            </form>
            <br />
            <div id="debug" style="height: 100%; border: 2px solid green; overflow: auto">
              <br />
              <br />
            </div>
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
    $("#courseInfo").addClass("onSelect");
    $("#currentMenu").html($(".onSelect").html());
	$("#currentPage").html($("title").html());
    $(".menu").hover(function() {
      $(this).addClass("onSelect");
    }, function() {
      $(this).removeClass("onSelect");
      $("#courseInfo").addClass("onSelect");
    });

    $("#files").change(onFileSelect);
    $("#uploadButton").click(startUpload);
  });
</script>
</html>

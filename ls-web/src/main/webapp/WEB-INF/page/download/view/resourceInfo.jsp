<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>资源详情</title>
<%@include file="../../common/head.jsp"%>
<link href="${basePath }/css/video-js-5.8.8.css" rel="stylesheet">
<script type="text/javascript"  src="${basePath }/js/flexpaper/js/flexpaper_flash.js"></script>
</head>
<body>
  <div style="width:70%;margin: auto;">
    <table style="margin-top:20px;">
      <input type="hidden" id="resourceId" value="${result.resourceId }"/>
      <tr><td colspan="2"><b style="font-size:24px;">${result.name}</b></td></tr>
      <tr><td>&nbsp;</td></tr>
      <tr><td><b>资源评分：${result.score}分</b></td><td style="border-left:2px solid #000;padding-left:5px;"><b>我的评分：${empty score.score?0:score.score}分</b></td></tr>
      <tr><td><div id="resourceStar" data-score="${result.score }"></div></td><td style="border-left:2px solid #000;padding-left:5px;"><div id="userStar" data-score="${empty score.score?0:score.score }"></div></td></tr>
    </table>
    <br/>
  <br/>
  </div>
  
  
  <div align="center">
    <c:choose>
      <c:when test="${fn:toUpperCase(result.suffix) == 'SWF' }">
          <c:if test="${empty useAddress }">
            <embed class="flash" src="${basePath }/download/resourcePreView/${result.resourceId}" quality="high" width="70%" height="760px;"  type="application/x-shockwave-flash"/>
          </c:if>
          <c:if test="${!empty useAddress }">
            <embed class="flash" src="${address }" quality="high" width="70%" height="760px;" type="application/x-shockwave-flash"/>
          </c:if>
      </c:when>
      <c:when test="${fn:toUpperCase(result.suffix) == 'PDF' }">
        <a id="viewerPlaceHolder" style="width: 70%; height: 960px; display: block"></a>
             <script type="text/javascript"> 
            	var fp = new FlexPaperViewer( 
            		'${basePath}/js/flexpaper/FlexPaperViewer',
                 'viewerPlaceHolder', { config : {
                 SwfFile : '${address }',
                 Scale : 0.6, 
                 ZoomTransition : 'easeOut',
                 ZoomTime : 0.5,
                 ZoomInterval : 0.2,
                 FitPageOnLoad : true,
                 FitWidthOnLoad : false,
                 FullScreenAsMaxWindow : false,
                 ProgressiveLoading : false,
                 MinZoomSize : 0.2,
                 MaxZoomSize : 5,
                 SearchMatchAll : false,
                 InitViewMode : 'SinglePage',
                 
                 ViewModeToolsVisible : true,
                 ZoomToolsVisible : true,
                 NavToolsVisible : true,
                 CursorToolsVisible : true,
                 SearchToolsVisible : true,
                  
                   localeChain: 'en_US'
                 }});
              </script>
      </c:when>
      <c:otherwise>
        <c:if test="${empty useAddress }">
          <embed class="flash" src="${basePath }/player/flvplayer.swf?file=${basePath }/${resourcePath}" autostart="true" quality="high" width="70%" height="760px;"type="application/x-shockwave-flash"/>
        </c:if>
        <c:if test="${!empty useAddress }">
        <%--<embed class="flash" src="${basePath }/player/flvplayer.swf?file=${address }" autostart="true" quality="high" width="70%" height="760px;"type="application/x-shockwave-flash"/> --%>
          <div id="a1"></div>
            <script type="text/javascript" src="${basePath }/ckplayer6.8/ckplayer/ckplayer.js" charset="utf-8"></script>
            <script type="text/javascript">
              var flashvars={
                f:'${address }',
                c:0,
                b:1,
                //i:'http://www.ckplayer.com/static/images/cqdw.jpg'
                l:''
                };
              var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
              CKobject.embedSWF('${basePath }/ckplayer6.8/ckplayer/ckplayer.swf','a1','ckplayer_a1','70%','760',flashvars,params); 
              function closelights(){//关灯
                //alert(' 本演示不支持开关灯');
              }
              function openlights(){//开灯
                //alert(' 本演示不支持开关灯');
              }
            </script>
        </c:if>
      </c:otherwise>
    </c:choose>
    <%-- <c:if test="${fn:toUpperCase(result.suffix) == 'PDF' }">
      <c:if test="${empty useAddress }">
        <embed class="flash" src="${basePath }/download/resourcePreView/${result.resourceId}" quality="high" width="70%" height="760px;" type="application/pdf"/>
      </c:if>
      <c:if test="${!empty useAddress }">
        <embed class="flash" src="${address }" quality="high" width="70%" height="760px;" type="application/pdf"/>
      </c:if>
    </c:if>
    <c:if test="${fn:toUpperCase(result.suffix) == 'SWF' }">
      <c:if test="${empty useAddress }">
        <embed class="flash" src="${basePath }/download/resourcePreView/${result.resourceId}" quality="high" width="70%" height="760px;"  type="application/x-shockwave-flash"/>
      </c:if>
      <c:if test="${!empty useAddress }">
        <embed class="flash" src="${address }" quality="high" width="70%" height="760px;" type="application/x-shockwave-flash"/>
      </c:if>
    </c:if>
    <c:if test="${fn:toUpperCase(result.suffix) == 'FLV' }">
      <c:if test="${empty useAddress }">
        <embed class="flash" src="${basePath }/player/flvplayer.swf?file=${basePath }/${resourcePath}" quality="high" width="70%" height="760px;"type="application/x-shockwave-flash"/>
      </c:if>
      <c:if test="${!empty useAddress }">
        <embed class="flash" src="${basePath }/player/flvplayer.swf?file=${address }" quality="high" width="70%" height="760px;"type="application/x-shockwave-flash"/>
      </c:if>
    </c:if>
    <c:if test="${fn:toUpperCase(result.suffix) == 'AVI' }">
      <c:if test="${empty useAddress }">
        <embed class="flash" src="${basePath }/player/flvplayer.swf?file=${basePath }/${resourcePath}" quality="high" width="70%" height="760px;"type="application/x-shockwave-flash"/>
      </c:if>
      <c:if test="${!empty useAddress }">
        <embed class="flash" src="${basePath }/player/flvplayer.swf?file=${address }" quality="high" width="70%" height="760px;"type="application/x-shockwave-flash"/>
      </c:if>
    </c:if>
    <c:if test="${fn:toUpperCase(result.suffix) == 'MP4' }">
      <c:if test="${empty useAddress }">
        <video id="my-video" class="video-js flash" controls preload="auto" width="70%" height="760px;" data-setup="{}" autoplay="autoplay">
          <source src="${basePath }/${resourcePath}" type='video/mp4' />
          <p class="vjs-no-js">
            您的浏览器不支持该视频，请使用最新版火狐浏览器重新打开！
          </p>
        </video>
      </c:if>
      <c:if test="${!empty useAddress }">
        <video id="my-video" class="video-js flash" controls preload="auto" width="70%" height="760px;" data-setup="{}" autoplay="autoplay">
          <source src="${address }" type='video/mp4' />
          <p class="vjs-no-js">
            您的浏览器不支持该视频，请使用最新版火狐浏览器重新打开！
          </p>
        </video>
      </c:if>
    </c:if> --%>
    <%-- <c:if test="${fn:toUpperCase(result.suffix) == 'AVI' }">
      <video class="flash" src="${basePath }/${resourcePath}" quality="high" width="80%" height="760px;" controls="controls">您的浏览器不支持此种视频格式。</video> 
    </c:if> --%>
  </div>
</body>
<%@include file="../../common/tail.jsp"%>
<script type="text/javascript" src="<c:url value="/js/star/jquery.raty.min.js"/>"></script>
<script type="text/javascript">
	$(function(){
		//alert($(document).height());
		$(".flash").attr("height",$(document).height());
		//评分详情
        $("#resourceStar").raty({
        	starOff:'${basePath}/image/star/star-off.png',
        	starOn:'${basePath}/image/star/star-on.png',
        	score:function(){
        		//alert($(this).attr("data-score"));
        		return $(this).attr("data-score");
        	},
        	readOnly:true
        });
        $("#userStar").raty({
        	starOff:'${basePath}/image/star/star-off.png',
        	starOn:'${basePath}/image/star/star-on.png',
        	score:function(){
        		//alert($(this).attr("data-score"));
        		return $(this).attr("data-score");
        	},
        	readOnly:function(){
        		return $(this).attr("data-score")!=0;
        	},
        	click:function(score,event){
        		var formData = new FormData();
        		formData.append("score", score);
        		formData.append("refId", $("#resourceId").val());
        		//alert($("#courseId").val());
        		$.ajax({
        			url : "${basePath}/user/scoreResource",
        			type : "POST",
        			success : function(result) {
        				alert(result.msg);
        				window.location.reload();
        			},
        			error : function(result) {
        				alert("评价失败！");
        			},
        			data : formData,
        			processData : false,
        			contentType : false,
        		});
        	}
        });
	});
</script>
<script src="${basePath }/js/videojs-ie8.min.js"></script>
</html>

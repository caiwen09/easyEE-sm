$(function() {
	/**
	 * EasyUI初始化
	 */
	$("#username").textbox('textbox').focus();

	/**
	 * 验证码打开实现
	 */
	var vcTr =$("#vcTr");
	var vcImg=$("#vcImg");
	
	$("#captcha").on("click", function() {
		vcTr.show();
		return false;
	}).on("focus", function() {
		vcTr.show();
		return false;
	}).on("keydown", function(event) {
			if(event.which==13){
				$("#loginBtn").click();
				vcTr.show();
			}
	});

	$("#vcImg").hover(function() {
		vcTr.show();
	});

	$(document).click(function() {
		vcTr.hide();
	})
	
	$("#vcTr").on("click", function(event) {
		vcImg.attr("src",EasyEE.basePath + "jsp/VerifyCode.jsp?r=" + new Date());
		//阻止冒泡
		event.stopPropagation();
	});
	
	/*
	 * 登录检测和提交
	 */
	$("#loginBtn").on("click",function(){
		if(uiEx.validate("#loginForm")){
			var vc=$("#captcha");
			// 是否开启验证码
			if(vc.size() != 0){
				//相当于ajax请求jQuery.post(url,data,success(data, textStatus, jqXHR),dataType)
				$.post("checkCaptcha","captcha="+vc.val(),function(data){
					if(data.statusCode==200){
						$('#loginBtn').linkbutton('disable');
						uiEx.submitForm('#loginForm');
					}else{
						uiEx.msg(data.msg);
						vcImg.attr("src",EasyEE.basePath + "jsp/VerifyCode.jsp?r=" + new Date());
						vc.select();
						vc.focus();
					}
				});
			}else{
				$('#loginBtn').linkbutton('disable');
				uiEx.submitForm('#loginForm');
			}
		}
	});
	
	
	$(":input.textbox-text").keydown(function(event){
		if(event.keyCode==13){
			var nxtIdx = $(":input.textbox-text,A.easyui-linkbutton").index(this);
	  		 $(":input.textbox-text,A.easyui-linkbutton").eq(nxtIdx+1).focus();
		}
	});

	/**
	 * easyui主题切换下拉菜单
	 */
	$("#themeCombobox")
			.combobox(
					{
						editable : false,
						panelHeight : "auto",
						valueField : "value",
						textField : 'text',
						url : EasyEE.basePath+ "static/easyee/json/easyui.theme.combobox.json",

						onSelect : function(selObj) {
							if(document.getElementById("themeLink")){
								document.getElementById("themeLink").href = EasyEE.basePath + "static/easyui/themes/" + selObj.value + "/easyui.css";
							}
                            //缓存天数365
                            $.cookie('ui_theme', selObj.value, { expires: 365 });
						},
						// 加载成功后设置默认值
						onLoadSuccess : function() {
							var defaultTheme = "metro-blue";
							
							var theme=$.cookie('ui_theme');
							if(theme){
								defaultTheme = theme;
							}
							
							 $("#themeCombobox").combobox("setValue", defaultTheme);
							 document.getElementById("themeLink").href=EasyEE.basePath+"static/easyui/themes/"+defaultTheme+"/easyui.css";
						}
					});
	

})
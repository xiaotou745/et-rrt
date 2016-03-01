$(function() {
	//进入该页面发送异步请求验证是否关注 是否绑定
	validateQualification(openid);
    var codeTime = 0;
    // 获取验证码
    $('.bg2').delegate('.getCode','click', function(){
        var tel      = $('.bg2 .ipt-phone');
        if($(this).hasClass('disabled')){
            return;
        }
        if(!tel.val()){
            alert('请填写手机号码');
            return;
        }
        if(!/^1\d{10}$/.test(tel.val())){
            alert('手机号码格式不正确');
            return;
        }
        $.ajax({
            url     :sendcodeurl,
            method  :'post',
            data    :{
            	phoneNo:tel.val(),
            	sType:5
            },
            success:function(a,b){
                codeTime = 60;
                startCode();
            }
        })
    });
    function startCode(){
        if(codeTime > 0){
            $('.bg2 .getCode').html(codeTime+'S').addClass('disabled')
            codeTime--;
            setTimeout(startCode, 1000)
            return;
        }
        $('.bg2 .getCode').html('发送验证码').removeClass('disabled')

    }
    // 领取红包
    $('.bg2').delegate('.gethb', 'click', function(){
        var code = $('.bg2 .ipt-code').val();
        var tel      = $('.bg2 .ipt-phone');
        if(!tel.val()){
            alert('请填写手机号');
            return;
        }
        if(!/^\d{6}$/.test(code)){
            alert('验证码是六位数字!');
            return;
        }
        $.ajax({
            url     :fetchredbagurl,
            method  :'post',
            data    :{
                phoneNo: tel.val(),
                code : code
            },
            success:function(res){
            	var fetchurl=basepath+"/clienter/fetchredbag";
//            	if(res.code==1014){
//            		var registerurl=basepath+"/clienter/register";
//            		res.data='<div class="to_reg c3"><div class="title">领取失败</div>该手机号尚未注册人人推，请点击下方按钮前往APP注册后 <a href="'+fetchurl+'" class="rebind stress">重新绑定</a><a href="'+registerurl+'" class="sub-btn">马上注册</a></div>';
//            	}else if(res.code==1015){
//            		res.data='<div class="to_reg to_bind c4"><div class="title">领取失败</div>该手机号已被绑定，使用其他号码重新绑定<a href="'+fetchurl+'" class="sub-btn rebind">返回重新绑定</a></div>';
//            	}else if(res.code==1000){
//            		res.data='<div class="success c1"><p>恭喜您获得现金奖励</p><p class="money">¥ 2 元</p>奖励已放入人人推账号'+tel.val()+'<a href="#" class="sub-btn">前往查看</a></div>';
//            	}else{
//            		
//            	}
            	if(res.code==1011 || res.code==1012 || res.code==1013){
            		alert(res.msg);
            		return;
            	}else{
	                var wrap = $('.bg2');
	                codeTime = 0;
	                // if(res.code == 1){
	                    wrap.html(res.data);
	                // }else{
	                //     wrap.html($(".contents .c0").clone());
	                // }
            	}
            }
        })
    });
    $('.bg2').delegate('.rebind', 'click', function(){
            var wrap = $('.bg2');
            wrap.html($('.contents .c0').clone());
    })
});

function validateQualification(openid){
	$.ajax({
        url     :basepath+"/clienter/validateQualification",
        method  :'post',
        async:false,
        data    :{
        	openid:openid
        },
        success:function(res){
        	if(res.code!=1000){
	        	var wrap = $('.bg2'); 
	            wrap.html(res.data);
        	}
        }
    })
}
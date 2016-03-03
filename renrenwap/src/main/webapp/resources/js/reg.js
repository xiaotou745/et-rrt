$(function() {
    var codeTime = 0;

    // 获取验证码
    $('.getCode').on('click', function(){
        var tel      = $('.ele-phone input');
        if($(this).hasClass('disabled')){
            return
        }
        if(!tel.val()){
            showTip('请填写手机号码');
            return;
        }
        if(!/^1\d{10}$/.test(tel.val())){
            showTip('手机号码格式不正确');
            return;
        }
        $.ajax({
            url     :sendcodeurl,
            method  :'post',
            data    :{
            	phoneNo:tel.val(),
            	sType:1
            },
            success:function(a){
            	showTip(a.message);
            	if(a.responseCode !=200){
            		return;
            	}else{            		
	                codeTime = 60;
	                startCode();
            	}
            }
        })
    });
    function startCode(){
        if(codeTime > 0){
            $('.getCode').html(codeTime+'S').addClass('disabled')
            codeTime--;
            setTimeout(startCode, 1000)
            return;
        }
        $('.getCode').html('获取验证码').removeClass('disabled')

    }
    $('.submit').on('click', function(){
        var code = $('.ipt-code').val();
        var tel      = $('.ele-phone input');
        var rem      = $('.ipt-rem');
        var pwd1      = $('.ipt-pwd1').val();
        var pwd2      = $('.ipt-pwd2').val();
        if(!tel.val()){
            showTip('请填写手机号码');
            return;
        }
        if(!/^1\d{10}$/.test(tel.val())){
            showTip('手机号码格式不正确');
            return;
        }

        if(!code){
            showTip('请填写验证码');
            return;
        }
        if(!/^\d{6}$/.test(code)){
            showTip('验证码是六位数字!');
            return;
        }

        if(!pwd1){
            showTip('请填写密码');
            return;
        }
        if(!/^.{6,}$/.test(pwd1)){
            showTip('密码需大于6位!');
            return;
        }
        if(pwd1 !== pwd2){
            showTip('两次输入密码不一致!');
            return;
        }
        if(rem.val().length>0){
	        if(!/^1\d{10}$/.test(rem.val())){
	            showTip('推荐人手机号码格式不正确');
	            return;
	        }
        }

        $.ajax({
            url     :registerurl,
            method  :'post',
            data    :{
                phoneNo: tel.val(),
                verifyCode : code,
                passWord:pwd1,
                recommendPhone : rem.val(),
                operSystem:"wap"
            },
            success:function(res){
                var wrap = $('.bg2');
                codeTime = 0; 
                showTip(res.msg); 
                if(res.code==200){
                	window.location=basepath+"/clienter/registersuccess?phoneNo="+tel.val()
                }
            }
        })
    });
    var ele_tip = $('.tip');
    var tip_timer;
    function showTip(txt){
        if(tip_timer){
            clearTimeout(tip_timer);
        }
        ele_tip.html(txt).show();;
        tip_timer = setTimeout(function(){
            tip_timer = null;
            ele_tip.hide();
        }, 1500);
    }


    $('.agreement,.close').on('click', function(){
        $('body').toggleClass('showAgreement')
    })
});
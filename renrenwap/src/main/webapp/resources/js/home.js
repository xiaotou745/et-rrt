$(function() {
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
            alert('请填写验证码');
            return;
        }
        if(!/^\d{6}$/.test(code)){
            alert('验证码是六位数字!');
            return;
        }

        $.ajax({
            url     :'aj/submit.json',
            method  :'post',
            data    :{
                phone: tel.val(),
                code : code
            },
            success:function(res){
                var wrap = $('.bg2');
                codeTime = 0;
                // if(res.code == 1){
                    wrap.html(res.data);
                // }else{
                //     wrap.html($(".contents .c0").clone());
                // }
            }
        })
    });
    $('.bg2').delegate('.rebind', 'click', function(){
            var wrap = $('.bg2');
            wrap.html($('.contents .c0').clone());
    })
});
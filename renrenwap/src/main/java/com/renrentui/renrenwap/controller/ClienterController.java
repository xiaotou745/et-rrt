package com.renrentui.renrenwap.controller; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 





import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.common.ResultModel;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.SignUpReq;
 

@Controller
@RequestMapping("clienter")
public class ClienterController {
	
	@Autowired
	private IClienterService clienterService;
	
	/*
	 * 领红包页面获取验证码
	 * wangchao
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	public ResponseBase sendcode(CSendCodeReq req){ 
		ResponseBase responseBase= clienterService.sendcode(req);
		
		return responseBase; 
	}
	/*
	 * 领红包页面
	 * wangchao
	 */
	@RequestMapping("fetchredbag")
	public ModelAndView fetchredbag(){
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "clienter/fetchredbag");
		return view;
	}
	/*
	 * 注册页面
	 * wangchao
	 */
	@RequestMapping("register")
	public ModelAndView register(){
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "clienter/register");
		return view;
	}
	
	/*
	 * 注册用户
	 * wangchao
	 */
	@RequestMapping("registersubmit")
	@ResponseBody
	public ResultModel<Object> registersubmit(SignUpReq req){
		ResultModel<Object> resultModel = new ResultModel<Object>();
		resultModel=clienterService.signup(req);
		return resultModel;
	}
}

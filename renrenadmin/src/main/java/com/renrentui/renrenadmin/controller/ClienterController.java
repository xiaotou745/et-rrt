package com.renrentui.renrenadmin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenapi.service.inter.IClienterRelationService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapi.service.inter.ITaskShareStatisticsService;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.TaskShareStatistics;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.domain.ClienterRelationLevelModel;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.req.CRelationReq;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.ShareTaskReq;
import com.renrentui.renrenentity.resp.ClienterResp;

@Controller
@RequestMapping("clienter")
public class ClienterController {
	@Autowired
	private IClienterService clienterService;
	@Autowired
	private IClienterBalanceRecordService clienterBalanceRecordService;
	@Autowired
	private ITaskShareStatisticsService taskShareStatisticsService;
	@Autowired
	private IClienterRelationService clienterRelationService;
	/**
	* @Des 地推员管理 
	* @Author WangXuDan
	* @Date 2015年9月29日14:45:04
	* @Return
	*/
	@RequestMapping("list")
	public ModelAndView list(){	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "地推员管理");
		model.addObject("currenttitle", "地推员管理");
		model.addObject("viewPath", "clienter/list");
		return model;
	}
	/**
	* @Des  地推员信息列表
	* @Author WangXuDan
	* @Date 2015年9月29日16:09:13
	* @Return
	*/
	@RequestMapping("listdo")
	public ModelAndView listdo(ClienterReq req) {	
		PagedResponse<ClienterResp> resp =clienterService.queryClienterList(req);
		ModelAndView model = new ModelAndView("clienter/listdo");
		model.addObject("listData", resp);
		return model;
	}
	/**
	* @Des 修改用户状态 
	* @Author WangXuDan
	* @Date 2015年10月8日11:43:44
	* @Return
	*/
	@RequestMapping(value="editclienterstatus",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase editclienterstatus(ModifyClienterStatusReq req,HttpServletRequest request){
		ResponseBase response = new ResponseBase();
		req.setLastOptName(UserContext.getCurrentContext(request).getLoginName());
		response =clienterService.editClienterStatus(req);
		return response;
	}
	/**
	 * 地推员交易列表
	 * @return
	 */
	@RequestMapping("recordlist")
	public ModelAndView recordlist(String phoneNo,String name,Double blance,Double hadWithdraw,Long id){	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "地推员管理");
		model.addObject("currenttitle", "地推员交易列表");
		model.addObject("viewPath", "clienter/recordlist");
		model.addObject("phoneNo", phoneNo);
		model.addObject("name", name);
		model.addObject("blance", blance);
		model.addObject("hadWithdraw", hadWithdraw);
		model.addObject("id", id);
		return model;
	}
	/**
	 * 地推员交易列表分页
	 * @param req
	 * @return
	 */
	@RequestMapping("recordlistdo")
	public ModelAndView recordlistdo(ClienterBlanceRecordReq req){	
		ModelAndView model = new ModelAndView("clienter/recordlistdo");
		PagedResponse<ClienterBalanceRecord> list= clienterBalanceRecordService.getRecordList(req);
		model.addObject("listData", list);
		return model;
	}
	
	/**
	 * 地推员推荐关系
	 * @return
	 */
	@RequestMapping("clientercelation")
	public ModelAndView clientertelation(){	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "地推员管理");
		model.addObject("currenttitle", "地推员交易列表");
		model.addObject("viewPath", "clienter/clientercelation");
		return model;
	}
	/**
	 * 
	 * 推荐关系列表
	 * @param req
	 * @return
	 */
	@RequestMapping("rcelationdo")
	public ModelAndView rcelationdo(CRelationReq req){	
		ModelAndView model = new ModelAndView("clienter/rcelationdo");
		List<ClienterRelationModel> list=clienterRelationService.getClienterRelationModelsByPhone(req);
		model.addObject("listData", list);
		return model;
	}
	/**
	 * 地推员分享或下载一个任务
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("sharetask")
	public void recordlistdo(ShareTaskReq req,HttpServletResponse response) throws Exception{	
		if (req==null||
			req.getClienterId()<=0||
			req.getTaskId()<=0||
			req.getDownUrl()==null||
			req.getDownUrl().isEmpty()) {
			return;
		}
		TaskShareStatistics record=new TaskShareStatistics();
		record.setClienterid(req.getClienterId());
		record.setTaskid(req.getTaskId());
		taskShareStatisticsService.insert(record);
		response.sendRedirect(req.getDownUrl());
	}
	
	/**
	 * 查询地推员信息
	 * @param req
	 * @return
	 */
	@RequestMapping("rejibie")
	public ModelAndView rejibie(Long cid,Integer jibie){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "地推员管理");
		model.addObject("currenttitle", jibie+"级地推员列表");
		model.addObject("viewPath", "clienter/rejibie");
		Clienter clienter=clienterService.getClienterById(cid);
		model.addObject("clienter", clienter);
		model.addObject("JIEBIE", jibie);
		return model;
	}
	
	/**
	 * 查询地推员信息
	 * @param req
	 * @return
	 */
	@RequestMapping("rejibiedo")
	public ModelAndView rejibiedo(CRelationReq req){
		ModelAndView model = new ModelAndView("clienter/rejibiedo");
		List<ClienterRelationLevelModel> list=clienterRelationService.getClienterRelationModelsByJibie(req);
		model.addObject("listData", list);
		return model;
	}
}
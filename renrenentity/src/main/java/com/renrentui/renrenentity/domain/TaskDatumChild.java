package com.renrentui.renrenentity.domain;

public class TaskDatumChild {
	/**

	 * 

	 */

	private Long id;

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}



	/**

	 * TaskDatum 客户提交资料表ID

	 */

	private Long taskDatumId;

	public Long getTaskDatumId() {

		return taskDatumId;

	}

	public void setTaskDatumId(Long taskDatumId) {

		this.taskDatumId = taskDatumId;

	}



	/**

	 * 控件名称name

	 */

	private String controlName;

	public String getControlName() {

		return controlName;

	}

	public void setControlName(String controlName) {

		this.controlName = controlName;

	}



	/**

	 * 控件值value

	 */

	private String controlValue;

	public String getControlValue() {

		return controlValue;

	}

	public void setControlValue(String controlValue) {

		this.controlValue = controlValue;

	}



	/**

	 * 是否有效 1 有效 0无效

	 */

	private Integer isEnable;

	public Integer getIsEnable() {

		return isEnable;

	}

	public void setIsEnable(Integer isEnable) {

		this.isEnable = isEnable;

	}

}

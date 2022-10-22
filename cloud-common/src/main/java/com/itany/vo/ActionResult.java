package com.itany.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class ActionResult implements Serializable{
	
	private String msg;
	
	private Integer status;
	
	private Object data;
	
}

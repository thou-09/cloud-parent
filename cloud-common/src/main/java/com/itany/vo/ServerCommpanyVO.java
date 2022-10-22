package com.itany.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ServerCommpanyVO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class ServerCommpanyVO implements Serializable {

    private static final long serialVersionUID = 386303721433133250L;
    private Integer id;
    private String name;
    private String info;
    private String address;
    private String gps;
    private String scale;
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date joindate;
    private Integer flag;
    private Integer type;
    private String linkman;
    private String phone;
}

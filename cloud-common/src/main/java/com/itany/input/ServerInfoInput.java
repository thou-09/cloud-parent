package com.itany.input;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itany.constants.AppConsts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ServerInfoInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerInfoInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = -6075668995576267022L;

    private Integer id;

    private String servername;

    private String info;

    private String linkman;

    private String phone;

    private Integer userid;

    private Integer commpanyid;

    private Date createdate;

    private Integer flag;

    private BigDecimal price;
}

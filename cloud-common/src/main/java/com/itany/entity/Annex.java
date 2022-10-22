package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Annex.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_annex")
public class Annex implements Serializable {

    private static final long serialVersionUID = -4134021796774383669L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 上传路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String filename;
    /**
     * 类型，服务图片 1 服务商图片 2 服务商执照图片 3
     */
    private Integer type;
    /**
     * 关联外键
     */
    private Integer keyid;
    /**
     * 关联表的类型，审核 1 服务商 2 服务 3
     */
    private Integer tabletype;
}

package com.bm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hex
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bm_book")
@ApiModel(value="Book对象", description="")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "书名")
    private String name;

    @ApiModelProperty(value = "书籍编码")
    private String bookNumber;

    @ApiModelProperty(value = "出版社")
    private String publishingHouse;

    @ApiModelProperty(value = "类别")
    private String bookClass;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "书架号")
    private String bookshelfNumber;

    @ApiModelProperty(value = "库存")
    private Integer over;

    @ApiModelProperty(value = "书本状态0=有效 1=已借完 2=删除")
    private Integer status;

    @ApiModelProperty(value = "创建者")
    private String creater;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新者")
    private String updater;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;


}

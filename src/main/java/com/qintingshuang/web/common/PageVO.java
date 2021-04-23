package com.qintingshuang.web.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qintingshuang
 * @create 2021-04-23 13:39
 * @description 分页实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {


    @ApiModelProperty("页码")
    private Integer pageNum;


    @ApiModelProperty("页尺码")
    private Integer pageSize;


    @ApiModelProperty("总数")
    private Long total;


    @ApiModelProperty("总页")
    private Integer pages;

    public PageVO(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 返回的实体类
     */
    @ApiModelProperty("实体数据")
    private T data;
}

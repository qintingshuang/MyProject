package com.qintingshuang.web.utils;

import com.qintingshuang.web.common.PageVO;

import java.util.Collections;
import java.util.List;

/**
 * @author qintingshuang
 * @create 2021-04-23 11:27
 * @description 分页工具类
 **/
public class PageUtils {


    public static PageVO startPage(List list, Integer pageNum,
                                   Integer pageSize) {
        PageVO pageVO = new PageVO();
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount = 0;
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }
        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
        List pageList = getPagedList(list, fromIndex, toIndex);
        pageVO.setPageNum(pageNum);
        pageVO.setPageSize(pageSize);
        pageVO.setTotal(count.longValue());
        pageVO.setPages(pageCount);
        pageVO.setData(pageList);
        return pageVO;
    }


    public static <T> List<T> getPagedList(List list, Integer fromIndex, Integer toIndex) {

        if (fromIndex >= list.size()) {
            return Collections.EMPTY_LIST;
        }
        if (fromIndex < 0) {
            return Collections.EMPTY_LIST;
        }
        if (toIndex >= list.size()) {
            toIndex = list.size();
        }
        return list.subList(fromIndex, toIndex);
    }

}

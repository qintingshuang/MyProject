package com.qintingshuang.web.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qintingshuang.web.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

import static com.qintingshuang.web.enums.ErrorEnum.SYSTEM_EXCEPTION;

/**
 * @author qintingshuang
 * @create 2021-04-08 14:52
 * @description 统一响应
 **/
@Data

@AllArgsConstructor
@NoArgsConstructor
public class QinResponse<T> {

    private String code;

    private String description;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    public QinResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public static <T> QinResponse<T> success() {
        return new QinResponse<>("000000", "success");
    }

    /**
     * 响应--有业务数据的返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> QinResponse<T> success(T data) {
        return result("000000", "success", data);
    }


    /**
     * 响应结果
     *
     * @param code
     * @param description
     * @param data
     * @param <T>
     * @return
     */
    public static <T> QinResponse<T> result(String code, String description, T data) {
        return new QinResponse(code, description, data);
    }

    /**
     * 响应-系统错误
     *
     * @param <T>
     * @return
     */
    public static <T> QinResponse<T> error() {
        return error(SYSTEM_EXCEPTION);
    }

    /**
     * 响应 --业务错误
     *
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> QinResponse<T> error(ErrorEnum errorEnum) {
        return new QinResponse(errorEnum.getCode(), errorEnum.getDescription());
    }


    /**
     * 响应--补充错误描述
     *
     * @param supplement
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> QinResponse<T> error(String supplement, ErrorEnum errorEnum) {
        supplement = Objects.isNull(supplement) ? Strings.EMPTY : supplement;
        return new QinResponse(errorEnum.getCode(), errorEnum.getDescription() + ":" + supplement );
    }


}

package com.qintingshuang.web.exception;

import com.qintingshuang.web.common.QinResponse;
import com.qintingshuang.web.enums.ErrorEnum;
import javafx.scene.media.MediaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.qintingshuang.web.enums.ErrorEnum.CUSTOMIZE_EXCEPTION;
import static com.qintingshuang.web.enums.ErrorEnum.JSON_PARSE_EXCEPTION;

/**
 * @author qintingshuang
 * @create 2021-04-08 14:38
 * @description 异常监控
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    private Map<String, ErrorEnum> errorEnumMap = new ConcurrentHashMap<>(16);

    @PostConstruct
    public void init() {
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            errorEnumMap.put(errorEnum.getCode(), errorEnum);
        }
    }

    /**
     * 获取错误码
     *
     * @param code
     * @return
     */
    public ErrorEnum getErrorEnum(String code) {
        return errorEnumMap.get(code);
    }


    /**
     * 请求json 异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public QinResponse jsonParseException(HttpMessageNotReadableException e) {
        log.error("json解析异常信息: \n {}", e);
        return QinResponse.error(JSON_PARSE_EXCEPTION);
    }


    /**
     * @Valid 不符合的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public QinResponse paramValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());
        ErrorEnum errorEnum = CUSTOMIZE_EXCEPTION.setDescription(message);
        log.error("@Valid异常拦截:\n", e);
        return QinResponse.error(errorEnum);
    }

    /**
     * 全局异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public  QinResponse globalException(Exception e){
        log.error("全局异常拦截:\n",e);
        return  QinResponse.error();
    }

    /**
     * 业务异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BusinessException.class})
    public QinResponse businessException(BusinessException e){
        log.error("业务异常拦截：\n", e);
        return  QinResponse.error(e.getErrorEnum());
    }
}

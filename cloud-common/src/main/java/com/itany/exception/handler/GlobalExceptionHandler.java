package com.itany.exception.handler;

import com.itany.constants.AppConsts;
import com.itany.exception.AppException;
import com.itany.vo.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler.
 *
 * @author Thou
 * @date 2022/10/6
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        GenericConversionService genericConversionService = (GenericConversionService)binder.getConversionService();
        if (null != genericConversionService) {
            genericConversionService.addConverter(new Converter<String, Date>() {
                @Override
                public Date convert(String source) {
                    try {
                        return DateUtils.parseDate(source, AppConsts.FORMAT_DATE);
                    } catch (ParseException e) {
                        return null;
                    }
                }
            });
        }
    }

    @ExceptionHandler({Exception.class})
    private ResponseResult<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseResult.fail("A0000", "服务器繁忙");
    }

    @ExceptionHandler({AppException.class})
    private ResponseResult<String> handleAppException(AppException e) {
        return ResponseResult.fail(e);
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    private ResponseResult<String> handleBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseResult.fail("A0001", StringUtils.strip(collect.toString(), "[]"));
    }
}

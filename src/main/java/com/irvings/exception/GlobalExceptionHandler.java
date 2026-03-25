package com.irvings.exception;

import com.irvings.order.ItemUnavailableException;
import com.irvings.order.OrderAlreadyInProgressException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理商品不可用异常
    @ExceptionHandler(ItemUnavailableException.class)
    public ResponseEntity<String> handleItemUnavailable(ItemUnavailableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 处理订单已在进行中异常
    @ExceptionHandler(OrderAlreadyInProgressException.class)
    public ResponseEntity<String> handleOrderInProgress(OrderAlreadyInProgressException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    // 处理非法参数/资源不存在异常
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 处理通用异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("服务器内部错误: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
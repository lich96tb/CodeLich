package com.solar.calm.web.rest.util;

import com.solar.calm.enums.ErrorType;
import com.solar.calm.service.dto.ErrorDTO;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResultHelper {

    public ResponseEntity<?> infoResp(ErrorType errorType, HttpStatus httpStatus) {
        return infoResp(errorType, errorType.description(), httpStatus);
    }

    public ResponseEntity infoResp(ErrorType errorType, String msg, HttpStatus httpStatus) {
        return new ResponseEntity(new ErrorDTO(errorType.name(), msg), httpStatus);
    }


    public ResponseEntity infoResp(Logger logger, ErrorType errorType, String msg, HttpStatus httpStatus) {
        LogUtils.trackInfo(logger, msg);
        return new ResponseEntity(new ErrorDTO(errorType.name(), msg), httpStatus);
    }

    public ResponseEntity errorResp(Logger logger, Throwable e, ErrorType errorType, HttpStatus httpStatus) {
        return errorResp(logger, e, errorType, errorType.description(), httpStatus);
    }


    public ResponseEntity errorResp(Logger logger, Throwable e, ErrorType errorType, String msg,
                                    HttpStatus httpStatus) {
        LogUtils.traceError(logger, e, errorType.description());
        return new ResponseEntity(new ErrorDTO(errorType.name(), msg), httpStatus);
    }

}

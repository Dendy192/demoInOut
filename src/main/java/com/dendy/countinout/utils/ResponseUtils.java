package com.dendy.countinout.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static Map ResponseError(String messages){
        Map response = new HashMap();
        response.put(LabelUtils.messages, messages);
        return response;
    }
    public static <T> ResponseEntity<?> response(HttpStatus httpStatus, T body , boolean result){
        Map responseBody = new HashMap<>();
        responseBody.put(LabelUtils.result, result);
        responseBody.put(LabelUtils.data, body);
        if(!result) {
            responseBody.put(LabelUtils.data,ResponseError((String) body));
        }
        return ResponseEntity.status(httpStatus).body(responseBody);
    }
}

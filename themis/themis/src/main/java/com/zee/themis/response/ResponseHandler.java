package com.zee.themis.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",true);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);


        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> generateError(String code,String message){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message",message);
        map.put("code",code);
        map.put("success",false);

        return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> generate400Error(String code,String message){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message",message);
        map.put("code",code);
        map.put("success",false);

        return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> generate404Error(String code,String message){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message",message);
        map.put("code",code);
        map.put("success",false);

        return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
    }

}

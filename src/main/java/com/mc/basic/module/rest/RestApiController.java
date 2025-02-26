package com.mc.basic.module.rest;

import com.mc.basic.infra.response.ApiResponse;
import com.mc.basic.module.rest.request.RestRequest;
import com.mc.basic.module.rest.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

//@RestController : 자동으로 @ResponseBody 를 붙여준다.
@RestController
@RequestMapping("rest")
public class RestApiController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /***
     *  content-type : applicatoin/json 넘어왔을 때 controller
     */
    @GetMapping("test")
    //@ResponseBody
    public Map<String, String> getTest(
            RestRequest request
    ){
        log.info("req : {}", request);
        return Map.of(request.email(), request.name());
    }

    // @ModelAttribute 에 의한 암묵적 바인딩이 실패
    // @ModelAttribute : x-www-form-urlencoded 양식만 매핑 할 수 있는 객체
    @PostMapping("test")
    //@ResponseBody
    public Map<String, String> postTest(
            @RequestBody
            RestRequest request
    ){
        log.info("req : {}", request);
        return Map.of(request.email(), request.name());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Void>> get(
            RestRequest request
    ){
        return ResponseEntity.ok(ApiResponse.noContent());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RestResponse>> post(
            @RequestBody
            RestRequest request
    ){
        RestResponse response = generateRestResponse(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    private RestResponse generateRestResponse(RestRequest request) {
        return new RestResponse(request.id(), request.email(), request.name(), LocalDateTime.now());
    }
}

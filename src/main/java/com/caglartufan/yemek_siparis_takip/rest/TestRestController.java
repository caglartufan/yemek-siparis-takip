package com.caglartufan.yemek_siparis_takip.rest;

import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @GetMapping("/test")
    public ResponseEntity<@NonNull MessageResponse> test() {
        MessageResponse res = new MessageResponse();
        res.setSuccess(true);
        res.setMessage("Testing...");

        return ResponseEntity.ok(res);
    }
}

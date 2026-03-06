package com.example.demo.notification;

// 모바일 푸시 : OS(Android/iOS)에서 푸시 수신 기능을 제공, 어플을 실행하지 않아도 핸드폰만 켜져있으면 알림 수신 가능
// 웹 푸시 : 웹 브라우저에서 푸시 수신 기능 제공, 웹 사이트에 접속해야하는 것은 아니지만 웹 브라우저는 실행되어 있어야 알림 수신 가능

import com.example.demo.notification.model.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/push")
@RestController
public class NotificationControlller {
    private final NotificationService notificationService;

    @PostMapping("/sub")
    public ResponseEntity subscribe(
            @RequestBody NotificationDto.Subscribe dto
    ) {
        notificationService.subsribe(dto);
        return ResponseEntity.ok("성공");
    }
}

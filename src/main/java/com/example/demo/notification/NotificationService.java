package com.example.demo.notification;

import com.example.demo.notification.model.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void subsribe(NotificationDto.Subscribe dto) {
        notificationRepository.save(dto.toEntity());
    }
}

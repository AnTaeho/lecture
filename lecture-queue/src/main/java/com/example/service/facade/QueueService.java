package com.example.service.facade;

import com.example.controller.dto.QueueResponse;
import com.example.controller.dto.TokenResponse;

public interface QueueService {
    TokenResponse issueTokenAndEnqueue(Long userId);
    void dequeueWaitingQueue(Long userId, String token);
    void dequeueProcessingQueue(Long userId, String token);
    QueueResponse findQueueStatus(Long userId, String token);
}

package com.example.service;

import com.example.controller.dto.QueueResponse;

public interface QueueQueryService {
    QueueResponse findQueueStatus(Long userId, String token);

}

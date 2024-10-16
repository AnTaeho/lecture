package com.example.controller.dto;


import static com.example.common.QueueConst.NOT_IN_QUEUE;
import static com.example.common.QueueConst.PROCESSING;
import static com.example.common.QueueConst.WAITING;
import static com.example.common.QueueConst.ZERO;

public record QueueResponse(
        String status,
        Integer waitingQueueCount,
        Integer estimatedWaitTime
) {
    public static QueueResponse processing() {
            return new QueueResponse(PROCESSING, ZERO, ZERO);
    }

    public static QueueResponse waiting(Integer position, Integer estimatedWaitTime) {
        return new QueueResponse(WAITING, position, estimatedWaitTime);
    }

    public static QueueResponse notInQueue() {
        return new QueueResponse(NOT_IN_QUEUE, ZERO, ZERO);
    }
}

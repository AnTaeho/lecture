package com.example.seat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    private int seatNumber;
    private Long lectureId;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    public Seat(int seatNumber, Long lectureId) {
        this.seatNumber = seatNumber;
        this.lectureId = lectureId;
        this.seatStatus = SeatStatus.AVAILABLE;
    }
}

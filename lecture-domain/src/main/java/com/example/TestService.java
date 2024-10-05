package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestClassRepository testClassRepository;

    public String temp(String value) {
        TestClass testClass = new TestClass(value);
        TestClass save = testClassRepository.save(testClass);
        return save.getValue();
    }

}

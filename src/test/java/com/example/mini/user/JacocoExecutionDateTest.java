package com.example.mini.user;


import com.example.mini.MiniApplication;
import com.example.mini.service.JacocoExecutionDataService;
import com.example.mini.service.impl.JacocoExecutionDataImpl;
import com.example.mini.service.impl.JacocoParserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@SpringBootTest(classes = MiniApplication.class)
@Service
public class JacocoExecutionDateTest {

    @Autowired
    JacocoExecutionDataImpl jacocoExecutionData;

    @Test
    void parse() throws IOException {
        jacocoExecutionData.generate();
    }
}

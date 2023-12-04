package com.example.mini.user;

import com.example.mini.MiniApplication;
import com.example.mini.service.impl.JacocoParserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;


@SpringBootTest(classes = MiniApplication.class)
@Service
public class JacocoParseTest {

    @Autowired
    JacocoParserImpl jacocoXmlParser;

    @Test
    void parse() {
        jacocoXmlParser.parse();
    }
}

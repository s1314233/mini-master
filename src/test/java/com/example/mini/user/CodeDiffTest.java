package com.example.mini.user;

import com.example.mini.MiniApplication;
import com.example.mini.service.CodeDiffService;
import com.example.mini.service.impl.CodeDiffImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest(classes = MiniApplication.class)
@Service
public class CodeDiffTest {
@Autowired
    CodeDiffImpl codeDiff;
    @Test
    void create() {

        codeDiff.cloneProject("https://github.com/s1314233/auto-clock-in.git", "no-email","./dev");
    }
}

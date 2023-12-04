package com.example.mini.service;

import java.io.IOException;
import java.net.ProtocolException;

public interface BatchRecordService {

    void batchRecord(String testCases) throws IOException;
}

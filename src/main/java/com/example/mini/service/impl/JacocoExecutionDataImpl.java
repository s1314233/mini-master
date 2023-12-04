package com.example.mini.service.impl;

import com.example.mini.service.JacocoExecutionDataService;

import org.jacoco.core.data.ExecutionDataWriter;
import org.jacoco.core.runtime.RemoteControlReader;
import org.jacoco.core.runtime.RemoteControlWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Service
public class JacocoExecutionDataImpl implements JacocoExecutionDataService {

    private static final String DESTFILE = "jacoco/jacoco.exec";

    private static final String ADDRESS = "127.0.0.1";

    private static final int PORT = 6301;

    @Override
    public void generate() throws IOException {
        final FileOutputStream localFile = new FileOutputStream(DESTFILE);
        final ExecutionDataWriter localWriter = new ExecutionDataWriter(
                localFile);

        // Open a socket to the coverage agent:
        final Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
        final RemoteControlWriter writer = new RemoteControlWriter(
                socket.getOutputStream());
        final RemoteControlReader reader = new RemoteControlReader(
                socket.getInputStream());
        reader.setSessionInfoVisitor(localWriter);
        reader.setExecutionDataVisitor(localWriter);

        // Send a dump command and read the response:
        writer.visitDumpCommand(true, false);
        reader.read();

        socket.close();
        localFile.close();
        System.out.println("ok");

    }
}

package com.example.mini.service.impl;
import cn.hutool.core.io.FileUtil;
import com.example.mini.service.JacocoParserService;
import org.jacoco.core.analysis.*;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.tools.ExecFileLoader;
import org.jacoco.report.DirectorySourceFileLocator;
import org.jacoco.report.FileMultiReportOutput;
import org.jacoco.report.IReportVisitor;
import org.jacoco.report.html.HTMLFormatter;
import org.springframework.stereotype.Service;
import org.jacoco.report.internal.html.page.ReportPage;
import java.io.File;
import java.io.IOException;

@Service
public class JacocoParserImpl implements JacocoParserService {

    public static void parse() {
        // 替换为实际的路径
        String dumpPath = "../../jacoco";
        String classFilesPath = "../../target/classes";
        String srcPath = "../../src/main/java";
        String reportPath = "../../report";

        dumpPath = FileUtil.getAbsolutePath(dumpPath);
        classFilesPath = FileUtil.getAbsolutePath(classFilesPath);
        srcPath = FileUtil.getAbsolutePath(srcPath);
        reportPath = FileUtil.getAbsolutePath(reportPath);

        File execFile = new File(dumpPath + File.separator + "jacoco.exec");
        ExecFileLoader execFileLoader = new ExecFileLoader();
        try {
            execFileLoader.load(execFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CoverageBuilder coverageBuilder = new CoverageBuilder();

        Analyzer analyzer = new Analyzer(execFileLoader.getExecutionDataStore(), coverageBuilder);
        try {
            analyzer.analyzeAll(new File(classFilesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reportTitle = "Coverage Report";
        IBundleCoverage bundleCoverage = coverageBuilder.getBundle(reportTitle);

        HTMLFormatter htmlFormatter = new HTMLFormatter();
        IReportVisitor reportVisitor = null;
        try {
            reportVisitor = htmlFormatter.createVisitor(new FileMultiReportOutput(new File(reportPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SessionInfoStore sessionInfoStore = execFileLoader.getSessionInfoStore();
        ExecutionDataStore executionDataStore = execFileLoader.getExecutionDataStore();

        if (reportVisitor != null) {
            try {
                reportVisitor.visitInfo(sessionInfoStore.getInfos(), executionDataStore.getContents());
                DirectorySourceFileLocator directorySourceFileLocator =
                        new DirectorySourceFileLocator(new File(srcPath), "utf-8", 4);

                reportVisitor.visitBundle(bundleCoverage, directorySourceFileLocator);
                reportVisitor.visitEnd();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

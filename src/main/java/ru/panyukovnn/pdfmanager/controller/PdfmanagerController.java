package ru.panyukovnn.pdfmanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.panyukovnn.pdfmanager.dto.PdfRequest;
import ru.panyukovnn.pdfmanager.service.PdfService;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PdfmanagerController {

    private final PdfService pdfService;

    @PostMapping("/pdf-from-html")
    public byte[] createPdfFromHtml(@RequestBody PdfRequest pdfRequest) throws IOException, URISyntaxException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        byte[] report = pdfService.createReport(pdfRequest.getHtml());

        stopWatch.stop();
        log.info(String.format("PDF файл сформирован за %.2f с", stopWatch.getTotalTimeSeconds()));

        return report;
    }
}

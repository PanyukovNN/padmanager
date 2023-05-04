package ru.panyukovnn.pdfmanager.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfService {

    public byte[] createReport(String html) throws IOException, URISyntaxException {
        Document document = Jsoup.parse(html);
        document.charset(StandardCharsets.UTF_8);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        org.w3c.dom.Document doc = W3CDom.convert(document);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            File file = new File("/fonts/Roboto-Light.ttf");

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri("http://host.docker.internal:8080/");
            builder.useFont(file, "Roboto Light");
            builder.toStream(outputStream);
            builder.withW3cDocument(doc, "http://host.docker.internal:8080/");
            builder.run();

            return outputStream.toByteArray();
        }
    }
}

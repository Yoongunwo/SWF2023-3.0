package com.example.real3.service;

import com.example.real3.admin.Account;
import com.example.real3.admin.BookMarker;
import com.example.real3.admin.Metadata;
import com.example.real3.admin.User;
import com.example.real3.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {
    private final MetadataRepository metadataRepository;

    public BufferedImage getImage() throws IOException {
        File inputFile = new File("/resource/static/image/1.jpg");
        BufferedImage image = ImageIO.read(inputFile);

        return image;
    }
    public Metadata getMetadata(HttpSession session, BookMarker bookMarker){
        String img = "localhost:8080/images/bookmark1";
        Metadata metadata = new Metadata(
                ((User)session.getAttribute("user")).getName(),
                bookMarker.getDescription(),
                img);
        metadataRepository.save(metadata);

        return metadata;
    }
    public void makeToken(String address, String id, String uri, HttpSession session) throws IOException, InterruptedException {
        //Metadata metadata = getMetadata(session, );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kip17-api.klaytnapi.com/v2/contract/0xef9864db8494194b0e8a38280a71f74ef2d82065/token"))
                .header("x-chain-id", "1001")
                .header("x-krn", "")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmJ3SW9IRlVVZWdWOXpGRDU1TWVPRTNkWVBVZHMwTzB4WFh2MThBTjQ=")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n  "
                                                                                    + "\"to\": \"" + address + "\",\n  "
                                                                                    + "\"id\": \"" + id + "\",\n  "
                                                                                    + "\"uri\": \"" + uri + "\"\n"
                                                                                    + "}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());
    }
}

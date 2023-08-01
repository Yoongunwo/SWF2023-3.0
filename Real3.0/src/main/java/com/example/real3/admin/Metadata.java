package com.example.real3.admin;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "metadata")
@Getter @Setter
public class Metadata {
    @Id
    private String id;
    private String name;
    private String description;
    private String uri;

    public Metadata(String name, String description, String uri) {
        this.name = name;
        this.description = description;
        this.uri = uri;
    }
}

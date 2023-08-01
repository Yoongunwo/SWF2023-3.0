package com.example.real3.repository;

import com.example.real3.admin.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataRepository extends MongoRepository<Metadata, String> {
    Metadata findMetadataByName(String name);
}

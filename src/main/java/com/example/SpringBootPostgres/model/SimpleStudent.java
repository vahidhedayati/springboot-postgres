package com.example.SpringBootPostgres.model;

import org.springframework.beans.factory.annotation.Value;


/**
 *
 * This simply means return these objects from the Student - JPA will still do a full query
 */
public interface SimpleStudent {

    String getName();
    int getAge();
    @Value("#{target.address.line1 +' in ' + target.address.city}")
    String getAddress();
}

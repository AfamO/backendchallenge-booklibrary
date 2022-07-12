package com.polarisdigitech.backendchallenge.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class XMLBookResponse {

    private String isbn;
    private String title;
    private String author;
}

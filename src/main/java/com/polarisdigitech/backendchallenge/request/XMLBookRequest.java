package com.polarisdigitech.backendchallenge.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class XMLBookRequest {
    private String isbn;
    private String title;
    private String author;
}

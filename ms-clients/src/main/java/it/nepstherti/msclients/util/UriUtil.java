package it.nepstherti.msclients.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UriUtil {
    private UriUtil(){}

    public static URI addIdToCurrentUrlPath(String nif) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{nif}")
                .buildAndExpand(nif)
                .toUri();
    }
}

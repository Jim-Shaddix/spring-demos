package com.example.whoami.webparser;

import java.util.List;

public interface HeaderParser {
    public List<? extends Header> parseHeaders();
}

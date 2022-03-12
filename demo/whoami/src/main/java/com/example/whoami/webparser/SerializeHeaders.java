package com.example.whoami.webparser;

import com.example.whoami.webparser.flavioparser.FlavioHeader;
import com.example.whoami.webparser.flavioparser.FlavioHeaderParser;
import com.example.whoami.webparser.rfcparser.RfcHeaderParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SerializeHeaders {

    FlavioHeaderParser flavioHeaderParser;
    RfcHeaderParser rfcHeaderParser;

    public void serializeHeaders() {
        List<FlavioHeader> headers = (List<FlavioHeader>) flavioHeaderParser.parseHeaders();
    }

}

package cg.park.simpleauth.common.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiResponse {

    public ResponseEntity<Message> of (Message msg) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

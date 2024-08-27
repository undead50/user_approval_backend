package com.ebl.userapproval.service;

import com.ebl.userapproval.dto.SignatureRequestDto;
import org.springframework.http.ResponseEntity;

public interface ApimsApiService {

    ResponseEntity<?> mainApiService(SignatureRequestDto signatureRequestDto);

}

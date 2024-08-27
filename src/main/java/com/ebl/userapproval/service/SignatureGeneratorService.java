package com.ebl.userapproval.service;

import com.ebl.userapproval.dto.SignatureRequestDto;
import com.ebl.userapproval.model.FinalApimsResponse;

public interface SignatureGeneratorService {

    FinalApimsResponse generatePayloadWithPkcs1(SignatureRequestDto signatureRequestDto);

}

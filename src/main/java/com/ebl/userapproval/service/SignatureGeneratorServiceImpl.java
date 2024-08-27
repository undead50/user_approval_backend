package com.ebl.userapproval.service;


import com.ebl.userapproval.config.ApimsConfig;
import com.ebl.userapproval.dto.SignatureRequestDto;
import com.ebl.userapproval.execption.EblException;
import com.ebl.userapproval.execption.InvalidSignatureException;
import com.ebl.userapproval.model.FinalApimsResponse;
import com.ebl.userapproval.utils.SignatureUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class SignatureGeneratorServiceImpl implements SignatureGeneratorService {

    private final ApimsConfig apimsConfig;
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public SignatureGeneratorServiceImpl(ApimsConfig apimsConfig) {
        this.apimsConfig = apimsConfig;
    }

    @Override
    public FinalApimsResponse generatePayloadWithPkcs1(SignatureRequestDto signatureRequestDto) {
        if (Objects.isNull(signatureRequestDto)) {
            throw new EblException(HttpStatus.BAD_REQUEST, "Payload / request body is required ! ");
        }
        return transformRequest(signatureRequestDto);
    }


    private FinalApimsResponse transformRequest(SignatureRequestDto signatureRequestDto) {

        // For current date time and format:
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT, Locale.ENGLISH);
        final String currentDateTime = LocalDateTime.now().format(formatter);

        final Gson gson = new Gson();
        final String base64String = Base64.getEncoder()
                .encodeToString(gson.toJson(signatureRequestDto.getRequest()).getBytes());

        final FinalApimsResponse finalApimsResponse = new FinalApimsResponse();
        finalApimsResponse.setFunctionName(signatureRequestDto.getFunctionName());
        finalApimsResponse.setData(base64String);
        // For Signature Method:
        Map<String, Object> signatureMap = new HashMap<>();
        signatureMap.put("TimeStamp", currentDateTime);
        signatureMap.put("Model", signatureRequestDto.getRequest());
        // For signature:
        log.info("This is the certificate path --> {}", apimsConfig.getCertFilePath());
        log.info("Signature map --> {}", signatureMap);
        try {
            finalApimsResponse.setSignature(
                    SignatureUtil.signSHA256RSAPKCS1(gson.toJson(signatureMap), apimsConfig.getCertFilePath()));
        } catch (InvalidSignatureException | IOException exp) {
            throw new EblException(HttpStatus.BAD_REQUEST, "Invalid Signature Format !");
        }
        finalApimsResponse.setTimeStamp(currentDateTime);

        return finalApimsResponse;
    }



}
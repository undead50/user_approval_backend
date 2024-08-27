package com.ebl.userapproval.service;

import com.ebl.userapproval.config.ApimsConfig;
import com.ebl.userapproval.dto.SignatureRequestDto;
import com.ebl.userapproval.execption.EblException;
import com.ebl.userapproval.model.FinalApimsResponse;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ApimsApiServiceImpl implements ApimsApiService {

    private final ApimsConfig apimsConfig;
    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;
    private final SignatureGeneratorService signatureGeneratorService;

    public ApimsApiServiceImpl(ApimsConfig apimsConfig, SignatureGeneratorService signatureGeneratorService) {
        this.apimsConfig = apimsConfig;
        this.signatureGeneratorService = signatureGeneratorService;

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setBasicAuth(apimsConfig.getUsername(), apimsConfig.getPassword());

        final MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
    }

    public ResponseEntity<?> mainApiService(SignatureRequestDto signatureRequestDto) {

        FinalApimsResponse finalApimsResponse = signatureGeneratorService.generatePayloadWithPkcs1(signatureRequestDto);

        Gson gson = new Gson();
        System.out.println("Final Payload --> " + gson.toJson(finalApimsResponse));

        final HttpEntity<FinalApimsResponse> payload = new HttpEntity<>( finalApimsResponse, httpHeaders);

        try {
            final ResponseEntity<Object> response = restTemplate.postForEntity(apimsConfig.getApi(), payload, Object.class);
            return response;
        } catch (Exception ex) {
            throw new EblException(HttpStatus.BAD_REQUEST, "Unable to request APIMS service !");
        }

    }
}

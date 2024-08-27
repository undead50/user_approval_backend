package com.ebl.userapproval.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Ritesh Shrestha on 8/30/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignatureRequestDto {

    private String functionName;

    private Map<String, Object> request;

}

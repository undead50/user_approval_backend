package com.ebl.userapproval.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ritesh Shrestha on 8/30/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalApimsResponse {

    @JsonProperty("FunctionName")
    private String functionName;

    @JsonProperty("Data")
    private String data;

    @JsonProperty("Signature")
    private String signature;

    @JsonProperty("TimeStamp")
    private String timeStamp;
}

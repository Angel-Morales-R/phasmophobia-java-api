package com.digisoft.apiphasmo.messages;

import com.digisoft.apiphasmo.models.TestingsModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class TestingsApiResponse {
    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TestingsModel getTesting;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TestingsModel createdTesting;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TestingsModel updatedTesting;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TestingsModel deletedTesting;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page<TestingsModel> testingsPage;
}

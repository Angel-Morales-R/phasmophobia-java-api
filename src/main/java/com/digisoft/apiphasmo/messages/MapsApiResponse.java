package com.digisoft.apiphasmo.messages;

import com.digisoft.apiphasmo.models.MapsModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.domain.Page;
@Data
public class MapsApiResponse {
    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapsModel map;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapsModel createdMap;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapsModel updatedMap;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapsModel deletedMap;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page<MapsModel> mapsPage;
}

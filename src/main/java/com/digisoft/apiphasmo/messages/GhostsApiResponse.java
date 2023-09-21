package com.digisoft.apiphasmo.messages;

import com.digisoft.apiphasmo.models.GhostsModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.domain.Page;
@Data
public class GhostsApiResponse {
    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GhostsModel getGhost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GhostsModel createdGhost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GhostsModel updatedGhost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GhostsModel deletedGhost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page<GhostsModel> ghostsPage;
}

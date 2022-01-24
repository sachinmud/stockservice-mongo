package com.sachin.stockservice.api;

import com.sachin.stockservice.model.ErrorDetails;
import com.sachin.stockservice.model.StockEvent;
import com.sachin.stockservice.model.StockRequest;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link StockQuoteApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-15T23:32:04.763918800+05:30[Asia/Calcutta]")

public interface StockQuoteApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /stockQuote
     *
     * @param stockRequest  (optional)
     * @return event response (status code 200)
     *         or error response (status code 400)
     * @see StockQuoteApi#stockQuotePost
     */
    default ResponseEntity<StockEvent> stockQuotePost(StockRequest stockRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"datetime\" : \"2021-01-30 08:30\", \"name\" : \"name\", \"id\" : \"id\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

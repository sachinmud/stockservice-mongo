package com.sachin.stockservice.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-15T23:32:04.763918800+05:30[Asia/Calcutta]")

@Controller
@RequestMapping("${openapi.stockFinanceEventGenerator.base-path:/stockdetailsservice/v1}")
public class StockQuoteApiController implements StockQuoteApi {

    private final StockQuoteApiDelegate delegate;

    public StockQuoteApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) StockQuoteApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new StockQuoteApiDelegate() {});
    }

    @Override
    public StockQuoteApiDelegate getDelegate() {
        return delegate;
    }

}

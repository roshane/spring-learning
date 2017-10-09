//package com.aeon.ws.client;
//
//import com.aeon.ws.integration.GetQuote;
//import com.aeon.ws.integration.GetQuoteResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
//import org.springframework.ws.soap.client.core.SoapActionCallback;
//
//
///**
// * Created by roshane on 10/5/2017.
// */
//public class QuoteClient extends WebServiceGatewaySupport {
//
//    private static final Logger logger = LoggerFactory.getLogger(QuoteClient.class);
//
//    public GetQuoteResponse getQuoteResponse(String ticker) {
//
//        logger.info("get quota for ticker [{}]", ticker);
//        GetQuote request = new GetQuote();
//        request.setSymbol(ticker);
//
//        GetQuoteResponse response = (GetQuoteResponse) getWebServiceTemplate()
//                .marshalSendAndReceive("http://www.webservicex.com/stockquote.asmx",
//                        request, new SoapActionCallback("http://www.webserviceX.NET/GetQuote"));
//
//        return response;
//    }
//}

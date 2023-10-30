//package com.ecommerce.teamviewerecommerce.bdd.steps;
//
//
//
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import lombok.SneakyThrows;
//import org.apache.hc.client5.http.classic.HttpClient;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.http.MediaType.valueOf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//public class OrderStepDefinition {
//
//    @Autowired
//    protected MockMvc mvc;
//
//    private ResultActions performedActions;
//
//    private CloseableHttpClient httpClient = new HttpClient();
//
//    private CloseableHttpResponse httpResponse;
//
//    @Given("application is up")
//    public void application_is_up() {
//        System.out.println("reached-1");
////        performedActions = mvc.perform(get("/echo")
////                        .queryParam("message", message))
////                .andDo(print());
//    }
//    @When("I send a message {string}")
//    public void iSendAMessage(String message) throws Exception {
//        System.out.println("reached-2 " + message);
////        performedActions = mvc.perform(get("/echo")
////                        .queryParam("message", message))
////                .andDo(print());
//    }
//
//    @Then("API replies {string}")
//    public void apiReplies(String expectedReply) throws Exception {
//        System.out.println("reached-3 " + expectedReply);
////        performedActions.andExpect(status().isOk())
////                .andExpect(content().contentType(valueOf("text/plain;charset=UTF-8")))
////                .andExpect(content().string(expectedReply));
//    }
//
//
//    @SneakyThrows
//    @Given("The order id with order number {string}")
//    public void theOrderIdWithOrderNumber(String orderNumber) {
//        performedActions  = mvc.perform(get("/api/orders/"));
//        HttpGet get = new HttpGet("http://localhost:5443" + "/api/orders/" + orderNumber);
//        httpResponse = httpClient.execute(get);
//
//
//    }
//
//    @Then("I expect status_code is {int} and response data is {string}")
//    public void iExpectStatus_code(int expectedStatusCode, String itemNumber) {
//        assertThat(httpResponse.getCode() == expectedStatusCode);
//        assertThat(httpResponse.getEntity().toString().contains(itemNumber));
//
//    }
//
//}
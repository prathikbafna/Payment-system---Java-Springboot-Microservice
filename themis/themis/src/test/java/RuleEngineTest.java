import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.themis.Themis;
import com.zee.themis.dto.Rule;
import com.zee.themis.model.payment.PaymentValidateRequest;
import com.zee.themis.model.payment.PaymentValidateResponse;
import com.zee.themis.service.KnowledgeBaseService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import responseobjects.PaymentValidateResponseDto;

import java.util.List;

import static com.zee.themis.entity.RuleNamespace.DEFAULT;
import static com.zee.themis.entity.RuleNamespace.PAYMENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Themis.class)
public class RuleEngineTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private KnowledgeBaseService knowledgeBaseServiceMock;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.objectMapper = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        when(knowledgeBaseServiceMock.getAllRuleByNamespace(PAYMENT)).thenReturn(getListOfPaymentRules());
        when(knowledgeBaseServiceMock.getAllRuleByNamespace(DEFAULT)).thenReturn(getListOfDefaultRules());
    }

    @Test
    public void verifyGetAllRulesForPaymentNamespace() throws Exception {
        mockMvc.perform(get("/api/v1/rules?namespace=PAYMENT")
                        .header("api_key","test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
        );
    }

    @Test
    public void verifyGetAllRulesForDefaultNamespace() throws Exception {
        mockMvc.perform(get("/api/v1/rules?namespace=DEFAULT")
                        .header("api_key","test")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void verifyPaymentRuleForApple() throws Exception {
        PaymentValidateRequest paymentValidateRequest = PaymentValidateRequest.builder().
                amount(1.0).
                country("ALL").
                providerName("Apple").
                merchantAccount("Apple").
                build();

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/rules/payment")
                        .header("api_key","test")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentValidateRequest)))
                        .andExpect(status().isOk()
                ).andReturn();

        PaymentValidateResponseDto dto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),PaymentValidateResponseDto.class);

        String actualResponseBody = objectMapper.writeValueAsString(dto);

        PaymentValidateResponse paymentValidateResponse = PaymentValidateResponse.builder().
                isValidPayment(true).
                minimumPaymentAmount("0.0").
                gracedBillingAllowed(true).
                gracedBillingDays(1).
                freeTrialSupported(true).
                providerId("90ed3109-330a-4fd3-b740-723071322112").
                build();

        PaymentValidateResponseDto paymentValidateResponseDto =  PaymentValidateResponseDto.builder().
                data(paymentValidateResponse).
                success(true).
                message("payment rule validated!").
                code(1).
                build();

        String expectedResponseBody = objectMapper.writeValueAsString(paymentValidateResponseDto);

        assertThat(expectedResponseBody).isEqualTo(actualResponseBody);
    }

    @Test
    public void verifyPaymentRuleForAdyenApac() throws Exception {
        PaymentValidateRequest paymentValidateRequest = PaymentValidateRequest.builder().
                amount(1.0).
                country("ALL").
                providerName("AdyenZee5APAC").
                merchantAccount("AdyenZee5APAC").
                build();

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/rules/payment")
                        .header("api_key","test")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentValidateRequest)))
                .andExpect(status().isOk()
                ).andReturn();

        PaymentValidateResponseDto dto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),PaymentValidateResponseDto.class);

        String actualResponseBody = objectMapper.writeValueAsString(dto);


        PaymentValidateResponse paymentValidateResponse = PaymentValidateResponse.builder().
                isValidPayment(true).
                minimumPaymentAmount("0.0").
                gracedBillingAllowed(true).
                gracedBillingDays(1).
                freeTrialSupported(true).
                providerId("90ed3109-330a-4fd3-b740-723071322107").
                build();

        PaymentValidateResponseDto paymentValidateResponseDto =  PaymentValidateResponseDto.builder().
                data(paymentValidateResponse).
                message("payment rule validated!").
                success(true).
                code(1).
                build();

        String expectedResponseBody = objectMapper.writeValueAsString(paymentValidateResponseDto);

        assertThat(expectedResponseBody).isEqualToIgnoringWhitespace(actualResponseBody);
    }

    private List<Rule> getListOfPaymentRules(){
        Rule rule1 = Rule.builder()
                .ruleNamespace(PAYMENT)
                .condition( "input.providerName == '$(payment.apple)' && input.amount >= $(payment.apple_min_payment_amount)")
                .action("output.setIsValidPayment(true);output.setMinimumPaymentAmount(0.0);output.setGracedBillingAllowed(true);output.setGracedBillingDays(1.0);output.setProviderId('$(payment.apple_provider_id)')")
                .priority("1")
                .description("Apple valid payment rule")
                .build();
        Rule rule2 = Rule.builder()
                .ruleNamespace(PAYMENT)
                .condition("input.providerName == '$(payment.adyen_zee5_apac)' && input.amount >= $(payment.adyen_apac_min_payment_amount)")
                .action("output.setIsValidPayment(true);output.setMinimumPaymentAmount(0.0);output.setGracedBillingAllowed(true);output.setGracedBillingDays(1.0);output.setProviderId('$(payment.adyen_apac_provider_id)')")
                .priority("2")
                .description("Adyen APAC valid payment rule")
                .build();

        List<Rule> allRulesByNamespace = Lists.newArrayList(rule1, rule2);
        return allRulesByNamespace;
    }

    private List<Rule> getListOfDefaultRules(){
        Rule rule1 = Rule.builder()
                .ruleNamespace(PAYMENT)
                .condition( "input.providerName == '$(payment.apple)' && input.amount >= $(payment.apple_min_payment_amount)")
                .action("output.setIsValidPayment(true);output.setMinimumPaymentAmount(0.0);output.setGracedBillingAllowed(true);output.setGracedBillingDays(1.0);output.setProviderId('$(payment.apple_provider_id)')")
                .priority("1")
                .description("Apple valid payment rule")
                .build();
        Rule rule2 = Rule.builder()
                .ruleNamespace(PAYMENT)
                .condition("input.providerName == '$(payment.adyen_zee5_apac)' && input.amount >= $(payment.adyen_apac_min_payment_amount)")
                .action("output.setIsValidPayment(true);output.setMinimumPaymentAmount(0.0);output.setGracedBillingAllowed(true);output.setGracedBillingDays(1.0);output.setProviderId('$(payment.adyen_apac_provider_id)')")
                .priority("2")
                .description("Adyen APAC valid payment rule")
                .build();

        List<Rule> allRulesByNamespace = Lists.newArrayList(rule1, rule2);
        return allRulesByNamespace;
    }
}

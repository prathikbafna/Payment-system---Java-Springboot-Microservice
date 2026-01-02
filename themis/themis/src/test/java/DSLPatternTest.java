import com.zee.themis.Themis;
import com.zee.themis.langparser.DSLPatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Themis.class)
public class DSLPatternTest {

    @Autowired
    private DSLPatternUtil dslPatternUtil;

    final String expression1 = "input.providerName == '$(payment.apple)' && input.amount >= $(payment.apple_min_payment_amount)";
    final String expression2 = "input.providerName == '$(payment.adyen_zee5_apac)' && input.amount >= $(payment.adyen_apac_min_payment_amount)";

    @Test
    public void verifyPatternInExpression(){
        String keyword = dslPatternUtil.getListOfDslKeywords(expression1).get(0);
        assertThat(keyword).isEqualTo("$(payment.apple)");
    }

    @Test
    public void verifyNumberOfPatternFoundInExpression(){
        int numberOfPatters = dslPatternUtil.getListOfDslKeywords(expression2).size();
        assertThat(numberOfPatters).isEqualTo(2);
    }

    @Test
    public void verifyExtractValue(){
        String keyword = dslPatternUtil.getListOfDslKeywords(expression1).get(0);
        assertThat(dslPatternUtil.extractKeyword(keyword)).isEqualTo("payment.apple");
    }

    @Test
    public void verifyKeywordResolverValue(){
        String keyword = dslPatternUtil.getListOfDslKeywords(expression1).get(0);
        assertThat(dslPatternUtil.getKeywordResolver(dslPatternUtil.extractKeyword(keyword))).isEqualTo("payment");
    }

    @Test
    public void verifyKeywordValue(){
        String keyword = dslPatternUtil.getListOfDslKeywords(expression1).get(0);
        assertThat(dslPatternUtil.getKeywordValue(dslPatternUtil.extractKeyword(keyword))).isEqualTo("apple");
    }
}

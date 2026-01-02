package com.zee.themis.dslresolver.impl;

import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.dslresolver.DSLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentResolver implements DSLResolver {
    private static final String RESOLVER_KEYWORD = "payment";
    private static final String ADYEN_ZEE5_APAC_PROVIDER_ID = "adyen_apac_provider_id";
    private static final String ADYEN_ZEE5_APAC_PAYMENT_PROVIDER_NAME = "adyen_zee5_apac";
    private static final String ADYEN_ZEE5_APAC_MIN_PAYMENT_AMT = "adyen_apac_min_payment_amount";
    private static final String ADYEN_ZEE5_APAC_GRACED_BILLING_ALLOWED = "adyen_apac_graced_billing_allowed";
    private static final String ADYEN_ZEE5_APAC_GRACED_BILLING_DAYS = "adyen_apac_graced_billing_days";
    private static final String ADYEN_ZEE5_APAC_COUNTRY = "adyen_apac_country";
    private static final String ADYEN_ZEE5_AFRICA_PROVIDER_ID = "adyen_africa_provider_id";
    private static final String ADYEN_ZEE5_AFRICA_PAYMENT_PROVIDER_NAME = "adyen_zee5_africa";
    private static final String ADYEN_ZEE5_AFRICA_MIN_PAYMENT_AMT = "adyen_africa_min_payment_amount";
    private static final String ADYEN_ZEE5_AFRICA_GRACED_BILLING_ALLOWED = "adyen_africa_graced_billing_allowed";
    private static final String ADYEN_ZEE5_AFRICA_GRACED_BILLING_DAYS = "adyen_africa_graced_billing_days";
    private static final String ADYEN_ZEE5_AFRICA_COUNTRY = "adyen_africa_country";
    private static final String ADYEN_ZEE5_AMERICAS_PROVIDER_ID = "adyen_americas_provider_id";
    private static final String ADYEN_ZEE5_AMERICAS_PAYMENT_PROVIDER_NAME = "adyen_zee5_americas";
    private static final String ADYEN_ZEE5_AMERICAS_MIN_PAYMENT_AMT = "adyen_americas_min_payment_amount";
    private static final String ADYEN_ZEE5_AMERICAS_GRACED_BILLING_ALLOWED = "adyen_americas_graced_billing_allowed";
    private static final String ADYEN_ZEE5_AMERICAS_GRACED_BILLING_DAYS = "adyen_americas_graced_billing_days";
    private static final String ADYEN_ZEE5_AMERICAS_COUNTRY = "adyen_americas_country";
    private static final String ADYEN_ZEE5_EUROPE_PROVIDER_ID = "adyen_europe_provider_id";
    private static final String ADYEN_ZEE5_EUROPE_PAYMENT_PROVIDER_NAME = "adyen_zee5_europe";
    private static final String ADYEN_ZEE5_EUROPE_MIN_PAYMENT_AMT = "adyen_europe_min_payment_amount";
    private static final String ADYEN_ZEE5_EUROPE_GRACED_BILLING_ALLOWED = "adyen_europe_graced_billing_allowed";
    private static final String ADYEN_ZEE5_EUROPE_GRACED_BILLING_DAYS = "adyen_europe_graced_billing_days";
    private static final String ADYEN_ZEE5_EUROPE_COUNTRY = "adyen_europe_country";
    private static final String ADYEN_ZEE5_MIDDLE_EAST_PAYMENT_PROVIDER_NAME = "adyen_zee5_middle_east";
    private static final String ADYEN_ZEE5_MIDDLE_EAST_PROVIDER_ID = "adyen_middle_east_provider_id";
    private static final String ADYEN_ZEE5_MIDDLE_EAST_MIN_PAYMENT_AMT = "adyen_middle_east_min_payment_amount";
    private static final String ADYEN_ZEE5_MIDDLE_EAST_GRACED_BILLING_ALLOWED = "adyen_middle_east_graced_billing_allowed";
    private static final String ADYEN_ZEE5_MIDDLE_EAST_GRACED_BILLING_DAYS = "adyen_middle_east_graced_billing_days";
    private static final String ADYEN_ZEE5_MIDDLE_EAST_COUNTRY = "adyen_middle_east_country";
    private static final String APPLE_PROVIDER_ID = "apple_provider_id";
    private static final String APPLE_PAYMENT_PROVIDER_NAME = "apple";
    private static final String APPLE_MIN_PAYMENT_AMT = "apple_min_payment_amount";
    private static final String APPLE_GRACED_BILLING_ALLOWED = "apple_graced_billing_allowed";
    private static final String APPLE_GRACED_BILLING_DAYS = "apple_graced_billing_days";
    private static final String APPLE_COUNTRY = "apple_country";
    private static final String PLAY_STORE_PAYMENT_PROVIDER_NAME = "play_store";
    private static final String PLAY_STORE_MIN_PAYMENT_AMT = "play_store_min_payment_amount";
    private static final String PLAY_STORE_PROVIDER_ID = "play_store_provider_id";
    private static final String JUSPAY_PROVIDER_ID = "juspay_provider_id";
    private static final String JUSPAY_PAYMENT_PROVIDER_NAME = "juspay";
    private static final String JUSPAY_MIN_PAYMENT_AMT = "juspay_min_payment_amount";


    //Adyen APAC payment config
    @Value("${payment-provider-config.Adyen-APAC.payment-provider-id}")
    private String AdyenApacProviderId;

    @Value("${payment-provider-config.Adyen-APAC.payment-provider-name}")
    private String AdyenApacPaymentProviderName;

    @Value("${payment-provider-config.Adyen-APAC.min-payment-amount}")
    private Double AdyenApacMinPaymentAmount;

    @Value("${payment-provider-config.Adyen-APAC.graced-billing-allowed}")
    private Boolean AdyenApacGracedBillingAllowed;

    @Value("${payment-provider-config.Adyen-APAC.graced-billing-days}")
    private float AdyenApacGracedBillingDays;

    @Value("${payment-provider-config.Adyen-APAC.country}")
    private String AdyenApacCountry;

    //Adyen Africa payment config
    @Value("${payment-provider-config.Adyen-Africa.payment-provider-id}")
    private String AdyenAfricaProviderId;

    @Value("${payment-provider-config.Adyen-Africa.payment-provider-name}")
    private String AdyenAfricaPaymentProviderName;

    @Value("${payment-provider-config.Adyen-Africa.min-payment-amount}")
    private Double AdyenAfricaMinPaymentAmount;

    @Value("${payment-provider-config.Adyen-Africa.graced-billing-allowed}")
    private Boolean AdyenAfricaGracedBillingAllowed;

    @Value("${payment-provider-config.Adyen-Africa.graced-billing-days}")
    private float AdyenAfricaGracedBillingDays;

    @Value("${payment-provider-config.Adyen-Africa.country}")
    private String AdyenAfricaCountry;

    //Adyen Americas payment config
    @Value("${payment-provider-config.Adyen-Americas.payment-provider-id}")
    private String AdyenAmericasProviderId;

    @Value("${payment-provider-config.Adyen-Americas.payment-provider-name}")
    private String AdyenAmericasPaymentProviderName;

    @Value("${payment-provider-config.Adyen-Americas.min-payment-amount}")
    private Double AdyenAmericasMinPaymentAmount;

    @Value("${payment-provider-config.Adyen-Americas.graced-billing-allowed}")
    private Boolean AdyenAmericasGracedBillingAllowed;

    @Value("${payment-provider-config.Adyen-Americas.graced-billing-days}")
    private float AdyenAmericasGracedBillingDays;

    @Value("${payment-provider-config.Adyen-Americas.country}")
    private String AdyenAmericasCountry;

    //Adyen Europe payment config
    @Value("${payment-provider-config.Adyen-Europe.payment-provider-id}")
    private String AdyenEuropeProviderId;

    @Value("${payment-provider-config.Adyen-Europe.payment-provider-name}")
    private String AdyenEuropePaymentProviderName;

    @Value("${payment-provider-config.Adyen-Europe.min-payment-amount}")
    private Double AdyenEuropeMinPaymentAmount;

    @Value("${payment-provider-config.Adyen-Europe.graced-billing-allowed}")
    private Boolean AdyenEuropeGracedBillingAllowed;

    @Value("${payment-provider-config.Adyen-Europe.graced-billing-days}")
    private float AdyenEuropeGracedBillingDays;

    @Value("${payment-provider-config.Adyen-Europe.country}")
    private String AdyenEuropeCountry;

    //Adyen MiddleEast payment config
    @Value("${payment-provider-config.Adyen-MiddleEast.payment-provider-id}")
    private String AdyenMiddleEastProviderId;

    @Value("${payment-provider-config.Adyen-MiddleEast.payment-provider-name}")
    private String AdyenMiddleEastPaymentProviderName;

    @Value("${payment-provider-config.Adyen-MiddleEast.min-payment-amount}")
    private Double AdyenMiddleEastMinPaymentAmount;

    @Value("${payment-provider-config.Adyen-MiddleEast.graced-billing-allowed}")
    private Boolean AdyenMiddleEastGracedBillingAllowed;

    @Value("${payment-provider-config.Adyen-MiddleEast.graced-billing-days}")
    private float AdyenMiddleEastGracedBillingDays;

    @Value("${payment-provider-config.Adyen-MiddleEast.country}")
    private String AdyenMiddleEastCountry;

    //Apple payment config
    @Value("${payment-provider-config.Apple.payment-provider-id}")
    private String AppleProviderId;

    @Value("${payment-provider-config.Juspay.payment-provider-id}")
    private String JuspayProviderId;

    @Value("${payment-provider-config.Apple.payment-provider-name}")
    private String ApplePaymentProviderName;

    @Value("${payment-provider-config.Juspay.payment-provider-name}")
    private String JuspayPaymentProviderName;

    @Value("${payment-provider-config.Apple.min-payment-amount}")
    private Double AppleMinPaymentAmount;

    @Value("${payment-provider-config.Juspay.min-payment-amount}")
    private Double JuspayMinPaymentAmount;

    @Value("${payment-provider-config.Apple.graced-billing-allowed}")
    private Boolean AppleGracedBillingAllowed;

    @Value("${payment-provider-config.Apple.graced-billing-days}")
    private float AppleGracedBillingDays;

    @Value("${payment-provider-config.Apple.country}")
    private String AppleCountry;

    @Value("${payment-provider-config.Play-Store.payment-provider-id}")
    private String playStoreProviderId;

    @Value("${payment-provider-config.Play-Store.payment-provider-name}")
    private String playStorePaymentProviderName;

    @Value("${payment-provider-config.Play-Store.min-payment-amount}")
    private Double playStoreMinPaymentAmount;


    @Override
    public String getResolverKeyword() {
        return RESOLVER_KEYWORD;
    }

    @Override
    public Object resolveValue(String keyword) {
        //TODO: can be added to payment provider cache
        return switch(keyword){
            case ADYEN_ZEE5_APAC_PROVIDER_ID -> AdyenApacProviderId;
            case ADYEN_ZEE5_APAC_PAYMENT_PROVIDER_NAME  -> AdyenApacPaymentProviderName;
            case ADYEN_ZEE5_APAC_MIN_PAYMENT_AMT -> AdyenApacMinPaymentAmount;
            case ADYEN_ZEE5_APAC_GRACED_BILLING_ALLOWED -> AdyenApacGracedBillingAllowed;
            case ADYEN_ZEE5_APAC_GRACED_BILLING_DAYS -> AdyenApacGracedBillingDays;
            case ADYEN_ZEE5_APAC_COUNTRY -> AdyenApacCountry;
            case ADYEN_ZEE5_AFRICA_PROVIDER_ID -> AdyenAfricaProviderId;
            case ADYEN_ZEE5_AFRICA_PAYMENT_PROVIDER_NAME -> AdyenAfricaPaymentProviderName;
            case ADYEN_ZEE5_AFRICA_MIN_PAYMENT_AMT -> AdyenAfricaMinPaymentAmount;
            case ADYEN_ZEE5_AFRICA_GRACED_BILLING_ALLOWED -> AdyenAfricaGracedBillingAllowed;
            case ADYEN_ZEE5_AFRICA_GRACED_BILLING_DAYS -> AdyenAfricaGracedBillingDays;
            case ADYEN_ZEE5_AFRICA_COUNTRY -> AdyenAfricaCountry;
            case ADYEN_ZEE5_AMERICAS_PROVIDER_ID -> AdyenAmericasProviderId;
            case ADYEN_ZEE5_AMERICAS_PAYMENT_PROVIDER_NAME -> AdyenAmericasPaymentProviderName;
            case ADYEN_ZEE5_AMERICAS_MIN_PAYMENT_AMT -> AdyenAmericasMinPaymentAmount;
            case ADYEN_ZEE5_AMERICAS_GRACED_BILLING_ALLOWED -> AdyenAmericasGracedBillingAllowed;
            case ADYEN_ZEE5_AMERICAS_GRACED_BILLING_DAYS -> AdyenAmericasGracedBillingDays;
            case ADYEN_ZEE5_AMERICAS_COUNTRY -> AdyenAmericasCountry;
            case ADYEN_ZEE5_EUROPE_PROVIDER_ID -> AdyenEuropeProviderId;
            case ADYEN_ZEE5_EUROPE_PAYMENT_PROVIDER_NAME -> AdyenEuropePaymentProviderName;
            case ADYEN_ZEE5_EUROPE_MIN_PAYMENT_AMT -> AdyenEuropeMinPaymentAmount;
            case ADYEN_ZEE5_EUROPE_GRACED_BILLING_ALLOWED -> AdyenEuropeGracedBillingAllowed;
            case ADYEN_ZEE5_EUROPE_GRACED_BILLING_DAYS -> AdyenEuropeGracedBillingDays;
            case ADYEN_ZEE5_EUROPE_COUNTRY -> AdyenEuropeCountry;
            case ADYEN_ZEE5_MIDDLE_EAST_PROVIDER_ID -> AdyenMiddleEastProviderId;
            case ADYEN_ZEE5_MIDDLE_EAST_PAYMENT_PROVIDER_NAME -> AdyenMiddleEastPaymentProviderName;
            case ADYEN_ZEE5_MIDDLE_EAST_MIN_PAYMENT_AMT -> AdyenMiddleEastMinPaymentAmount;
            case ADYEN_ZEE5_MIDDLE_EAST_GRACED_BILLING_ALLOWED -> AdyenMiddleEastGracedBillingAllowed;
            case ADYEN_ZEE5_MIDDLE_EAST_GRACED_BILLING_DAYS -> AdyenMiddleEastGracedBillingDays;
            case ADYEN_ZEE5_MIDDLE_EAST_COUNTRY -> AdyenMiddleEastCountry;
            case APPLE_PROVIDER_ID -> AppleProviderId;
            case APPLE_PAYMENT_PROVIDER_NAME -> ApplePaymentProviderName;
            case APPLE_GRACED_BILLING_ALLOWED -> AppleGracedBillingAllowed;
            case APPLE_GRACED_BILLING_DAYS -> AppleGracedBillingDays;
            case APPLE_MIN_PAYMENT_AMT -> AppleMinPaymentAmount;
            case APPLE_COUNTRY -> AppleCountry;
            case PLAY_STORE_PROVIDER_ID -> playStoreProviderId;
            case PLAY_STORE_PAYMENT_PROVIDER_NAME -> playStorePaymentProviderName;
            case PLAY_STORE_MIN_PAYMENT_AMT -> playStoreMinPaymentAmount;
            case JUSPAY_PROVIDER_ID -> JuspayProviderId;
            case JUSPAY_PAYMENT_PROVIDER_NAME -> JuspayPaymentProviderName;
            case JUSPAY_MIN_PAYMENT_AMT -> JuspayMinPaymentAmount;
            default -> ApplicationConstants.NOT_APPLICABLE;
        };
    }
}

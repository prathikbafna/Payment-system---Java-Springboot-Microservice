package com.zee.themis.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.PgDetail;
import com.zee.themis.dto.PgDetailDTO;
import com.zee.themis.entity.InHousePaymentGatewayMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class InHousePaymentMetaDataConverter implements Converter<InHousePaymentGatewayMetaData, InHousePaymentMethodResponse, InHousePaymentMethodResponse>{

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public InHousePaymentMethodResponse convert(InHousePaymentGatewayMetaData input, InHousePaymentMethodResponse existing) {
        if(Objects.isNull(input)){
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new InHousePaymentMethodResponse();
        }
        existing.setId(input.getId());
        existing.setPlatform(input.getPlatform());
        existing.setTenantId(input.getTenantId());
        List<PgDetailDTO> pgDetailDTOS = new ArrayList<>();
        for(PgDetail each : input.getPg()){
            PgDetailDTO pgDetailDTO = PgDetailDTO.builder()
                    .pgId(each.getPgId())
                    .pgName(each.getPgName())
                    .priority(each.getPriority())
                    .redirectUri(each.getRedirectUri())
                    .build();
            pgDetailDTO.setPaymentMethods(objectMapper.convertValue(each.getPaymentMethods(),JsonNode.class));
            pgDetailDTOS.add(pgDetailDTO);
        }
        existing.setPg(pgDetailDTOS);
        return existing;

    }
}

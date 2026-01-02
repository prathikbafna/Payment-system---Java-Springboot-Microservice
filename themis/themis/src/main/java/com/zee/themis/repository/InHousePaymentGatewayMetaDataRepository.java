package com.zee.themis.repository;

import com.zee.themis.entity.InHousePaymentGatewayMetaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InHousePaymentGatewayMetaDataRepository extends MongoRepository<InHousePaymentGatewayMetaData,String> {

    InHousePaymentGatewayMetaData findByCountryAndPlatform(String country, String platform);
}

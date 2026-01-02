package com.zee.themis;

import com.zee.themis.constant.ApplicationConstants;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@SecurityScheme(name = ApplicationConstants.THEMIS_ISC_KEY_NAME,type = SecuritySchemeType.APIKEY,in = SecuritySchemeIn.HEADER )
public class Themis {

	public static void main(String[] args) {
		SpringApplication.run(Themis.class, args);
	}

}

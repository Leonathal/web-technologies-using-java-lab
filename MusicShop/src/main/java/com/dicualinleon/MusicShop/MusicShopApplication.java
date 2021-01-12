package com.dicualinleon.MusicShop;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import com.dicualinleon.MusicShop.service.products.GuitarService;
import com.dicualinleon.MusicShop.utils.GuitarTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(MusicShopApplication.class, args);

		GuitarDto guitar1 = GuitarDto.builder()
				.name("Gibson SGX 1")
				.price(250)
				.description("Les Paul Gibson")
				.guitarType(GuitarTypes.ACOUSTIC)
				.build();
	}

}

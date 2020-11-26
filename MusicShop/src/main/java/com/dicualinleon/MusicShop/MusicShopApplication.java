package com.dicualinleon.MusicShop;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.utils.GuitarTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicShopApplication.class, args);

		Guitar guitar1 = Guitar.builder()
				.name("Gibson SGX 1")
				.price(250)
				.description("Les Paul Gibson")
				.type(GuitarTypes.ACOUSTIC)
				.build();
	}

}

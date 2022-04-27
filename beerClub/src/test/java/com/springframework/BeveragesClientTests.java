package com.springframework;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springframework.client.BeerClient;
import com.springframework.model.BeerDao;

@SpringBootTest
class BeveragesClientTests {

	@Autowired
	private BeerClient beerClient;
	
	@Test
	public void testGetBeerById() {
		BeerDao beerDao=beerClient.getBeerById(UUID.randomUUID());
		
		assertThat(beerDao,is(notNullValue()));
	}
	
	@Test
	public void testPostBeer() {
		BeerDao beerDao=beerClient.postBeerById(BeerDao.builder().beerName("Tuborg").build());
		assertThat(beerDao,is(notNullValue()));
	}
	
	@Test
	public void testPutBeer() {
		beerClient.putBeerById(BeerDao.builder().beerName("Tuborg").build());
		
	}

}

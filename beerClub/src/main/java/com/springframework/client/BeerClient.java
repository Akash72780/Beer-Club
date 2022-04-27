package com.springframework.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springframework.model.BeerDao;

@Component
@ConfigurationProperties("application.properties")
public class BeerClient {
	
	public final String BEER_PATH_V1="/api/v1/beer/";
	
	private final RestTemplate restTemplate;
	
	public BeerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}
	
	@Value("${beer.apiHost}")
	private String apihost;
	
	public BeerDao getBeerById(UUID id) {
		BeerDao beerDao=null;
		try {
			beerDao=restTemplate.getForObject(apihost+BEER_PATH_V1+id.toString() , BeerDao.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return beerDao;
	}
	
	public BeerDao postBeerById(BeerDao beerDao) {
		return restTemplate.postForObject(apihost+BEER_PATH_V1, beerDao, BeerDao.class);
	}
	
	public void putBeerById(BeerDao beerDao) {
		restTemplate.put(apihost+BEER_PATH_V1+UUID.randomUUID().toString(), beerDao, BeerDao.class);
	}
	
	
}

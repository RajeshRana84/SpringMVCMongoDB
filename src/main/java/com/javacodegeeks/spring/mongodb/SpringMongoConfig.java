package com.javacodegeeks.spring.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "database";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		Mongo mongo = new MongoClient();
		mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongo;
	}

}

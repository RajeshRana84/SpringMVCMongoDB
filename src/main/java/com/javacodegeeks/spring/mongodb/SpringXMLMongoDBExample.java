package com.javacodegeeks.spring.mongodb;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class SpringXMLMongoDBExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		try {
			MongoOperations mongoOperation = (MongoOperations) ctx
					.getBean("mongoTemplate");

			Company abcCompany = new Company("ABC");
			mongoOperation.save(abcCompany);			

			Company xyzCompany = new Company("XYZ");
			mongoOperation.save(xyzCompany);

			Employee empJoe = new Employee("01", "Joe", abcCompany);
			Employee empSam = new Employee("02", "Sam", abcCompany);
			Employee empJohn = new Employee("03", "John", xyzCompany);

			mongoOperation.save(empJoe);
			mongoOperation.save(empSam);
			mongoOperation.save(empJohn);

			System.out.println("Employees added: " + empJoe + ", " + empSam
					+ ", " + empJohn);

			Query searchUserQuery = new Query(Criteria.where("company").is(
					abcCompany));

			List<Employee> abcEmployees = mongoOperation.find(searchUserQuery,
					Employee.class);
			System.out.println("ABC Employees : " + abcEmployees);

			System.out.println("Update John's company to " + xyzCompany);

			mongoOperation.updateFirst(
					new Query(Criteria.where("company").is(xyzCompany)),
					Update.update("company", abcCompany), Employee.class);

			abcEmployees = mongoOperation.find(searchUserQuery, Employee.class);
			System.out.println("ABC Employees after update: " + abcEmployees);

			System.out.println("Remove all employees");

			mongoOperation.remove(
					new Query(Criteria.where("company").is(abcCompany)),
					Employee.class);

			abcEmployees = mongoOperation.find(searchUserQuery, Employee.class);
			System.out.println("ABC Employees after remove: "
					+ abcEmployees.size());

		} finally {
			ctx.close();
		}
	}
}

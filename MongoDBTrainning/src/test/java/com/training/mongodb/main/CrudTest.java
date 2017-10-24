package com.training.mongodb.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.mongodb.domain.Domain;
import com.training.mongodb.repository.DomainRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {

	@Autowired
	DomainRepository domainRepo;

	@Test
	public void whenInsertingTwo_findFirstByDomain() {
		domainRepo.deleteAll();
		// given
		Domain domain = new Domain();
		domain.setId(1);
		domain.setDomain("123");
		domain.setDisplayAds(true);
		Domain domain1 = new Domain();
		domain1.setId(2);
		domain1.setDomain("125");
		domain1.setDisplayAds(false);

		// when
		domainRepo.save(domain);
		domainRepo.save(domain1);

		// then
		assertEquals(true, domainRepo.findFirstByDomain("123").isDisplayAds());
	}

	@Test
	public void whenInsertingTwo_findByDomain() {
		domainRepo.deleteAll();
		// given
		Domain domain = new Domain();
		domain.setId(4);
		domain.setDomain("123");
		domain.setDisplayAds(true);
		Domain domain1 = new Domain();
		domain1.setId(5);
		domain1.setDomain("1234");
		domain1.setDisplayAds(false);

		// when
		domainRepo.save(domain);
		domainRepo.save(domain1);

		// then
		assertEquals(domain1, domainRepo.findCustomByDomain("1234"));
	}

	@Test
	public void whenInsertingMany_findByDomainRegex() {
		domainRepo.deleteAll();
		// given
		Domain domain = new Domain();
		domain.setId(6);
		domain.setDomain("123");
		domain.setDisplayAds(true);
		Domain domain1 = new Domain();
		domain1.setId(7);
		domain1.setDomain("1234");
		domain1.setDisplayAds(false);
		Domain domain2 = new Domain();
		domain2.setId(8);
		domain2.setDomain("123456");
		domain2.setDisplayAds(false);

		// when
		domainRepo.save(domain);
		domainRepo.save(domain1);
		domainRepo.save(domain2);

		// then
		assertEquals(domain2, domainRepo.findCustomByRegExDomain("45").get(0));
	}

	@Test
	public void whenInsertingManyAndDeletingThen_findNone() {
		domainRepo.deleteAll();
		// given
		Domain domain = new Domain();
		domain.setId(9);
		domain.setDomain("123");
		domain.setDisplayAds(true);
		Domain domain1 = new Domain();
		domain1.setId(11);
		domain1.setDomain("1234");
		domain1.setDisplayAds(false);
		Domain domain2 = new Domain();
		domain2.setId(12);
		domain2.setDomain("123456");
		domain2.setDisplayAds(false);

		// when
		domainRepo.save(domain);
		domainRepo.save(domain1);
		domainRepo.save(domain2);

		domainRepo.deleteAll();

		// then
		assertEquals(0, domainRepo.findAll().size());
	}

	@Test
	public void whenInsertingManyAndEditing_findTheValueUpdated() {
		domainRepo.deleteAll();
		// given
		Domain domain = new Domain();
		domain.setId(9);
		domain.setDomain("123");
		domain.setDisplayAds(true);
		Domain domain1 = new Domain();
		domain1.setId(11);
		domain1.setDomain("1234");
		domain1.setDisplayAds(false);
		Domain domain2 = new Domain();
		domain2.setId(12);
		domain2.setDomain("123456");
		domain2.setDisplayAds(false);

		// when
		domainRepo.save(domain);
		domainRepo.save(domain1);
		domainRepo.save(domain2);

		domain2.setDisplayAds(true);

		domainRepo.save(domain2);

		// then
		assertEquals(true, domainRepo.findFirstByDomain("123456").isDisplayAds());
	}

}

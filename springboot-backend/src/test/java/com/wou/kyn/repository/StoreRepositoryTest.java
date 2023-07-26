package com.wou.kyn.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.wou.kyn.entity.Store;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EnableJpaAuditing
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreRepositoryTest {

	@Autowired
	private StoreRepository storeRepository;
	
	@Test
	public void testCreateStore() {
		// Store details
		String storeName = "Econsave Kuala Terengganu";
		String contactNumber = "07-386 4913";
		String openingHours = "8am - 10pm daily";
		String address = "Lot 4061, PN 2627, Kompleks, Taman Selera Tanjung, 20200 Kuala Terengganu, Terengganu";
		
		// Create store
		Store store = new Store(storeName, contactNumber, openingHours, address);
		store.setCreatedBy(1L);
		store.setUpdatedBy(1L);

		// Save the store to database
		Store storeToCreate = storeRepository.save(store);
		
		// Retrieve the store from database
		Store storeSaved = storeRepository.findById(storeToCreate.getStoreId()).get();
		
		// Verify that the store record is successfully saved
		assertThat(storeToCreate.getStoreId()).isEqualTo(storeSaved.getStoreId());
	}
}

package com.wou.kyn.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wou.kyn.repository.StoreRepository;
import com.wou.kyn.repository.UserRepository;
import com.wou.kyn.util.StoreModelMapper;
import com.wou.kyn.entity.Store;
import com.wou.kyn.entity.User;
import com.wou.kyn.exception.ResourceNotFoundException;
import com.wou.kyn.payload.request.StoreRequest;
import com.wou.kyn.payload.response.StoreResponse;

@Service
@Transactional
public class StoreService {
	@Autowired
	private StoreRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreModelMapper modelMapper;

	/**
	 * Get all the stores
	 * 
	 * @return the list of the store responses
	 */
	public List<StoreResponse> getAllStores() {
		List<Store> stores = repository.findAll();
		Map<Long, User> creatorMap = getStoreCreatorMap(stores);
		Map<Long, User> updaterMap = getStoreUpdaterMap(stores);

		return modelMapper.convertToStoreResList(stores, creatorMap, updaterMap);
	}

	/**
	 * Get a store by its ID
	 * 
	 * @param storeId the ID of the store to be found
	 */
	public StoreResponse getByStoreId(Long storeId) {
		Store store = repository.findById(storeId)
				.orElseThrow(() -> new ResourceNotFoundException("Store", "id", storeId));
		
	    User creator = userRepository.findById(store.getCreatedBy())
	    		.orElseThrow(() -> new ResourceNotFoundException("User", "userId", store.getCreatedBy()));

	    User updater = userRepository.findById(store.getUpdatedBy())
	    		.orElseThrow(() -> new ResourceNotFoundException("User", "userId", store.getUpdatedBy()));

		return modelMapper.convertToStoreRes(store, creator, updater);
	}

	/**
	 * Search stores by keyword (Store Name or Address)
	 * 
	 * @param keyword the searching keyword
	 * @return the relevant store responses found
	 */
	public List<StoreResponse> searchStore(String keyword) {
		List<Store> stores = repository.search(keyword);
		Map<Long, User> creatorMap = getStoreCreatorMap(stores);
		Map<Long, User> updaterMap = getStoreUpdaterMap(stores);

		return modelMapper.convertToStoreResList(stores, creatorMap, updaterMap);
	}

	/**
	 * Creates a new store
	 * 
	 * @param storeRequest the store to be created
	 * @return the created store object
	 */
	public Store createStore(StoreRequest storeRequest) {
		Store store = new Store(storeRequest.getStoreName(), 
				storeRequest.getContactNumber(), 
				storeRequest.getOpeningHours(), 
				storeRequest.getAddress());

		return repository.save(store);
	}

	/**
	 * Update a particular store
	 * 
	 * @param storeId the store id to be updated
	 */
	public StoreResponse updateStore(Long storeId, StoreRequest storeRequest) {
		// Retrieve the existing store
		Store storeToUpdate = repository.findById(storeId)
				.orElseThrow(() -> new ResourceNotFoundException("Store", "id", storeId));

		storeToUpdate.setStoreName(storeRequest.getStoreName());
		storeToUpdate.setContactNumber(storeRequest.getContactNumber());
		storeToUpdate.setOpeningHours(storeRequest.getOpeningHours());
		storeToUpdate.setAddress(storeRequest.getAddress());

		Store store = repository.save(storeToUpdate);
		User creator = userRepository.findById(store.getCreatedBy())
	    		.orElseThrow(() -> new ResourceNotFoundException("User", "userId", store.getCreatedBy()));

		User updater = userRepository.findById(store.getUpdatedBy())
	    		.orElseThrow(() -> new ResourceNotFoundException("User", "userId", store.getUpdatedBy()));
		
		return modelMapper.convertToStoreRes(store, creator, updater);
	}

	/**
	 * Delete an existing store
	 * 
	 * @param storeId the store id to be deleted
	 */
	public void deleteStore(Long storeId) {
		repository.deleteById(storeId);
	}

	/**
	 * To map the user details of the creator to its corresponding stores
	 *
	 * @param stores the list of stores
	 * @return the store creator map
	 */
	private Map<Long, User> getStoreCreatorMap(List<Store> stores) {
        // Get creator details of the given list of stores
        List<Long> creatorIds = stores.stream()
                .map(Store::getCreatedBy)
                .distinct()
                .collect(Collectors.toList());
        
        List<User> creators = userRepository.findByUserIdIn(creatorIds);

		return creators.stream()
				.collect(Collectors.toMap(User::getUserId, Function.identity()));
    }

	/**
	 * To map the user details of the updater to its corresponding stores
	 *
	 * @param stores the list of stores
	 * @return the store updater map
	 */
	private Map<Long, User> getStoreUpdaterMap(List<Store> stores) {
        // Get creator details of the given list of stores
        List<Long> updaterIds = stores.stream()
                .map(Store::getUpdatedBy)
                .distinct()
                .collect(Collectors.toList());
        
        List<User> updaters = userRepository.findByUserIdIn(updaterIds);

		return updaters.stream()
				.collect(Collectors.toMap(User::getUserId, Function.identity()));
    }
}

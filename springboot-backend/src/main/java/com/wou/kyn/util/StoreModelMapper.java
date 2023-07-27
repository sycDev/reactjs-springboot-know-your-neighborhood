package com.wou.kyn.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wou.kyn.entity.Store;
import com.wou.kyn.entity.User;
import com.wou.kyn.payload.response.StoreResponse;
import com.wou.kyn.payload.response.UserSummary;

@Component
public class StoreModelMapper {
	public List<StoreResponse> convertToStoreResList(List<Store> fromList, 
			Map<Long, User> creatorMap, Map<Long, User> updaterMap) {

		List<StoreResponse> storeResponses = new ArrayList<>();

		for (Store store : fromList) {
		    Long creatorId = store.getCreatedBy();
		    User creator = creatorMap.get(creatorId);
		    
		    Long updaterId = store.getUpdatedBy();
		    User updater = updaterMap.get(updaterId);

		    // Create a UserSummary object for createdBy field
		    UserSummary createdBySummary = new UserSummary(creator.getUserId(),
					creator.getUsername(),
					creator.getName(),
					creator.getProvider()
			);

		    // Create a UserSummary object for updatedBy field
		    UserSummary updatedBySummary = new UserSummary(updater.getUserId(),
					updater.getUsername(),
					updater.getName(),
					updater.getProvider()
			);

		    // Create a StoreResponse object and map the relevant properties
		    StoreResponse storeResponse = new StoreResponse(store.getStoreId(), 
		    		store.getStoreName(),
					store.getContactNumber(),
					store.getOpeningHours(),
					store.getAddress(),
					store.getCreatedAt(), 
		    		createdBySummary, 
		    		store.getUpdatedAt(), 
		            updatedBySummary);

		    storeResponses.add(storeResponse);
		}

		return storeResponses;
    }
	
	public StoreResponse convertToStoreRes(Store store, User creator, User updater) {
	    // Create a UserSummary object for createdBy field
	    UserSummary createdBySummary = new UserSummary(creator.getUserId(),
	    		creator.getUsername(),
	    		creator.getName(),
	    		creator.getProvider()
		);
	    
	    // Create a UserSummary object for updatedBy field
	    UserSummary updatedBySummary = new UserSummary(updater.getUserId(),
				updater.getUsername(),
				updater.getName(),
				updater.getProvider()
		);

	    // Return a StoreResponse object that map the relevant properties
		return new StoreResponse(store.getStoreId(),
				store.getStoreName(),
				store.getContactNumber(),
				store.getOpeningHours(),
				store.getAddress(),
				store.getCreatedAt(),
				createdBySummary,
				store.getUpdatedAt(),
				updatedBySummary);
	}
}

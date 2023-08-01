package com.wou.kyn.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wou.kyn.entity.Store;
import com.wou.kyn.payload.request.StoreRequest;
import com.wou.kyn.payload.response.ApiResponse;
import com.wou.kyn.payload.response.StoreResponse;
import com.wou.kyn.service.StoreService;

@RestController
@RequestMapping("/api/stores")
public class StoreRestController {
	@Autowired
	StoreService storeService;

	/**
	 * Retrieve all stores
	 * 
	 * GET /api/stores
	 */
	@GetMapping
	ResponseEntity<List<StoreResponse>> getAll() {
		return ResponseEntity.ok(storeService.getAllStores());
	}

	/**
	 * Retrieve single store by its id
	 * 
	 * GET /api/stores/{id}
	 */
	@GetMapping("/{id}")
	ResponseEntity<StoreResponse> getById(@PathVariable("id") Long storeId) {
		return ResponseEntity.ok(storeService.getByStoreId(storeId));
	}

	/**
	 * Search stores by keyword (Store Name or Address)
	 * 
	 * GET /api/stores/search?q={keyword}
	 */
	@GetMapping("/search")
	ResponseEntity<List<StoreResponse>> search(@RequestParam("q") String query) {
		return ResponseEntity.ok(storeService.searchStore(query));
	}

	/**
	 * Create a new store
	 * 
	 * POST /api/stores
	 */
	@PostMapping
	ResponseEntity<?> create(@Valid @RequestBody StoreRequest storeRequest) {
		Store store = storeService.createStore(storeRequest);

		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{storeId}")
                .buildAndExpand(store.getStoreId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Store successfully created"));
	}
	
	/**
	 * Update an existing store
	 * 
	 * PUT /api/stores/update/{id}
	 */
	@PutMapping("/update/{id}")
	ResponseEntity<StoreResponse> update(@PathVariable("id") Long storeId, @Valid @RequestBody StoreRequest storeRequest) {
		return ResponseEntity.ok(storeService.updateStore(storeId, storeRequest));
	}

	/**
	 * Delete an existing store
	 * 
	 * DELETE /api/store/delete/{id}
	 */
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	ResponseEntity<ApiResponse> delete(@PathVariable("id") Long storeId) {
		storeService.deleteStore(storeId);

		return ResponseEntity.ok().body(new ApiResponse(true, "Store successfully deleted"));
	}
}

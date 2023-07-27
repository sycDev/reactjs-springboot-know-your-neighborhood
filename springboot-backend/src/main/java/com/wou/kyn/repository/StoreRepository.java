package com.wou.kyn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wou.kyn.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	/**
	 * Searches for stores based on a keyword 
	 * (Store name or address) 
	 * 
	 * @param keyword the keyword to search for
	 * @return a list of stores matching the keyword
	*/
	@Query("SELECT s FROM Store s "
			+ "WHERE (s.storeName LIKE CONCAT('%', :keyword, '%') OR "
            + "s.address LIKE CONCAT('%', :keyword, '%')) AND "
            + "TRIM(:keyword) != '' ")
	List<Store> search(@Param("keyword") String keyword);
}

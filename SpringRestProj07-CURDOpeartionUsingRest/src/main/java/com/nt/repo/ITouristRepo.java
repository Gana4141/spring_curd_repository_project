package com.nt.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.Tourist;

import jakarta.transaction.Transactional;
public interface ITouristRepo extends JpaRepository<Tourist, Integer>
{
	@Query("from Tourist where city in (:city1,:city2,:city3)order by name asc")
	public List<Tourist> findTouristByCities(String city1,String city2,String city3);
	
	@Query("from Tourist where name=:name")
		public List<Tourist> getTouristByName(@Param("name")String name);
	
	@Modifying
	@Transactional
	@Query("delete from Tourist t where t.budget >= :start and t.budget <= :end")
	int deleteByBudgetRange(@Param("start") double start,
	                        @Param("end") double end);
	
	
	
	
	
	
	
}

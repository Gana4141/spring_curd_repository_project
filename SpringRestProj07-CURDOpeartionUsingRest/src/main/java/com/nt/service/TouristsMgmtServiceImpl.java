package com.nt.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Tourist;
import com.nt.repo.ITouristRepo;
@Service("touristService")
public class TouristsMgmtServiceImpl implements ITouristMgmtService
{
	@Autowired
	private ITouristRepo touristRepo;

	@Override
	public String registerTourist(Tourist tourist)
	{
	int idVales=touristRepo.save(tourist).getId();
	return "Tourist is registered having the id vales is :"+idVales;
	}
	@Override
	public List<Tourist> fecthAllTourist()
	{
		
		return touristRepo.findAll();
	}
    @Override
    public List<Tourist> showAllTourist(String city1, String city2, String city3) 
    {
    	
    	return touristRepo.findTouristByCities(city1, city2, city3);
    }
   
    
    
    
    @Override
    public Tourist fetchByTouristById(Integer tid) 
    {
    	 Optional<Tourist> opt = touristRepo.findById(tid);
         if(opt.isPresent())
             return opt.get();
         else
             throw new IllegalArgumentException("Tourist not found with id : " + tid);
    }
    
    @Override
    public String updateTouristDetails(Tourist tourist)
    {
        Tourist existingTourist = touristRepo.findById(tourist.getId())
                .orElseThrow(() -> 
                    new IllegalArgumentException(
                        "Tourist not found with id : " + tourist.getId()));
	
        touristRepo.save(tourist);

        return "Tourist updated successfully with id : " + tourist.getId();
    }
    
    
    @Override
    public List<Tourist> findAllByTouristName(String name) 
    {
    	
    	return touristRepo.getTouristByName(name);
    }
    
    
    
    @Override
    public String removeTouristById(int id) 
    {
    	Optional<Tourist> opt = touristRepo.findById(id);
    	if (opt.isPresent())
    	{
    		touristRepo.deleteById(id);
    	}
    	return id+"ID Tourist found & Deleted";
    }
     @Override
    public String removeTouristByBudgetRange(double start, double end)
     {
    	 int count =touristRepo.deleteByBudgetRange(start, end);
    	 
    	return count==0? "Tourist not found for deletion":count+"no.of Tourist are found & deleted";
    }
    
    
    
    
}
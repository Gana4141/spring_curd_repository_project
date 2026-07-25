package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Tourist;
import com.nt.service.ITouristMgmtService;

@RestController
@RequestMapping("/tourist")
public class TouristOperationController 
{
    @Autowired
    private ITouristMgmtService service;

    // Register Tourist
    @PostMapping("/register")
    public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist) 
    {
        try {
            String resultMsg = service.registerTourist(tourist);
            return new ResponseEntity<>(resultMsg, HttpStatus.CREATED);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "Problem in Tourist Enrollment",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Fetch All Tourists
    @GetMapping("/findAll")
    public ResponseEntity<?> displayTourists() 
    {
        try 
        {
            List<Tourist> list = service.fecthAllTourist();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "Problem in Fetching Tourists",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Fetch Tourist By Id
    @GetMapping("/find/{id}")
    public ResponseEntity<?> displayTouristById(@PathVariable("id") Integer tid)
    {
        try
        {
            Tourist tourist = service.fetchByTouristById(tid);
            return new ResponseEntity<>(tourist, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "Tourist not found with id : " + tid,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/modify")
    public ResponseEntity<String> modifyTourist(@RequestBody Tourist tourist)
    {
        try 
        {
            String msg = service.updateTouristDetails(tourist);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "Problem in updating Tourist details",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/findWithName/{name}")
    public ResponseEntity<?> findTouristByName(	@PathVariable("name")String name)
    {
    	try 
    	{
    			List<Tourist> tourists =service.findAllByTouristName(name);
    			return new ResponseEntity<List<Tourist>>(tourists,HttpStatus.OK);
    	}
    	catch (Exception e)
    	{
    		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    		
    	}
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTouristById(@PathVariable("id") Integer id) 
    {
        try 
        {
            String msg = service.removeTouristById(id);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } 
        catch (Exception e) 
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @DeleteMapping("/delete/{start}/{end}")
    public ResponseEntity<?> deleteTouristByBudgetRange(@PathVariable ("start")double start,@PathVariable ("end")double end)
    {
    	try
    	{
    		String resultMsg=service.removeTouristByBudgetRange(start, end);
    		return new ResponseEntity<String> (resultMsg,HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		return new ResponseEntity<String> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    
    
    
    
    
    
    
}
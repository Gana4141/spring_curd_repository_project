package com.nt.service;

import java.util.List;

import com.nt.entity.Tourist;

public interface ITouristMgmtService 
{
public String registerTourist(Tourist tourist);
public List<Tourist> fecthAllTourist();
public List<Tourist> showAllTourist(String city1,String city2,String city3);
public Tourist fetchByTouristById(Integer tid) ;
public String updateTouristDetails(Tourist tourist);
public List<Tourist> findAllByTouristName(String name);
public String removeTouristById(int id); 	
public String removeTouristByBudgetRange(double start,double end);
}

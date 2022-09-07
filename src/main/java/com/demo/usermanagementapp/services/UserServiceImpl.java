package com.demo.usermanagementapp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.usermanagementapp.bindings.LoginForm;
import com.demo.usermanagementapp.entities.CityMasterEntity;
import com.demo.usermanagementapp.entities.CountryMasterEntity;
import com.demo.usermanagementapp.entities.StateMasterEntity;
import com.demo.usermanagementapp.entities.UserAccountEntity;
import com.demo.usermanagementapp.repositories.CityRepository;
import com.demo.usermanagementapp.repositories.CountryRepository;
import com.demo.usermanagementapp.repositories.StateRepository;
import com.demo.usermanagementapp.repositories.UserAccountRepository;



@Service
public class UserServiceImpl implements UserServiceI {
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	

	@Override
	public String loginCheck(LoginForm loginForm) {
		UserAccountEntity userAccountEntity = userAccountRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		if(userAccountEntity != null) {
			String accStatus = userAccountEntity.getAccStatus();
			if(accStatus.equals("LOCKED")) {
				return "Your Account Is Locked";
			}else {
				return "Login successful Welcome to Bikkad IT";
			}
		}else {
			return "Invalid Creditionals.. ";
		}
	}


	@Override
	public Map<Integer, String> getCountries() {
		List<CountryMasterEntity> findAll = countryRepository.findAll();
		Map<Integer, String> countryMap = new HashMap<Integer, String>();
		for(CountryMasterEntity cme : findAll) {
			countryMap.put(cme.getCountryId(), cme.getCountryName());
		}
		return countryMap;
	}
	
	@Override
	public Map<Integer, String> getStates(Integer countryId){
		List<StateMasterEntity> states = stateRepository.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<Integer, String>();
		for(StateMasterEntity sme : states) {
			stateMap.put(sme.getStateId(), sme.getStateName());
		}
		return stateMap;
	}


	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityMasterEntity> cities = cityRepository.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<Integer,String>();
		for(CityMasterEntity cme : cities) {
			cityMap.put(cme.getCityId(), cme.getCityName());
		}
		return cityMap;
	}
	

}

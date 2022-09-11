package com.demo.usermanagementapp.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.usermanagementapp.bindings.LoginForm;
import com.demo.usermanagementapp.bindings.UnlockAccountForm;
import com.demo.usermanagementapp.bindings.UserRegForm;
import com.demo.usermanagementapp.entities.CityMasterEntity;
import com.demo.usermanagementapp.entities.CountryMasterEntity;
import com.demo.usermanagementapp.entities.StateMasterEntity;
import com.demo.usermanagementapp.entities.UserAccountEntity;
import com.demo.usermanagementapp.repositories.CityRepository;
import com.demo.usermanagementapp.repositories.CountryRepository;
import com.demo.usermanagementapp.repositories.StateRepository;
import com.demo.usermanagementapp.repositories.UserAccountRepository;
import com.demo.usermanagementapp.utils.AppConstants;
import com.demo.usermanagementapp.utils.AppProps;
import com.demo.usermanagementapp.utils.EmailUtils;

import net.bytebuddy.utility.RandomString;



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
	@Autowired
	private EmailUtils emailUtils;
	@Autowired
	private AppProps appProps;
	
	@Override
	public String loginCheck(LoginForm loginForm) {
		UserAccountEntity userAccountEntity = userAccountRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		Map<String, String> messages = appProps.getMessages();
		if(userAccountEntity != null) {
			String accStatus = userAccountEntity.getAccStatus();
			if(accStatus.equals(AppConstants.LOCKED)) {
				return AppConstants.ACCOUNT_LOCKED;
			}else {
				return /* AppConstants.LOGIN_SUCCESS */ messages.get("loginSucces");
			}
		}else {
			return AppConstants.INVALID_CRED;
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


	@Override
	public boolean saveUser(UserRegForm userRegForm) {

		userRegForm.setAccStatus(AppConstants.LOCKED);
		userRegForm.setPassword(generateRandomPassword());
		UserAccountEntity userAccountEntity = new UserAccountEntity();
		BeanUtils.copyProperties(userRegForm, userAccountEntity);
		UserAccountEntity save = userAccountRepository.save(userAccountEntity);
		if(save != null) {
			// send mail
			String subject = AppConstants.UNLOCK_CHECK_MAIL;/* "Please check Your Mail To Unlock Account"; */
			String body = getUserRegEmailBody(userRegForm);
			emailUtils.sendMail(userRegForm.getEmail(), subject, body);
			return true;
		}
		return false;
	}
	
	// method for mail configuration to unlock account
	
	private String getUserRegEmailBody(UserRegForm userRegForm){
		StringBuffer sb = new StringBuffer();
		String filename = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		List<String> lines = new ArrayList();
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get(filename));
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lines.forEach(line -> {
			if(line.contains("{FNAME}")) {
				line.replace("{FNAME}", userRegForm.getFname());
			}
			if(line.contains("{LNAME}")) {
				line.replace("{LNAME}", userRegForm.getLname());
			}
			if(line.contains("{TEMP-PWD}")) {
				line.replace("{TEMP-PWD}", userRegForm.getPassword());
			}
			if(line.contains("{EMAIL}")) {
				line.replace("{EMAIL}", userRegForm.getEmail());
			}
			sb.append(line);
		});
		return lines.toString();
		
	// method for generating random password for new user	
	}
	private String generateRandomPassword() {
		String randomPassword = RandomStringUtils.randomAlphanumeric(6);
		return randomPassword;
	}


	@Override
	public boolean unLockAccount(UnlockAccountForm unlockAccountForm) {
		String email = unlockAccountForm.getEmail();
		String tempPassword = unlockAccountForm.getTempPassword();
		
		UserAccountEntity user = userAccountRepository.findByEmailAndPassword(email, tempPassword);
		if(user !=null) {
			user.setAccStatus(AppConstants.UNLOCKED);
			user.setPassword(unlockAccountForm.getNewPassword());
			userAccountRepository.save(user);
			return true;
		}else {
			
		}
		return false;
	}


	@Override
	public String forgotPassword(String email){
		UserAccountEntity user = userAccountRepository.findByEmail(email);
		if(user!=null) {
			String subject = AppConstants.RESET_CHECK_MAIL;/*"Password is sent to you email id, Check your mail"*/
			String body = getForgotPasswordEmail(user);
			emailUtils.sendMail(email, subject, body);
			return "Success";
		}else {
			return "Failed";
		}
		
	}
	
	// method for forgot password mail configuration
	
	private String getForgotPasswordEmail(UserAccountEntity userRegForm){
		StringBuffer sb = new StringBuffer();
		String filename = "RECOVER-PASS-EMAIL-BODY-TEMPLATE.txt";
		List<String> lines = new ArrayList<>();
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get(filename));
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lines.forEach(line -> {
			if(line.contains("{FNAME}")) {
				line.replace("{FNAME}", userRegForm.getFname());
			}
			if(line.contains("{LNAME}")) {
				line.replace("{LNAME}", userRegForm.getLname());
			}
			if(line.contains("{PWD}")) {
				line.replace("{PWD}", userRegForm.getPassword());
			}
			
			sb.append(line);
		});
		return lines.toString();
		
	}
	
	

}

package com.bridgelabz.fundooapp.services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.LabelDto;
import com.bridgelabz.fundooapp.model.LabelInformation;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.repository.LabelRepository;
import com.bridgelabz.fundooapp.repository.UserRepository;
import com.bridgelabz.fundooapp.util.JwtGenerator;

@Service
public class LabelServiceImplementation implements LabelService {
	@Autowired
	private LabelRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LabelInformation labelInformation;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInformation user;

	@Autowired
	private JwtGenerator tokenGenerator;
	
	

	@Transactional
	@Override
	public void createLabel(LabelDto label, String token) {
		 Long id = null;

		try {
		 id = (long) tokenGenerator.parseJWT(token);
		} catch (Exception e) {

			throw new UserException("user is not present ");
		}

		
		UserInformation user=userRepository.getUserById(id);
		if(user!=null)
		{
		labelInformation = modelMapper.map(label, LabelInformation.class);
		labelInformation.getLabelId();
		labelInformation.getName();
        labelInformation.setUserId(user.getUserId());
		repository.save(labelInformation);
		}
		else
		{
			throw new UserException("note is not present with the given id ");
		}

	}

	@Override
	public void editLabel(LabelDto label) {

	}

	@Override
	public void deleteLabel(LabelDto label) {
	}

}

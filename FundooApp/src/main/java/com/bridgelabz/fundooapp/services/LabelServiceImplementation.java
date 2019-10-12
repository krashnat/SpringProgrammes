package com.bridgelabz.fundooapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.LabelDto;
import com.bridgelabz.fundooapp.model.LabelInformation;
import com.bridgelabz.fundooapp.model.LabelUpdate;
import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.repository.LabelRepository;
import com.bridgelabz.fundooapp.repository.NoteRepository;
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

	@Autowired
	private NoteRepository noteRepository;

	@Transactional
	@Override
	public void createLabel(LabelDto label, String token) {
		Long id = null;

		try {
			id = (long) tokenGenerator.parseJWT(token);
		} catch (Exception e) {

			throw new UserException("user is not present ");
		}

		UserInformation user = userRepository.getUserById(id);
		if (user != null) {
			LabelInformation labelInfo = repository.fetchLabel(user.getUserId(), label.getName());
			if (labelInfo == null) {
				labelInformation = modelMapper.map(label, LabelInformation.class);
				labelInformation.getLabelId();
				labelInformation.getName();
				labelInformation.setUserId(user.getUserId());
				repository.save(labelInformation);
			} else {
				throw new UserException("label is already present");
			}
		} else {
			throw new UserException("note is not present with the given id ");
		}

	}

	@Transactional
	@Override
	public void addLabel(Long labelId, Long noteId, String token) {
		NoteInformation note = noteRepository.findById(noteId);
		LabelInformation label = repository.fetchLabelById(labelId);
		label.getList().add(note);
		repository.save(label);
	}

	@Transactional
	@Override
	public void editLabel(LabelUpdate label, String token) {
		Long id = null;

		try {
			id = (long) tokenGenerator.parseJWT(token);
		} catch (Exception e) {

			throw new UserException("user is not present ");
		}

		UserInformation user = userRepository.getUserById(id);
		if (user != null) {
			LabelInformation labelInfo = repository.fetchLabelById(label.getLabelId());
			if (labelInfo != null) {
				labelInfo.setName(label.getLabelName());
				repository.save(labelInfo);
			} else {
				throw new UserException("label not present with this id");
			}

		} else {
			throw new UserException("user not present ");
		}

	}

	@Transactional
	@Override
	public void deleteLabel(LabelUpdate info, String token) {
		Long id = null;

		try {
			id = (long) tokenGenerator.parseJWT(token);
		} catch (Exception e) {

			throw new UserException("user is not present ");
		}

		UserInformation user = userRepository.getUserById(id);
		if (user != null) {
			LabelInformation labelInfo = repository.fetchLabelById(info.getLabelId());
			if (labelInfo != null) {
				repository.deleteLabel(info.getLabelId());
			} else {
				throw new UserException("note not present ");
			}
		}

	}

	@Override
	public List<LabelInformation> getLabel(Long id) {
		/*
		 * Long id; try { id = (long) tokenGenerator.parseJWT(token); } catch (Exception
		 * e) {
		 * 
		 * throw new UserException("note not present "); }
		 */
		List<LabelInformation> labels=repository.getAllLabel(id);
		return labels;
		
	}

}

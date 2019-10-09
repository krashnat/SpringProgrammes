package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.LabelDto;
import com.bridgelabz.fundooapp.model.LabelUpdate;

public interface LabelService {
	void createLabel(LabelDto label,String token);
	void editLabel(LabelUpdate label, String userid);
	void deleteLabel(LabelUpdate info, String token);
	void addLabel(Long labelId, Long noteId, String token);

}

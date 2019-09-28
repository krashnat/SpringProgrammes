package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.LabelDto;

public interface LabelService {
	
	void editLabel(LabelDto label);
	void deleteLabel(LabelDto label);
	void createLabel(LabelDto label,String token);

}

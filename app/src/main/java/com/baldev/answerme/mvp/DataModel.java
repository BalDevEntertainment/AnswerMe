package com.baldev.answerme.mvp;

import com.baldev.answerme.model.DTOs.Tweet;

import java.util.List;

public interface DataModel {

	void storeDataToRetain(List<Tweet> retainedTweets, String lastSearch);

}

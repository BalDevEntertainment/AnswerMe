package com.baldev.answerme.mvp;

import com.baldev.answerme.model.DTOs.SearchResponse;
import com.baldev.answerme.model.DTOs.Tweet;
import com.baldev.answerme.model.DTOs.TwitterToken;

import java.util.List;

import rx.Observable;
import rx.Single;

public interface DataModel {

	Single<TwitterToken> retrieveStoredAccessToken();

	Single<TwitterToken> authenticate();

	Observable<SearchResponse> getTweetsBySearchTerm(String queryTerm);

	void saveAccessToken(String accessToken);

	void storeDataToRetain(List<Tweet> retainedTweets, String lastSearch);

	boolean needsUpdate(String searchTerm);
}

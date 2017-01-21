package com.baldev.answerme.model;

import android.content.Context;

import com.baldev.answerme.model.DTOs.SearchResponse;
import com.baldev.answerme.model.DTOs.Tweet;
import com.baldev.answerme.model.DTOs.TwitterAuthentication;
import com.baldev.answerme.model.DTOs.TwitterAuthentication.GrantType;
import com.baldev.answerme.model.DTOs.TwitterToken;
import com.baldev.answerme.model.helpers.PreferencesManager;
import com.baldev.answerme.model.helpers.SharedPreferencesManager;
import com.baldev.answerme.model.helpers.TwitterAPIHelper;
import com.baldev.answerme.model.helpers.TwitterService;
import com.baldev.answerme.mvp.DataModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;

@Singleton
public class DataManager implements DataModel {

	//TODO inject dependencies
	private static TwitterService twitterService = TwitterAPIHelper.getInstance().create(TwitterService.class);
	private static PreferencesManager preferencesManager = SharedPreferencesManager.getInstance();

	private List<Tweet> retainedTweets;
	private String lastSearch = "";

	private Context context;

	@Inject
	public DataManager(Context context) {
		this.context = context;
	}

	@Override
	public Single<TwitterToken> retrieveStoredAccessToken() {
		String accessToken = preferencesManager.getAccessToken(this.context);
		TwitterToken value = TwitterToken.bearerToken(accessToken);
		return Single.just(value);
	}

	@Override
	public Observable<SearchResponse> getTweetsBySearchTerm(String searchTerm) {
		if (isSearchEmpty(searchTerm)) {
			return Observable.just(SearchResponse.getEmptyResponse());
		} else {
			if (isCached(searchTerm)) {
				this.lastSearch = "";
				return Observable.just(new SearchResponse(this.retainedTweets));
			} else {
				String formattedAccessToken = getFormattedAccessToken(this.getAccessToken());
				return twitterService.getTweetsBySearchTerm(formattedAccessToken, searchTerm);
			}
		}
	}

	private boolean isCached(String searchTerm) {
		return searchTerm.equals(this.lastSearch);
	}

	private boolean isSearchEmpty(String searchTerm) {
		return searchTerm == null || searchTerm.equals("");
	}

	@Override
	public Single<TwitterToken> authenticate() {
		TwitterAuthentication tokenAuthentication = new TwitterAuthentication();
		tokenAuthentication.setGrantType(GrantType.CLIENT_CREDENTIALS);
		return twitterService.authenticate(tokenAuthentication.getRequestBody());
	}

	@Override
	public void saveAccessToken(String accessToken) {
		preferencesManager.saveAccessToken(this.context, accessToken);
	}

	@Override
	public void storeDataToRetain(List<Tweet> retainedTweets, String lastSearch) {
		this.retainedTweets = retainedTweets;
		this.lastSearch = lastSearch;
	}

	@Override
	public boolean needsUpdate(String searchTerm) {
		return !isSearchEmpty(searchTerm) && !isCached(searchTerm);
	}

	private String getFormattedAccessToken(TwitterToken accessToken) {
		String tokenType = accessToken.getTokenType().getValue();
		String accessTokenValue = accessToken.getAccessToken();
		return String.format("%s %s", tokenType, accessTokenValue);
	}

	private TwitterToken getAccessToken() {
		//Try to retrieve stored access token.
		return this.retrieveStoredAccessToken()
				.subscribeOn(Schedulers.computation())
				.flatMap(accessToken -> accessToken.getAccessToken() == null ?
								//Not cached, authenticate.
								authenticate()
										//Save access token on the shared preferences.
										.doOnSuccess(twitterToken -> saveAccessToken(twitterToken.getAccessToken()))
								//Return cached access token
								: Single.just(accessToken))
				.toBlocking()
				.value();
	}
}

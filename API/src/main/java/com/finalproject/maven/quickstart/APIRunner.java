package com.finalproject.maven.quickstart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Create a class called APIRunner that takes in a theme and prints out a list of songs and lyrics 
 * based on user search term
 * 
 * @author shiao xiaoxuan wu
 */

public class APIRunner {
	private String userInput;
	private HashMap<String, String> songs;
	/**
	 * constructor method that accepts theme
	 * @param theme
	 * @throws IOException 
	 * @throws UnirestException 
	 */
	public APIRunner(String theme) throws IOException{
		API api = new API("https://genius.p.rapidapi.com/search", "genius.p.rapidapi.com",
				"91c5b6dd1dmsheb1b0094da25a0bp11b7e1jsnd0e5eb663c7e");
		APIReader apiReader = new APIReader();
		//APIRunner apiRunner = new APIRunner();
		try {
			apiReader.lyricsAPI(theme, api.getHost(), api.getRapidapiHost(), api.getRapidapiKey());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		songs = apiReader.lyricsGetter(apiReader.getResponseDetail());
	}
	
	
	/**
	 * This method scans for user input. This method will be connected with user
	 * input in ThemeSelector class
	 * 
	 * @return userInput
	 */
	
	public String userInput() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a word to search for songs and lyrics: ");
		return userInput = in.nextLine();
	}
	
	
	
	/**
	 * @return the songs
	 */
	public HashMap<String, String> getSongs() {
		return songs;
	}


	/**
	 * main for testing purposes
	 * @param args
	 * @throws UnirestException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnirestException, IOException {
//		API api = new API("https://genius.p.rapidapi.com/search", "genius.p.rapidapi.com",
//				"91c5b6dd1dmsheb1b0094da25a0bp11b7e1jsnd0e5eb663c7e");
//		APIReader apiReader = new APIReader();
//		APIRunner apiRunner = new APIRunner(""); //added temp ""
//		apiReader.lyricsAPI(apiRunner.userInput(), api.getHost(), api.getRapidapiHost(), api.getRapidapiKey());
//		apiReader.lyricsGetter(apiReader.getResponseDetail());
		
		APIRunner apiRunner = new APIRunner("love"); //added temp "
		HashMap<String, String> songs = apiRunner.getSongs();
		System.out.println(songs);
	}
}

//call gui to song 
//

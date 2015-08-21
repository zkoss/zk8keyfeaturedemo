package org.zkoss.keyfeature4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.json.JSONValue;

public class UserService {

	private static List<User> users = new ArrayList<User>();

	static {
		// get dummy users from http://api.randomuser.me/
		JSONObject jsonFromUrl = getJSONFromUrl("http://api.randomuser.me/?results=2000");
		int index = 0;
		for (Object user : (JSONArray) jsonFromUrl.get("results")) {
			JSONObject u = (JSONObject) ((JSONObject) user).get("user");
			JSONObject name = (JSONObject) u.get("name");
			users.add(new User(
					(String) u.get("gender"), 
					name.get("first") + " " + name.get("last") + " " + index++,
					(String) u.get("email"), 
					(String) ((JSONObject) u.get("picture")).get("thumbnail")));
		}
	}

	public static List<User> getUsers(int begin, int end) {
		return new ArrayList<User>(users.subList(begin, end));
	}

	public static List<User> getAllUsers() {
		return users;
	}

	public static int getUsersCount() {
		return users.size();
	}

	public static JSONObject getJSONFromUrl(String url) {
		JSONObject jsonArray = null;

		HttpURLConnection httpURLConnection = null;
		try {
			URL u = new URL(url);
			httpURLConnection = (HttpURLConnection) u.openConnection();
			httpURLConnection.setRequestMethod("GET");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line + '\n');
			}
			return (JSONObject) JSONValue.parse(stringBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpURLConnection.disconnect();
		}
		return jsonArray;
	}
}

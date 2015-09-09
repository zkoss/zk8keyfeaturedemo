package org.zkoss.keyfeature4;

import java.util.Map;

public class RestaurantPreview {

	private String _cityInitial;
	private int _numberOfCity;
	private String _cityName;
	private Map<String, Integer> _numberOfType;
	private int _position;

	public String getCityInitial() {
		return _cityInitial;
	}

	public void setCityInitial(String cityInitial) {
		_cityInitial = cityInitial;
	}

	public int getNumberOfCity() {
		return _numberOfCity;
	}

	public void setNumberOfCity(int numberOfCity) {
		_numberOfCity = numberOfCity;
	}

	public String getCityName() {
		return _cityName;
	}

	public void setCityName(String cityName) {
		_cityName = cityName;
	}

	public Map<String, Integer> getNumberOfType() {
		return _numberOfType;
	}

	public void setNumberOfType(Map<String, Integer> numberOfType) {
		_numberOfType = numberOfType;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

}

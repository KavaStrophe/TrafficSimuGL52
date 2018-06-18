package utils;

public class Converter {
	static public float convertKHtoMS(float speedPerKilometerHour)
	{
		float speedPerMeterSecond = (speedPerKilometerHour * 1000) / 3600;
		return speedPerMeterSecond;
	}
}

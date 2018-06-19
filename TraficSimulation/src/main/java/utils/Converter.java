package utils;

public class Converter {
	static public float convertKHtoMS(float speedPerKilometerHour)
	{
		float speedPerMeterSecond = (speedPerKilometerHour * 1000) / 3600;
		return speedPerMeterSecond;
	}

	public static float convertMStoKM(float speedPerMeterSecond) {
		float speedPerKilometerHour = (speedPerMeterSecond * 3600) / 1000;
		return speedPerKilometerHour;
	}
}

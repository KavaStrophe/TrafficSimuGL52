package utils;

/**
 * Convertisseur m/s => km/h et inversement
 * @author Nulboroth
 *
 */
public class Converter {
	/**
	 * Convertie une vitesse en km/h en m/s
	 * @param speedPerKilometerHour Vitesse en Km/h
	 * @return Vitesse en m/s
	 */
	static public float convertKHtoMS(float speedPerKilometerHour)
	{
		float speedPerMeterSecond = (speedPerKilometerHour * 1000) / 3600;
		return speedPerMeterSecond;
	}

	/**
	 * Convertie une vitesse en m/s en km/h
	 * @param speedPerMeterSecond Vitesse en m/s
	 * @return Vitesse en km/h
	 */
	public static float convertMStoKM(float speedPerMeterSecond) {
		float speedPerKilometerHour = (speedPerMeterSecond * 3600) / 1000;
		return speedPerKilometerHour;
	}
}

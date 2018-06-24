package configfile;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import javafx.scene.paint.Color;

/**Json Deserializer using Jackson for CarConfig
 * 
 * @author Nahil
 *
 */
public class CarConfigDeserializer extends StdDeserializer<CarConfig> {

	public CarConfigDeserializer() {
		this(null);
	}

	public CarConfigDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public CarConfig deserialize(JsonParser parser, DeserializationContext deserializer)
			throws IOException, JsonProcessingException {

		ObjectCodec codec = parser.getCodec();
		JsonNode node = codec.readTree(parser);

		CarConfig car = new CarConfig(node.get("name").asText(), node.get("lenght").numberValue().floatValue(),
				node.get("maxSpeed").numberValue().floatValue(), node.get("maxAccel").numberValue().floatValue(),
				node.get("maxDecel").numberValue().floatValue(),
				new Color(node.get("color").get("red").numberValue().doubleValue(),
						node.get("color").get("green").numberValue().doubleValue(),
						node.get("color").get("blue").numberValue().doubleValue(),
						node.get("color").get("opacity").numberValue().doubleValue()),
				node.get("agentNumber").numberValue().intValue());

		return car;
	}

}

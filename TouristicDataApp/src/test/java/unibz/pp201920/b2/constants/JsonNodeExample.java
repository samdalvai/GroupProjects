package unibz.pp201920.b2.constants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Enum containing test json nodes.
 * 
 * @author Samuel Dalvai, Gioele De Vitti
 */
public enum JsonNodeExample {
	GENERAL("{\"Some\":{\"value\":3},\"array\":[2,3,-3]}"),

	GENERAL_MALFORMED_CUTOFF("{[[{["),

	ROOT("{\"Items\":[" + "{\"Id\":\"01CFEFF8DA586E548327E539276C42F3\","
			+ "\"Detail\":{\"en\":{\"Title\":\"01 Cross Country Stadio Track"
			+ " Dobbiaco/Toblach\",\"BaseText\":\"Demanding round-trip tours starting"
			+ " at the cross-country stadium in Dobbiaco\"},\"it\":{\"Title\":"
			+ "\"01 Giro dello stadio di Dobbiaco\",\"BaseText\":\"La pista impegnativa"
			+ " che parte dallo stadio di sci da fondo di Dobbiaco \"},\"de\":{\"Title\""
			+ ":\"01 Toblacher Stadionrunde\",\"BaseText\":\"Die anspruchsvolle Rundloipe"
			+ " ab dem Langlaufstadion in Toblach wird\"}},\"GpsInfo\":[{\"Gpstype\":"
			+ "\"Start und Ziel\"}],\"GpsPoints\":{\"position\":{\"Gpstype\":\"Start"
			+ " und Ziel\"}},\"GpsTrack\":[{\"Id\":\"28D640B9B87F8FBA57153F685BCB7A41\"}],"
			+ "\"ODHTags\":[{\"Id\":\"loipen\"},{\"Id\":\"klassisch und skating\"}],"
			+ "\"LocationInfo\":{\"RegionInfo\":{\"Id\":\"D2633A1FC24E11D18F1B006097B8970B\","
			+ "\"Name\":{\"en\":\"Dolomites Region Three Peaks\",\"it\":\"Regione dolomitica"
			+ " Tre Cime\",\"de\":\"Dolomitenregion Drei Zinnen\"}}}},"
			+ "{\"Id\":\"B66FA66DA650717E0964A4E30A716DAE\","
			+ "\"Detail\":{\"en\":{\"Title\":\"02 Cross Country Stadio Dobbiaco/Toblach FISI"
			+ " - Stephanie\",\"BaseText\":\"Demanding round-trip tours starting at the"
			+ " cross-country stadium in Dobbiaco\"},\"it\":{\"Title\":"
			+ "\"02 Stadio sci da fondo Dobbiaco pista FISI - Stephanie\",\"BaseText\":\"La pista impegnativa"
			+ " che parte dallo stadio di sci da fondo di Dobbiaco \"},\"de\":{\"Title\""
			+ ":\"02 Langlaufstadion Toblach Fisloipe - Stephanie\",\"BaseText\":\"Die anspruchsvolle Rundloipe"
			+ " ab dem Langlaufstadion in Toblach wird\"}},\"GpsInfo\":[{\"Gpstype\":"
			+ "\"Start und Ziel\"}],\"GpsPoints\":{\"position\":{\"Gpstype\":\"Start"
			+ " und Ziel\"}},\"GpsTrack\":[{\"Id\":\"6D40ACAE374491DF7975FD6FB8F0B8CA\"}],"
			+ "\"ODHTags\":[{\"Id\":\"loipen\"},{\"Id\":\"klassisch und skating\"}],"
			+ "\"LocationInfo\":{\"RegionInfo\":{\"Id\":\"522822A751CA11D18F1400A02427D15E\","
			+ "\"Name\":{\"en\":\"Three Peaks Dolomites\",\"it\":\"Tre Cime Dolomiti"
			+ " Tre Cime\",\"de\":\"Drei Zinnen Dolomiten\"}}}}" + "]}\""),
	
	ROOT_NO_GPS("{\"Items\":[" + "{\"Id\":\"01CFEFF8DA586E548327E539276C42F1\","
			+ "\"Detail\":{\"en\":{\"Title\":\"01 Cross Country Stadio Track"
			+ " Dobbiaco/Toblach\",\"BaseText\":\"Demanding round-trip tours starting"
			+ " at the cross-country stadium in Dobbiaco\"},\"it\":{\"Title\":"
			+ "\"01 Giro dello stadio di Dobbiaco\",\"BaseText\":\"La pista impegnativa"
			+ " che parte dallo stadio di sci da fondo di Dobbiaco \"},\"de\":{\"Title\""
			+ ":\"01 Toblacher Stadionrunde\",\"BaseText\":\"Die anspruchsvolle Rundloipe"
			+ " ab dem Langlaufstadion in Toblach wird\"}}," + "\"GpsInfo\": null," + "\"GpsPoints\":{}"
			+ ",\"GpsTrack\":[]," + "\"ODHTags\":[{\"Id\":\"loipen\"},{\"Id\":\"klassisch und skating\"}],"
			+ "\"LocationInfo\":{\"RegionInfo\":{\"Id\":\"D2633A1FC24E11D18F1B006097B8970B\","
			+ "\"Name\":{\"en\":\"Dolomites Region Three Peaks\",\"it\":\"Regione dolomitica"
			+ " Tre Cime\",\"de\":\"Dolomitenregion Drei Zinnen\"}}}}" + "]}\""),

	ROOT_MALFORMED_PARENTHESIS("\"Items\":[{\"Id\":\"01CFEFF8DA586E548327E539276C42F1\",\"\r\n" 
			+ "\"Detail\":{\"en\":{\"Title\":\"01 Cross Country Stadio Track\"\r\n"  
			+ " Dobbiaco/Toblach\",\"BaseText\":\"Demanding round-trip tours starting\"\r\n"  
			+ " at the cross-country stadium in Dobbiaco\"},\"it\":{\"Title\":\"\r\n"  
			+ "\"01 Giro dello stadio di Dobbiaco\",\"BaseText\":\"La pista impegnativa\"\r\n"  
			+ " che parte dallo stadio di sci da fondo di Dobbiaco \"},\"de\":{\"Title\"\"\r\n"  
			+ ":\"01 Toblacher Stadionrunde\",\"BaseText\":\"Die anspruchsvolle Rundloipe\"\r\n"  
			+ " ab dem Langlaufstadion in Toblach wird\"}},\" + \"\"GpsInfo\": null,\" + \"\"GpsPoints\":{}\"\r\n"  
			+ ",\"GpsTrack\":[],\" + \"\"ODHTags\":[{\"Id\":\"loipen\"},{\"Id\":\"klassisch und skating\"}],\"\r\n"  
			+ "\"LocationInfo\":{\"RegionInfo\":{\"Id\":\"D2633A1FC24E11D18F1B006097B8970B\",\"\r\n"  
			+ "\"Name\":{\"en\":\"Dolomites Region Three Peaks\",\"it\":\"Regione dolomitica\"\r\n"  
			+ " Tre Cime\",\"de\":\"Dolomitenregion Drei Zinnen\"}}}}]}"),

	ROOT_MALFORMED_ITEMS("{\"Items\":[ {}, {}, {}]}"),

	ACTIVITY_VALID("{\"id\":\"01CFEFF8DA586E548327E539276C42F1\",\"name\":"
			+ "\"01 Cross Country Stadio Track Dobbiaco/Toblach\",\"description\":"
			+ "\"Demanding round-trip tours starting at the cross-country \",\"types\":"
			+ "[\"loipen\",\"klassisch und skating\"],\"hasGPSTrack\":true,\"region\":"
			+ "\"Dolomites Region Three Peaks\"}"),

	ACTIVITY_NONVALID("{\"id\":\"01CFEFF8DA586E548327E539276C42F1\",\"name\":"
			+ "\"01 Cross Country Stadio Track Dobbiaco/Toblach\",\"description\":"
			+ "\"Demanding round-trip tours starting at the cross-country \",\"types\":"
			+ "[\"loipen\",\"klassisch und skating\"],\"hasGPSTrack\":\"This is wrong\",\"region\":"
			+ "\"Dolomites Region Three Peaks\"}"),

	SCHEMA("{\"title\":\"Activity Json Schema\",\"description\":"
			+ "\"Schema used to validate json output file representing Activity objects\","
			+ "\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"string\"},\"name\":"
			+ "{\"type\":\"string\"},\"description\":{\"type\":[\"string\",\"null\"]},"
			+ "\"types\":{\"type\":\"array\",\"minItems\":1,\"uniqueItems\":true,\"items\":"
			+ "{\"type\":\"string\"}},\"hasGPSTrack\":{\"type\":\"boolean\"},\"region\":{\"type\":"
			+ "[\"string\",\"null\"]}},\"required\":[\"id\",\"name\",\"description\",\"types\","
			+ "\"hasGPSTrack\",\"region\"],\"additionalProperties\":false}"),

	SCHEMA_WRONG("{\"title\":\"Activity Json Schema\",\"description\":"
			+ "\"Schema used to validate json output file representing Activity objects\","
			+ "\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"string\"},\"name\":"
			+ "{\"type\":\"string\"},\"description\":{\"type\":[\"string\",\"null\"]},"
			+ "\"types\":{\"type\":\"array\",\"minItems\":1,\"uniqueItems\":true,\"items\":"
			+ "{\"type\":\"string\"}},\"hasGPSTrack\":{\"type\":\"string\"},\"region\":{\"type\":"
			+ "[\"string\",\"null\"]}},\"required\":[\"id\",\"name\",\"description\",\"types\","
			+ "\"hasGPSTrack\",\"region\"],\"additionalProperties\":false}"),

	SCHEMA_SIMPLE("{\"title\":\"Json Schema for testing purposes\","
			+ "\"type\":\"object\",\"properties\":{\"testName\":{\"type\":\"string\"}},\"required\":"
			+ "[\"testName\"],\"additionalProperties\":false}");

	private static final ObjectMapper mapper = new ObjectMapper();
	private final String rawJson;

	JsonNodeExample(String rawJson) {
		this.rawJson = rawJson;
	}

	public String getRaw() {
		return rawJson;
	}

	public JsonNode getNode() {
		try {
			return mapper.readTree(rawJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}


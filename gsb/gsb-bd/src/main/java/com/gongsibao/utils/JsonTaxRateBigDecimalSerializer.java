package com.gongsibao.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;


public class JsonTaxRateBigDecimalSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

		String v = "";
		if (value != null) {

			BigDecimal result = value.divide(new BigDecimal(1), 4, BigDecimal.ROUND_HALF_UP);
			DecimalFormat decimalFormat = new DecimalFormat("0.0000");
			v = decimalFormat.format(result);
		}
		jgen.writeString(v);
	}
}

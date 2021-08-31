package org.TOC.Naajil;

import java.io.IOException;

import org.drools.compiler.compiler.DroolsParserException;

public class main {
    public static void main (String[] args) throws IOException, DroolsParserException {
        sensorInputs allSensorsRawData = new sensorInputs();
        sensorData currentSensorValues = allSensorsRawData.readAllSensors();
        situationModel obj = new situationModel(currentSensorValues);
        obj.execteRule();
    }
}

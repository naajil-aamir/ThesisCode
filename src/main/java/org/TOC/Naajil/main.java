package org.TOC.Naajil;

import java.io.IOException;
import java.util.Random;

import org.drools.compiler.compiler.DroolsParserException;

public class main {
    public static void main (String[] args) throws IOException, DroolsParserException {
        sensorInputs allSensorsRawData = new sensorInputs();
        sensorData currentSensorValues = allSensorsRawData.readAllSensors();
        situationModel obj = new situationModel(currentSensorValues);
        ruleEngine engine = new ruleEngine();
        engine.execteRule(obj);
        mainDecisionModel mainDecisionModelInstance = new mainDecisionModel(obj);
        mainDecisionModelInstance.printMainDecisionModelOutput();
//        System.out.println("Distance to Stop is: " + mainDecisionModelInstance.totalDistanceToStop + "m");
//        System.out.println("The preferred Action is: " + mainDecisionModelInstance.preferredAction);
//        System.out.println("The safe takeover time for this scenario is: " + mainDecisionModelInstance.totalSafeTakeoverTime);
//        Driver code to test predicting modality
//        Random r = new Random();
//        for(int i=0; i < 10; i++){
//            int randomInt1 = r.nextInt(100) + 1;
//            int randomInt2 = r.nextInt(100) + 1;
//            int randomInt3 = r.nextInt(100) + 1;
//            System.out.println("Sight: "+ randomInt1 + " Hearing: " + randomInt2 + " Haptics: " + randomInt3);
//            String[] predicted = mainDecisionModelInstance.predictInformingLowModalityTest(randomInt1,randomInt2,randomInt3);
//            System.out.println("----- Low Modality -----");
//            System.out.println("Visual Feedback: "+ predicted[0]);
//            System.out.println("Auditory Feedback: " + predicted[1]);
//            System.out.println("HapticsFeedback: " + predicted[2]);
//            System.out.println();
//            predicted = mainDecisionModelInstance.predictInformingHighModalityTest(randomInt1,randomInt2,randomInt3);
//            System.out.println("----- High Modality -----");
//            System.out.println("Visual Feedback: "+ predicted[0]);
//            System.out.println("Auditory Feedback: " + predicted[1]);
//            System.out.println("HapticsFeedback: " + predicted[2]);
//            System.out.println();
//        }
    }
}

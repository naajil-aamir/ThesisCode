package org.TOC.Naajil;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class situationModel {
    public humanSenses sensesAvailability = new humanSenses();
    public double frictionCoefficient;
    public int timeToSituationalAwareness;
    public Boolean[][] trafficMap;
    public int speed;
    public int distanceToObstacle;
    public String weatherConditions;
    public String trafficIntensity;
    public String humanActivity;
    public int noiseLevels;
    situationModel(){};
    situationModel(sensorData currentSensorValues){
        sensesAvailability.sight = 100;
        sensesAvailability.hearing = 100;
        sensesAvailability.haptics = 100;
        frictionCoefficient = 0.6;
        timeToSituationalAwareness = 6;
        createTrafficMap(currentSensorValues.trafficSensorInfo);
        speed = currentSensorValues.speed;
        distanceToObstacle = currentSensorValues.distanceToObstacle;
        weatherConditions = currentSensorValues.weatherConditions;
        humanActivity = currentSensorValues.humanActivity;
        noiseLevels = currentSensorValues.noiseLevels;
        trafficIntensity = currentSensorValues.trafficIntensity;
    }
    private void createTrafficMap(Boolean[] traficSensorInfo){
        trafficMap = new Boolean[3][3];
        for(int i=0; i< traficSensorInfo.length;i++){
            trafficMap[i/3][i%3] = traficSensorInfo[i];
        }
        printTrafficMap();
    }

    //Get Functions:
    public int getSight(){
        return sensesAvailability.sight;
    }
    public int getHearing(){
        return sensesAvailability.hearing;
    }
    public int getHaptics(){
        return sensesAvailability.haptics;
    }
    public double getFrictionCoefficient(){
        return  frictionCoefficient;
    }
    public int getTimeToSituationalAwareness(){
        return timeToSituationalAwareness;
    }
    public void printTrafficMap(){
        System.out.println( trafficMap[0][0] + " " + trafficMap[0][1] + " " + trafficMap[0][2]);
        System.out.println( trafficMap[1][0] + " " + trafficMap[1][1] + " " + trafficMap[1][2]);
        System.out.println( trafficMap[2][0] + " " + trafficMap[2][1] + " " + trafficMap[2][2]);
    }

    // Adjust Situational parameters Functions.
    public void reduceSightAvailability(int value){
        sensesAvailability.sight = sensesAvailability.sight - value;
        if(sensesAvailability.sight < 0){
            sensesAvailability.sight = 0;
        }
    }
    public void reduceHearingAvailability(int value){
        sensesAvailability.hearing = sensesAvailability.hearing - value;
        if(sensesAvailability.hearing < 0){
            sensesAvailability.hearing = 0;
        }
    }
    public void reduceHapticAvailability(int value){
        sensesAvailability.haptics = sensesAvailability.haptics - value;
        if(sensesAvailability.haptics < 0){
            sensesAvailability.haptics = 0;
        }
    }
    public void reduceFrictionCoefficient(double value){
        frictionCoefficient = frictionCoefficient - value;
    }
    public void addTimeToSituationalAwareness(int value){
        timeToSituationalAwareness = timeToSituationalAwareness + value;
    }
    public class humanSenses{
        public int sight;
        public int hearing;
        public int haptics;
    }

    public void execteRule() throws DroolsParserException, IOException {
        PackageBuilder builder = new PackageBuilder();
        String ruleFile = "/offers.drl";
        InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);

        Reader ruleReader = new InputStreamReader(resourceAsStream);
        builder.addPackageFromDrl(ruleReader);
        org.drools.core.rule.Package rulePackage = builder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(rulePackage);

        WorkingMemory workingMemory = ruleBase.newStatefulSession();

        PaymentOffer paymentOffer = new PaymentOffer();
        paymentOffer.setChannel("paytm");
        workingMemory.insert(paymentOffer);
        workingMemory.fireAllRules();

        System.out.println("The cashback for this payment channel "+paymentOffer.getChannel()+" is "+paymentOffer.getDiscount());

    }
}


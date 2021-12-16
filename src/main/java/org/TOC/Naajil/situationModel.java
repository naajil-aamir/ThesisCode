package org.TOC.Naajil;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class situationModel {
    public humanSenses sensesAvailability = new humanSenses();
    public double frictionCoefficient;
    public int timeToSituationalAwareness;
    public int numberOfPassengers;
    public Boolean[][] trafficMap;
    public Boolean fixedObstacle;
    public Boolean nightDriving;
    public String obstacleType; //Living or non living
    public boolean highCriticalSituation; // Provided by sensors.
    public Timestamp time;
    public Boolean highWayDriving;
    public Boolean urbanDriving;
    public Boolean ruralDriving;
    public Boolean driverSeatbelt;
    public Boolean frontPassengerSeatbelt;
    public int speed;
    public int distanceToObstacle;
    public String weatherConditions;
    public String trafficIntensity;
    public String humanActivity;
    public String frontPassengerActivity;
    public String rearPassenger1Activity;
    public String rearPassenger2Activity;
    public String rearPassenger3Activity;
    public int noiseLevels;
    public String driverExperience;
    public String humanStressLevel;
    public int angleOfIncline;
    public String trafficHandedness;
    List<String> randomSituations = new ArrayList<String>(Arrays.asList());
    situationModel(){};
    situationModel(sensorData currentSensorValues){
        sensesAvailability.sight = 100;
        sensesAvailability.hearing = 100;
        sensesAvailability.haptics = 100;
        sensesAvailability.cognitiveLoad = 0;
        frictionCoefficient = 0.6;
        timeToSituationalAwareness = 6;
        createTrafficMap(currentSensorValues.trafficSensorInfo);
        speed = currentSensorValues.speed;
        distanceToObstacle = currentSensorValues.distanceToObstacle;
        weatherConditions = currentSensorValues.weatherConditions;
        humanActivity = currentSensorValues.humanActivity;
        noiseLevels = currentSensorValues.noiseLevels;
        trafficIntensity = currentSensorValues.trafficIntensity;
        driverExperience = currentSensorValues.driverExperience;
        humanStressLevel = currentSensorValues.humanStressLevel;
        angleOfIncline = currentSensorValues.angleOfIncline;
        highCriticalSituation = currentSensorValues.highCriticalSituation;
        trafficHandedness = currentSensorValues.trafficHandedness;
//        randomSituations.add("sleep");
//        randomSituations.add("lol");
    }
    private void createTrafficMap(Boolean[] traficSensorInfo){
        trafficMap = new Boolean[3][3];
        for(int i=0; i< traficSensorInfo.length;i++){
            trafficMap[i/3][i%3] = traficSensorInfo[i];
        }
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
    public void printSensorandCarDynamics(){
        System.out.println("=====================================");
        System.out.println("Sensor and Car dynamics");
        System.out.println("=====================================");
        System.out.println("Speed: " + speed);
        System.out.println("Weather Conditions: " + weatherConditions);
        System.out.println("Human Activity: " + humanActivity);
        System.out.println("Driver Experience: " + driverExperience);
        System.out.println("Noise Levels: " + noiseLevels);
        System.out.println("Distance to Obstacle: " + distanceToObstacle);
        System.out.println("Traffic Intensity: " + trafficIntensity);
        System.out.println();
    }
    public void printTrafficMap(){
        System.out.println("=====================================");
        System.out.println("Traffic Map");
        System.out.println("=====================================");
        System.out.println( trafficMap[0][0] + " " + trafficMap[0][1] + " " + trafficMap[0][2]);
        System.out.println( trafficMap[1][0] + " " + trafficMap[1][1] + " " + trafficMap[1][2]);
        System.out.println( trafficMap[2][0] + " " + trafficMap[2][1] + " " + trafficMap[2][2]);
        System.out.println();
    }
    public void printAdjustedValues(){
        System.out.println("=====================================");
        System.out.println("ADJUSTED VALUES");
        System.out.println("=====================================");
        System.out.println("The adjusted friction coefficient is: " + getFrictionCoefficient());
        System.out.println("The adjusted Sight values are: " + getSight());
        System.out.println("The adjusted Hearing values are: " + getHearing());
        System.out.println("The adjusted Haptic values are: " + getHaptics());
        System.out.println("The adjusted Time to situational awareness is: " + getTimeToSituationalAwareness() + "s");
        System.out.println();
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
    public void increaseCognitiveLoad(int value){
        sensesAvailability.cognitiveLoad = sensesAvailability.cognitiveLoad + value;
        if(sensesAvailability.haptics >= 100){
            sensesAvailability.haptics = 100;
        }
    }
    public void setHighCriticalSituation(Boolean value){
        if(highCriticalSituation == false){
            highCriticalSituation = value;
        }
    }
    public void setHighWayDriving(Boolean value){ highWayDriving = value; }
    public void setNumberOfPassengers(int value){ numberOfPassengers = value; }
    public void setFixedObstacle(Boolean value){ fixedObstacle = value; }
    public void setNightDriving(Boolean value){ nightDriving = value; }
    public void setObstacleType(String value){ obstacleType = value; }
    public void setUrbanDriving(Boolean value){ urbanDriving = value; }
    public void setRuralDriving(Boolean value){ ruralDriving = value; }
    public void setDriverSeatbelt(Boolean value){ driverSeatbelt = value; }
    public void setFrontPassengerSeatbelt(Boolean value){ frontPassengerSeatbelt = value; }
    public void setFrontPassengerActivity(String value){ frontPassengerActivity = value; }
    public void setRearPassenger1Activity(String value){ rearPassenger1Activity = value; }
    public void setRearPassenger2Activity(String value){ rearPassenger2Activity = value; }
    public void setRearPassenger3Activity(String value){ rearPassenger3Activity = value; }
    public void reduceFrictionCoefficient(double value){
        frictionCoefficient = frictionCoefficient - value;
    }
    public void addTimeToSituationalAwareness(int value){
        timeToSituationalAwareness = timeToSituationalAwareness + value;
    }
    public void setHeartRate(int value){
        if(sensesAvailability.heartRate != 0) {
            sensesAvailability.heartRate = value;
        }
    }
    public void setLeftEyeGaze(int x, int y, int z){
        if(x!=0 && y!=0 && z!=0) {
            sensesAvailability.gazeLeftX = x;
            sensesAvailability.gazeLeftY = y;
            sensesAvailability.gazeLeftZ = z;
        }
    }
    public void setRightEyeGaze(int x, int y, int z){
        if(x!=0 && y!=0 && z!=0) {
            sensesAvailability.gazeRightX = x;
            sensesAvailability.gazeRightY = y;
            sensesAvailability.gazeRightZ = z;
        }
    }
    public void setSoundLevelsEars(int left, int right){
        if(sensesAvailability.leftEarSoundLevel !=0 && sensesAvailability.rightEarSoundLevel!=0) {
            sensesAvailability.leftEarSoundLevel = left;
            sensesAvailability.rightEarSoundLevel = right;
        }
    }
    public void setAge(int value){
        sensesAvailability.age = value;
    }
    public void setBloodPressure(int systolicBP, int diastolicBP){
        if(systolicBP !=0 && diastolicBP !=0) {
            sensesAvailability.systolicBP = systolicBP;
            sensesAvailability.diastolicBP = diastolicBP;
        }
    }
    public void setBreathsPerMinute(int value){
        if(sensesAvailability.breathsPerMinute != 0) {
            sensesAvailability.breathsPerMinute = value;
        }
    }
    public void setLeftHandOccupancy(boolean handOccupancy){
        sensesAvailability.leftHandOccupied = handOccupancy;
    }
    public void setRightHandOccupancy(boolean handOccupancy){
        sensesAvailability.rightHandOccupied = handOccupancy;
    }
    public void setHeadphonesWorn(boolean headphonesWorn){
        sensesAvailability.headphonesWorn = headphonesWorn;
    }
    public void setSunglasses(boolean sunglassesWorn){
        sensesAvailability.sunglassesWorn = sunglassesWorn;
    }

    public Boolean checkCase(String input) {
       return randomSituations.contains(input);
    }
    public void setAlertness(int value){
        sensesAvailability.alertness = value;
    }
    public void increaseAlertness(int value){
        sensesAvailability.alertness += value;
    }
    public void reduceAlertness(int value){
        sensesAvailability.alertness -= value;
    }
    public void setLeftHandSteering(boolean value){
        sensesAvailability.leftHandOnSteering = value;
    }
    public void setRightHandSteering(boolean value){
        sensesAvailability.rightHandOnSteering = value;
    }


    public void tempPrint(String toBePrinted){
        System.out.println("To be printed: " + toBePrinted);
        System.out.println("To be printed: " + toBePrinted);
        System.out.println("To be printed: " + toBePrinted);
        System.out.println("To be printed: " + toBePrinted);
        System.out.println("To be printed: " + toBePrinted);
        System.out.println("To be printed: " + toBePrinted);
        System.out.println("To be printed: " + toBePrinted);
    }

}


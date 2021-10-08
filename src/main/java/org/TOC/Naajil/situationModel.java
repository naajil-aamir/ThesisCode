package org.TOC.Naajil;

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
    public String driverExperience;
    public String humanStressLevel;
    public int angleOfIncline;
    public String trafficHandedness;
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
        driverExperience = currentSensorValues.driverExperience;
        humanStressLevel = currentSensorValues.humanStressLevel;
        angleOfIncline = currentSensorValues.angleOfIncline;
        trafficHandedness = currentSensorValues.trafficHandedness;
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
}


package org.TOC.Naajil;

public class sensorInputs {
    private sensorData sensorDataValues;

    public sensorInputs(){
        sensorDataValues = new sensorData();
    }

    public sensorData readAllSensors(){
        sensorDataValues.speed = getVehicleSpeedKMH();
        sensorDataValues.distanceToObstacle = getDistanceToObstacle();
        sensorDataValues.weatherConditions = getWeatherConditions();
        sensorDataValues.trafficIntensity = getTrafficIntensity();
        sensorDataValues.trafficSensorInfo = getTrafficSensorInfo();
        sensorDataValues.humanActivity = getHumanActivity();
        sensorDataValues.noiseLevels = getNoiseLevelsDB();
        sensorDataValues.driverExperience = getDriverExperience();
        sensorDataValues.angleOfIncline = getAngleOfIncline();
        sensorDataValues.humanStressLevel = getHumanStressLevel();
        return(sensorDataValues);
    }

    //Get the current speed in KM/H.
    private int getVehicleSpeedKMH(){
        return 100;
    }

    //Get the distance to the obstacle in meters.
    private int getDistanceToObstacle(){
        return 300;
    }

    //Get current weather conditions.
    private String getWeatherConditions(){
        return "Snow";
    }

    //Get current traffic intensity.
    private String getTrafficIntensity(){
        return "High";
    }

    //Get the sensor Info, based on the free spaces around the car. Position 4 is the car itself.
    private Boolean[] getTrafficSensorInfo(){
        Boolean [] currentSensorInfo = new Boolean[9];
        currentSensorInfo[4] = true;
        return (currentSensorInfo);
    }
    private String getDriverExperience(){ return "Low"; }
    //Get the current human activity.
    private String getHumanActivity(){
        return "Sleep";
    }

    //Get the incline angle
    private int getAngleOfIncline(){
        return 0;
    }
    private String getHumanStressLevel(){
        return "Low";
    }
    //Get the current noise levels.
    private int getNoiseLevelsDB(){
        return 81;
    }
}
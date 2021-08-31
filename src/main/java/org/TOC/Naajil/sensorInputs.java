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
        return "Sunny";
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

    //Get the current human activity.
    private String getHumanActivity(){
        return "Phone";
    }

    //Get the current noise levels.
    private int getNoiseLevelsDB(){
        return 60;
    }
}
package org.TOC.Naajil;
import java.lang.Math;
import java.util.Arrays;

public class mainDecisionModel {
    public humanSenses sensesAvailability = new humanSenses();
    public double frictionCoefficient;
    public int timeToSituationalAwareness;
    public Boolean[][] trafficMap;
    public double speedMS;
    public int distanceToObstacle;
    //public String weatherConditions;
    public String trafficIntensity;
    //public String humanActivity;
    public int noiseLevels;
    //public String driverExperience;
    //public String humanStressLevel;
    public String informingModality[] = new String[3]; //[Visual, Auditory, Haptics]
    public double angleOfIncline;

    //--- CONSTRUCTOR ---//
    public mainDecisionModel(situationModel currentSituation){
        sensesAvailability = currentSituation.sensesAvailability;
        frictionCoefficient = currentSituation.frictionCoefficient;
        timeToSituationalAwareness = currentSituation.timeToSituationalAwareness;
        trafficMap = currentSituation.trafficMap;
        speedMS = convertSpeedtoMeterPerSeconds(currentSituation.speed);
        distanceToObstacle = currentSituation.distanceToObstacle;
        trafficIntensity = currentSituation.trafficIntensity;
        angleOfIncline = convertDegreesToRadians(currentSituation.angleOfIncline);
    }

    public double calculateSafeTakeoverTime(){
        double totalTimeToStop = 0;
        totalTimeToStop = timeToSituationalAwareness + timeToStopAfterBraking();
        return totalTimeToStop;
    }
    //--- Predicts the best possible mode of informing the user based on senses availability ---//
    public String[] predictInformingLowModality(){
          String visualFeedback = "low";
          String audioFeedback = "low";
          String hapticsFeedback = "low";
          if(sensesAvailability.sight < 50){
              visualFeedback = "high";
              if(sensesAvailability.hearing >= 50){
                  audioFeedback = "low";
                  hapticsFeedback = "low";
              }
              else if (sensesAvailability.haptics >= 50){
                  hapticsFeedback = "low";
                  audioFeedback = "high";
              }
              else {
                  audioFeedback = "low";
                  hapticsFeedback = "high";
              }
          }
          else if(sensesAvailability.sight >= 50){
              visualFeedback = "low";
              if(sensesAvailability.hearing >= 50){
                  audioFeedback = "low";
                  hapticsFeedback = "low";
              }
              else {
                  hapticsFeedback = "low";
                  audioFeedback = "high";
              }
          }
          informingModality[0] = visualFeedback;
          informingModality[1] = audioFeedback;
          informingModality[2] = hapticsFeedback;
        return informingModality;
    }
    public String[] predictInformingHighModality(){
        String visualFeedback = "low";
        String audioFeedback = "low";
        String hapticsFeedback = "low";
        if(sensesAvailability.sight < 50){
            visualFeedback = "Low";
            if(sensesAvailability.hearing >= 50){
                audioFeedback = "high";
                hapticsFeedback = "low";
            }
            else if (sensesAvailability.haptics >= 50){
                hapticsFeedback = "High";
                audioFeedback = "high";
            }
            else {
                visualFeedback = "high";
                audioFeedback = "high";
                hapticsFeedback = "high";
            }
        }
        else if(sensesAvailability.sight >= 50){
            visualFeedback = "high";
            if(sensesAvailability.hearing >= 50){
                audioFeedback = "high";
                hapticsFeedback = "low";
            }
            else if(sensesAvailability.haptics >= 50 ){
                hapticsFeedback = "high";
                audioFeedback = "low";
            }
            else{
                audioFeedback = "high";
                hapticsFeedback = "low";
            }
        }
        informingModality[0] = visualFeedback;
        informingModality[1] = audioFeedback;
        informingModality[2] = hapticsFeedback;
        return informingModality;
    }
    //Test Functions for predicting modality
    public String[] predictInformingLowModalityTest(int sight, int hearing, int haptics){
        String visualFeedback = "low";
        String audioFeedback = "low";
        String hapticsFeedback = "low";
        if(sight < 50){
            visualFeedback = "high";
            if(hearing >= 50){
                audioFeedback = "low";
                hapticsFeedback = "low";
            }
            else if (haptics >= 50){
                hapticsFeedback = "low";
                audioFeedback = "high";
            }
            else {
                audioFeedback = "low";
                hapticsFeedback = "high";
            }
        }
        else if(sight >= 50){
            visualFeedback = "low";
            if(hearing >= 50){
                audioFeedback = "low";
                hapticsFeedback = "low";
            }
            else {
                hapticsFeedback = "low";
                audioFeedback = "high";
            }
        }
        informingModality[0] = visualFeedback;
        informingModality[1] = audioFeedback;
        informingModality[2] = hapticsFeedback;
        return informingModality;
    }
    public String[] predictInformingHighModalityTest(int sight, int hearing, int haptics){
        String visualFeedback = "low";
        String audioFeedback = "low";
        String hapticsFeedback = "low";
        if(sight < 50){
            visualFeedback = "Low";
            if(hearing >= 50){
                audioFeedback = "high";
                hapticsFeedback = "low";
            }
            else if (haptics >= 50){
                hapticsFeedback = "High";
                audioFeedback = "high";
            }
            else {
                visualFeedback = "high";
                audioFeedback = "high";
                hapticsFeedback = "high";
            }
        }
        else if(sight >= 50){
            visualFeedback = "high";
            if(hearing >= 50){
                audioFeedback = "high";
                hapticsFeedback = "low";
            }
            else if(haptics >= 50 ){
                hapticsFeedback = "high";
                audioFeedback = "low";
            }
            else{
                audioFeedback = "high";
                hapticsFeedback = "low";
            }
        }
        informingModality[0] = visualFeedback;
        informingModality[1] = audioFeedback;
        informingModality[2] = hapticsFeedback;
        return informingModality;
    }

    //--- returns Total distance to stop including the distance travelled while situational awareness is gained. ---//
    public double calculateTotalDistanceToStop(){
        double totalDistanceToStop = 0;
        totalDistanceToStop = distanceBeforeSituationalAwareness() + distanceToStopAfterBraking();
        return totalDistanceToStop;
    }

    //--- Converts the angle of incline from degrees (provided by the car) to radians ---//
    private double convertDegreesToRadians(int angleOfIncline){
        return Math.toRadians(angleOfIncline);
    }

    //--- Converts speed from Km/h to m/s ---//
    private double convertSpeedtoMeterPerSeconds(int speed){
        double speedMS = speed * 5;
        speedMS = speedMS/18;
        return speedMS;
    }

    //--- Calculates distance travelled while the human was becoming situationally aware ---//
    private double distanceBeforeSituationalAwareness(){
        return(speedMS*timeToSituationalAwareness);
    }

    //--- Calculates Distance Travelled after fully applying the brakes ---//
    private double distanceToStopAfterBraking(){
        double distance;
        double velocitySquared = speedMS*speedMS;
        double inclinedAdjustment = frictionCoefficient * Math.cos(angleOfIncline);
        inclinedAdjustment = inclinedAdjustment + Math.sin(angleOfIncline);
        double denominator = 2 * 9.81 * inclinedAdjustment;
        distance = velocitySquared/denominator;
        return distance;
    }

    //--- Calculates time required for the vehicle to stop ---//
    private double timeToStopAfterBraking(){
        double time;
        double inclinedAdjustment = frictionCoefficient * Math.cos(angleOfIncline);
        inclinedAdjustment = inclinedAdjustment + Math.sin(angleOfIncline);
        double denominator = 9.81 * inclinedAdjustment;
        time = speedMS/denominator;
        return time;
    }
}

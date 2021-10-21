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
    public String trafficHandedness;
    //public String humanActivity;
    public int noiseLevels;
    //public String driverExperience;
    //public String humanStressLevel;
    public String informingModality[] = new String[3]; //[Visual, Auditory, Haptics]
    public double angleOfIncline;
    public String preferredAction = "";
    public double totalDistanceToStop = 0;
    public double totalSafeTakeoverTime = 0;
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
        trafficHandedness = currentSituation.trafficHandedness;
        //calculateSafeTakeoverTime();
        calculateTotalDistanceToStop();
        preferredAction = predictPreferredAction();
        calculateSafeTakeoverTime();
        predictInformingHighModality();


    }

    //--- Prints the output of the maindecision model ---//
    public void printMainDecisionModelOutput(){
        System.out.println("=====================================");
        System.out.println("MAIN DECISION MODEL OUTPUT");
        System.out.println("=====================================");
        System.out.println("Informing Modality: ");
        System.out.println("Visual: " + informingModality[0] + ". Auditory: " + informingModality[1] + ". Haptics: " + informingModality[2] );
        if(preferredAction != ""){
            System.out.println("Preferred Action: " + preferredAction);
            System.out.println("Safe Takeover time if Preferred Action is followed: " + totalSafeTakeoverTime + "s");
        }
        else{
            System.out.println("Safe takeover time with stopping: " + totalSafeTakeoverTime + "s");
        }
    }


    //--- returns Total safe time required for the TOC ---//
    public double calculateSafeTakeoverTime(){
        if(preferredAction == "Brake" || preferredAction == "") {
            totalSafeTakeoverTime = timeToSituationalAwareness + timeToStopAfterBraking();
            return totalSafeTakeoverTime;
        }
        else{
            totalSafeTakeoverTime = timeToSituationalAwareness + minimumTimeTakenForSwerve();
            return totalSafeTakeoverTime;
        }

    }

    //--- returns Total distance to stop including the distance travelled while situational awareness is gained. ---//
    public double calculateTotalDistanceToStop(){
        totalDistanceToStop = distanceBeforeSituationalAwareness() + distanceToStopAfterBraking();
        return totalDistanceToStop;
    }

    //--- Predicts the preferred action that the user can take. currently supports braking and swerving in different directions ---//
    public String predictPreferredAction(){
        if(totalDistanceToStop < distanceToObstacle && trafficMap[2][1] == false){
            return "Brake";
        }
        if (totalDistanceToStop < distanceToObstacle && trafficMap[2][1] == true) {
            if(trafficHandedness == "RHT") {
                if(trafficMap[0][1] == false && trafficMap[0][0] == false){
                    return "Swerve Left";
                }
                if(trafficMap[0][2] == false && trafficMap [1][2] == false && trafficMap[2][2] == false){
                    return "Swerve Right";
                }
                return "Brake";
            }
            if(trafficHandedness == "LHT"){
                if(trafficMap[0][2] == false && trafficMap[1][2] == false){
                    return "Swerve Right";
                }
                if(trafficMap[0][0] == false && trafficMap[0][1] == false && trafficMap[2][1] == false){
                    return "Swerve Left";
                }
                return "Brake";
            }
        }
        if(trafficHandedness == "RHT") {
            if(trafficMap[0][1] == false && trafficMap[0][0] == false){
                return "Swerve Left";
            }
            if(trafficMap[0][2] == false && trafficMap [1][2] == false){
                return "Swerve Right";
            }
        }
        if(trafficHandedness == "LHT"){
            if(trafficMap[0][2] == false && trafficMap[1][2] == false){
                return "Swerve Right";
            }
            if(trafficMap[0][0] == false && trafficMap[0][1] == false){
                return "Swerve Left";
            }
        }
        return "";
    }

    //--- Predicts the time taken when swerving. Using: https://jneades.com/wp-content/uploads/2016/12/Swerves-and-Lane-Changes.pdf  ---//
    private double minimumTimeTakenForSwerve(){
        double timeForSwerve = 0;
        double widthOfLane = 3.7; // Width of an average lane on a highway in metres.
        double numerator = 4 * widthOfLane;
        double denominator = frictionCoefficient * 9.81;
        timeForSwerve = Math.sqrt(numerator/denominator);
        return timeForSwerve;
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
    //Test Functions for predicting modality ---//
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
        if(distance < 0){ //This means that it is physically impossible to stop the car depending on the current situation.
            distance = Double.MAX_VALUE;
        }
        return distance;
    }

    //--- Calculates time required for the vehicle to stop ---//
    private double timeToStopAfterBraking(){
        double time;
        double inclinedAdjustment = frictionCoefficient * Math.cos(angleOfIncline);
        inclinedAdjustment = inclinedAdjustment + Math.sin(angleOfIncline);
        double denominator = 9.81 * inclinedAdjustment;
        time = speedMS/denominator;
        if(time < 0){ //This means that it is physically impossible to stop the car depending on the current situation.
            time = Double.MAX_VALUE;
        }
        return time;
    }
}

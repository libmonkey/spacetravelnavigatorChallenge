package system;

public class SpaceHighway {
    private Integer travelTime;
    private StarSystem ramp;
    private StarSystem exit;

    /**
     *
     * @param travelTime
     * @param ramp
     * @param exit
     */
    public SpaceHighway(Integer travelTime, StarSystem ramp, StarSystem exit){
        if(ramp.equals(exit)) {
            throw new IllegalArgumentException("Ramp and exit cannot be the same star system.");
        }
        this.travelTime = travelTime;
        this.ramp = ramp;
        this.exit = exit;
        ramp.addRamp(this);
        exit.addExit(this);
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public StarSystem getRamp() {
        return ramp;
    }

    public StarSystem getExit() {
        return exit;
    }

    public static Boolean isNull(SpaceHighway highway){
        return highway == null;
    }
}

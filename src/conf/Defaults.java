package conf;

public final class Defaults {
    public static final double startingTemperature = 10;
    public static final int numberOfIterationsRS = 100;
    public static final double coolingRate = (numberOfIterationsRS - 1) /100;

    public static final double mutationRate = 0.015;
    public static final int arenaSize = 5;
    public static final int populationSize = 10;
    public static final int numberOfIterationsAG = 100;

    public static final int numberOfIterationsTabu = 50;
    public static final int tabuListMaxSize = 3;
}

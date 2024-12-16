package org.RokueLike.domain;

import java.util.Random;

public class BuildManager {

    private static final int HALL_WIDTH = 25;
    private static final int HALL_HEIGHT = 25;

    private static String[][] hallOfEarth;
    private static String[][] hallOfAir;
    private static String[][] hallOfWater;
    private static String[][] hallOfFire;

    private static final Random random = new Random();

    public static void init() {
        // Initialize all 4 halls
        System.out.println("[BuildManager]: Initializing build mode...");
        hallOfEarth = createEmptyHall();
        hallOfAir = createEmptyHall();
        hallOfWater = createEmptyHall();
        hallOfFire = createEmptyHall();

        // Add doors
        placeDoor(hallOfEarth);
        placeDoor(hallOfAir);
        placeDoor(hallOfWater);
        placeDoor(hallOfFire);

        System.out.println("[BuildManager]: All halls initialized.");
    }

    private static String[][] createEmptyHall() {
        String[][] hall = new String[HALL_HEIGHT][HALL_WIDTH];
        for (int y = 0; y < HALL_HEIGHT; y++) {
            for (int x = 0; x < HALL_WIDTH; x++) {
                if (x == 0 || x == HALL_WIDTH - 1 || y == 0 || y == HALL_HEIGHT - 1) {
                    hall[y][x] = "#"; // Walls
                } else {
                    hall[y][x] = "."; // Floor
                }
            }
        }
        return hall;
    }

    private static void placeDoor(String[][] hall) {
        int side = random.nextInt(4); // Random side: 0=top, 1=bottom, 2=left, 3=right
        int position;

        switch (side) {
            case 0: // Top
                position = 1 + random.nextInt(HALL_WIDTH - 2);
                hall[0][position] = "d";
                break;
            case 1: // Bottom
                position = 1 + random.nextInt(HALL_WIDTH - 2);
                hall[HALL_HEIGHT - 1][position] = "d";
                break;
            case 2: // Left
                position = 1 + random.nextInt(HALL_HEIGHT - 2);
                hall[position][0] = "d";
                break;
            case 3: // Right
                position = 1 + random.nextInt(HALL_HEIGHT - 2);
                hall[position][HALL_WIDTH - 1] = "d";
                break;
        }
    }

    public static void placeObjectRandomly(String hallName, int numberOfObjects) {
        String[][] hall = getHall(hallName);
        if (hall == null) return;

        int placedObjects = 0;
        while (placedObjects < numberOfObjects) {
            int x = random.nextInt(HALL_WIDTH);
            int y = random.nextInt(HALL_HEIGHT);

            if (hall[y][x].equals(".")) { // Only place on floor tiles
                hall[y][x] = "o";
                placedObjects++;
            }
        }
        System.out.println("[BuildManager]: Placed " + numberOfObjects + " objects in " + hallName);
    }

    public static void placeObjectManually(String hallName, int x, int y) {
        String[][] hall = getHall(hallName);
        if (hall == null) return;

        if (x > 0 && x < HALL_WIDTH - 1 && y > 0 && y < HALL_HEIGHT - 1 && hall[y][x].equals(".")) {
            hall[y][x] = "o";
            System.out.println("[BuildManager]: Object placed at (" + x + ", " + y + ") in " + hallName);
        } else {
            System.out.println("[BuildManager]: Invalid position or cell not empty.");
        }
    }

    public static String[][] getHall(String hallName) {
        switch (hallName.toLowerCase()) {
            case "earth":
                return hallOfEarth;
            case "air":
                return hallOfAir;
            case "water":
                return hallOfWater;
            case "fire":
                return hallOfFire;
            default:
                System.out.println("[BuildManager]: Invalid hall name: " + hallName);
                return null;
        }
    }

    public static void reset() {
        init();
        System.out.println("[BuildManager]: Build mode reset.");
    }

    public static String[][][] getAllHalls() {
        return new String[][][]{hallOfEarth, hallOfAir, hallOfWater, hallOfFire};
    }
}

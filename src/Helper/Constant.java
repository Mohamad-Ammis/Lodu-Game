package Helper;

import java.util.*;


public class Constants {
    public static final int BOARD_SIZE = 53;  // Board has 52 positions
    public static final Map<Integer, double[]> COORDINATES_MAP = new HashMap<>();
    public static final double STEP_LENGTH = 7.0;
    public static final String[] PLAYERS = { "P2", "P2" };
    public static final Map<Integer, double[]> PATH_POSITIONS = new HashMap<>();  // New map for path positions
    public static final Map<String, int[]> BASE_POSITIONS = new HashMap<>();
    public static final Map<String, Integer> START_POSITIONS = new HashMap<>();
    public static final Map<String, int[]> HOME_ENTRANCE = new HashMap<>();
    public static final Map<String, Integer> HOME_POSITIONS = new HashMap<>();
    public static final Map<String, Integer> TURNING_POINTS = new HashMap<>();
    public static final int[] SAFE_POSITIONS = { 1, 8, 13, 21, 26, 34, 39, 47 };
    public static final Map<String, String> STATE = new HashMap<>();
    static {
        // Initialize COORDINATES_MAP
        COORDINATES_MAP.put(1, new double[]{6, 13});
        COORDINATES_MAP.put(2, new double[]{6, 12});
        COORDINATES_MAP.put(3, new double[]{6, 11});
        COORDINATES_MAP.put(4, new double[]{6, 10});
        COORDINATES_MAP.put(5, new double[]{6, 9});
        COORDINATES_MAP.put(6, new double[]{5, 8});
        COORDINATES_MAP.put(6, new double[]{4, 8});
        COORDINATES_MAP.put(7, new double[]{3, 8});
        COORDINATES_MAP.put(8, new double[]{2, 8});
        COORDINATES_MAP.put(9, new double[]{1, 8});
        COORDINATES_MAP.put(10, new double[]{0, 8});
        COORDINATES_MAP.put(11, new double[]{0, 7});
        COORDINATES_MAP.put(12, new double[]{0, 6});
        COORDINATES_MAP.put(13, new double[]{1, 6});
        COORDINATES_MAP.put(14, new double[]{2, 6});
        COORDINATES_MAP.put(15, new double[]{3, 6});
        COORDINATES_MAP.put(16, new double[]{4, 6});
        COORDINATES_MAP.put(17, new double[]{5, 6});
        COORDINATES_MAP.put(18, new double[]{6, 5});
        COORDINATES_MAP.put(19, new double[]{6, 4});
        COORDINATES_MAP.put(20, new double[]{6, 3});
        COORDINATES_MAP.put(21, new double[]{6, 2});
        COORDINATES_MAP.put(22, new double[]{6, 1});
        COORDINATES_MAP.put(23, new double[]{6, 0});
        COORDINATES_MAP.put(24, new double[]{7, 0});
        COORDINATES_MAP.put(25, new double[]{8, 0});
        COORDINATES_MAP.put(26, new double[]{8, 1});
        COORDINATES_MAP.put(27, new double[]{8, 2});
        COORDINATES_MAP.put(28, new double[]{8, 3});
        COORDINATES_MAP.put(29, new double[]{8, 4});
        COORDINATES_MAP.put(30, new double[]{8, 5});
        COORDINATES_MAP.put(31, new double[]{9, 6});
        COORDINATES_MAP.put(32, new double[]{10, 6});
        COORDINATES_MAP.put(33, new double[]{11, 6});
        COORDINATES_MAP.put(34, new double[]{12, 6});
        COORDINATES_MAP.put(35, new double[]{13, 6});
        COORDINATES_MAP.put(36, new double[]{14, 6});
        COORDINATES_MAP.put(37, new double[]{14, 7});
        COORDINATES_MAP.put(38, new double[]{14, 8});
        COORDINATES_MAP.put(39, new double[]{13, 8});
        COORDINATES_MAP.put(40, new double[]{12, 8});
        COORDINATES_MAP.put(41, new double[]{11, 8});
        COORDINATES_MAP.put(42, new double[]{10, 8});
        COORDINATES_MAP.put(43, new double[]{9, 8});
        COORDINATES_MAP.put(44, new double[]{8, 9});
        COORDINATES_MAP.put(45, new double[]{8, 10});
        COORDINATES_MAP.put(46, new double[]{8, 11});
        COORDINATES_MAP.put(47, new double[]{8, 12});
        COORDINATES_MAP.put(48, new double[]{8, 13});
        COORDINATES_MAP.put(49, new double[]{8, 14});
        COORDINATES_MAP.put(50, new double[]{7, 14});
        COORDINATES_MAP.put(51, new double[]{6, 14});

        COORDINATES_MAP.put(100, new double[]{7, 13});
        COORDINATES_MAP.put(101, new double[]{7, 12});
        COORDINATES_MAP.put(102, new double[]{7, 11});
        COORDINATES_MAP.put(103, new double[]{7, 10});
        COORDINATES_MAP.put(104, new double[]{7, 9});
        COORDINATES_MAP.put(105, new double[]{7, 8});

        COORDINATES_MAP.put(200, new double[]{7, 1});
        COORDINATES_MAP.put(201, new double[]{7, 2});
        COORDINATES_MAP.put(202, new double[]{7, 3});
        COORDINATES_MAP.put(203, new double[]{7, 4});
        COORDINATES_MAP.put(204, new double[]{7, 5});
        COORDINATES_MAP.put(205, new double[]{7, 6});

        COORDINATES_MAP.put(500, new double[]{1.5, 10.58});
        COORDINATES_MAP.put(501, new double[]{3.57, 10.58});
        COORDINATES_MAP.put(502, new double[]{1.5, 12.43});
        COORDINATES_MAP.put(503, new double[]{3.57, 12.43});

        COORDINATES_MAP.put(600, new double[]{10.5, 1.58});
        COORDINATES_MAP.put(601, new double[]{12.54, 1.58});
        COORDINATES_MAP.put(602, new double[]{10.5, 3.45});
        COORDINATES_MAP.put(603, new double[]{12.54, 3.45});

        // Initialize BASE_POSITIONS
        BASE_POSITIONS.put("P1", new int[] { 500, 501, 502, 503 });
        BASE_POSITIONS.put("P2", new int[] { 600, 601, 602, 603 });
        // Initialize START_POSITIONS
        START_POSITIONS.put("P1", 0);
        START_POSITIONS.put("P2", 26);

        // Initialize HOME_ENTRANCE
        HOME_ENTRANCE.put("P1", new int[] { 100, 101, 102, 103, 104 });
        HOME_ENTRANCE.put("P2", new int[] { 200, 201, 202, 203, 204 });
        // Initialize HOME_POSITIONS
        HOME_POSITIONS.put("P1", 105);
        HOME_POSITIONS.put("P2", 205);

        // Initialize TURNING_POINTS
        TURNING_POINTS.put("P1", 50);
        TURNING_POINTS.put("P2", 24);

        // Initialize STATE
        STATE.put("DICE_NOT_ROLLED", "DICE_NOT_ROLLED");
        STATE.put("DICE_ROLLED", "DICE_ROLLED");
        // Initialize PATH_POSITIONS
        PATH_POSITIONS.put(0, new double[]{6, 13});
        PATH_POSITIONS.put(1, new double[]{6, 12});
        PATH_POSITIONS.put(2, new double[]{6, 11});
        PATH_POSITIONS.put(3, new double[]{6, 10});
        PATH_POSITIONS.put(4, new double[]{6, 9});
        PATH_POSITIONS.put(5, new double[]{5, 8});
        PATH_POSITIONS.put(6, new double[]{4, 8});
        PATH_POSITIONS.put(7, new double[]{3, 8});
        PATH_POSITIONS.put(8, new double[]{2, 8});
        PATH_POSITIONS.put(9, new double[]{1, 8});
        PATH_POSITIONS.put(10, new double[]{0, 8});
        PATH_POSITIONS.put(11, new double[]{0, 7});
        PATH_POSITIONS.put(12, new double[]{0, 6});
        PATH_POSITIONS.put(13, new double[]{1, 6});
        PATH_POSITIONS.put(14, new double[]{2, 6});
        PATH_POSITIONS.put(15, new double[]{3, 6});
        PATH_POSITIONS.put(16, new double[]{4, 6});
        PATH_POSITIONS.put(17, new double[]{5, 6});
        PATH_POSITIONS.put(18, new double[]{6, 5});
        PATH_POSITIONS.put(19, new double[]{6, 4});
        PATH_POSITIONS.put(20, new double[]{6, 3});
        PATH_POSITIONS.put(21, new double[]{6, 2});
        PATH_POSITIONS.put(22, new double[]{6, 1});
        PATH_POSITIONS.put(23, new double[]{6, 0});
        PATH_POSITIONS.put(24, new double[]{7, 0});
        PATH_POSITIONS.put(25, new double[]{8, 0});
        PATH_POSITIONS.put(26, new double[]{8, 1});
        PATH_POSITIONS.put(27, new double[]{8, 2});
        PATH_POSITIONS.put(28, new double[]{8, 3});
        PATH_POSITIONS.put(29, new double[]{8, 4});
        PATH_POSITIONS.put(30, new double[]{8, 5});
        PATH_POSITIONS.put(31, new double[]{9, 6});
        PATH_POSITIONS.put(32, new double[]{10, 6});
        PATH_POSITIONS.put(33, new double[]{11, 6});
        PATH_POSITIONS.put(34, new double[]{12, 6});
        PATH_POSITIONS.put(35, new double[]{13, 6});
        PATH_POSITIONS.put(36, new double[]{14, 6});
        PATH_POSITIONS.put(37, new double[]{14, 7});
        PATH_POSITIONS.put(38, new double[]{14, 8});
        PATH_POSITIONS.put(39, new double[]{13, 8});
        PATH_POSITIONS.put(40, new double[]{12, 8});
        PATH_POSITIONS.put(41, new double[]{11, 8});
        PATH_POSITIONS.put(42, new double[]{10, 8});
        PATH_POSITIONS.put(43, new double[]{9, 8});
        PATH_POSITIONS.put(44, new double[]{8, 9});
        PATH_POSITIONS.put(45, new double[]{8, 10});
        PATH_POSITIONS.put(46, new double[]{8, 11});
        PATH_POSITIONS.put(47, new double[]{8, 12});
        PATH_POSITIONS.put(48, new double[]{8, 13});
        PATH_POSITIONS.put(49, new double[]{8, 14});
        PATH_POSITIONS.put(50, new double[]{7, 14});
        PATH_POSITIONS.put(51, new double[]{6, 14});
    }
}

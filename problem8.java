import java.util.*;

class ParkingSpot {
    String plate;
    long entryTime;
}

public class Problem8 {

    private ParkingSpot[] table = new ParkingSpot[500];

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % table.length;
    }

    public void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (table[index] != null) {
            index = (index + 1) % table.length;
            probes++;
        }

        ParkingSpot p = new ParkingSpot();
        p.plate = plate;
        p.entryTime = System.currentTimeMillis();

        table[index] = p;

        System.out.println("Assigned spot #" + index + " (" + probes + " probes)");
    }

    public void exitVehicle(String plate) {

        for (int i = 0; i < table.length; i++) {

            if (table[i] != null && table[i].plate.equals(plate)) {

                long duration = (System.currentTimeMillis() - table[i].entryTime) / 1000;

                table[i] = null;

                System.out.println("Spot #" + i + " freed, Duration: " + duration + " seconds");
                return;
            }
        }
    }

    public static void main(String[] args) {

        Problem8 lot = new Problem8();

        lot.parkVehicle("ABC1234");
        lot.parkVehicle("ABC1235");

        lot.exitVehicle("ABC1234");
    }
}
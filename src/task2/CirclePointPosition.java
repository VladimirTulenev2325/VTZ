package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CirclePointPosition {
    public static void main(String[] args) {

        String workingDir = System.getProperty("user.dir");
        String circleFile = workingDir + "/test1.txt";
        String pointsFile = workingDir + "/test2.txt";

        try {
            // Read circle coordinates and radius from file
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
            String circleLine = circleReader.readLine();
            String[] circleCoords = circleLine.split(" ");
            float circleX = Float.parseFloat(circleCoords[0]);
            float circleY = Float.parseFloat(circleCoords[1]);
            float circleRadius = Float.parseFloat(circleReader.readLine());
            circleReader.close();

            // Read point coordinates from file
            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile));
            String pointLine;
            while ((pointLine = pointsReader.readLine()) != null) {
                String[] pointCoords = pointLine.split(" ");
                float pointX = Float.parseFloat(pointCoords[0]);
                float pointY = Float.parseFloat(pointCoords[1]);
                int position = getPointPosition(circleX, circleY, circleRadius, pointX, pointY);
                System.out.println(position);
            }
            pointsReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPointPosition(float circleX, float circleY, float circleRadius, float pointX, float pointY) {
        float distance = (float) Math.sqrt(Math.pow(pointX - circleX, 2) + Math.pow(pointY - circleY, 2));
        if (distance < circleRadius) {
            return 1; // point inside the circle
        } else if (distance > circleRadius) {
            return 2; // point outside the circle
        } else {
            return 0; // point on the circle
        }
    }
}

package wethinkcode.simulator;

import wethinkcode.aircraft.AircraftFactory;
import wethinkcode.interfaces.Flyable;

import java.io.*;

public class Simulator {

    public static PrintWriter writer;
    public static int simulations;

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        String filename = args[0];

        File simOut = new File("simulation.txt");
        try {
            writer = new PrintWriter(simOut);
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return;
        }

        WeatherTower weatherTower = new WeatherTower();

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String inputLine;
            int lineNumber = 1;
            String[] splitInput;

            while ((inputLine = bufferedReader.readLine()) != null)
            {
                if (lineNumber == 1)
                    try {
                        simulations = Integer.parseInt(inputLine);
                        if (simulations < 0)
                        {
                            System.out.println("Error: the first line of the scenario should contain a POSITIVE integer. Please review the scenario file and try again.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: first line of scenario file must be an integer. Please review the scenario file and try again.");
                        return;
                    }
                else
                {
                    splitInput = inputLine.split(" ");
                    if (splitInput.length == 1 && splitInput[0].isEmpty()) // Skips over empty lines in the input file
                        continue;
                    if (splitInput.length != 5)
                        throw new Exception("Error: line " + lineNumber + ": input should contain 5 parameters. This line only has " + splitInput.length + " parameters.");
                    try {
                        Flyable aircraft = AircraftFactory.newAircraft(
                                splitInput[0],
                                splitInput[1],
                                Integer.parseInt(splitInput[2]),
                                Integer.parseInt(splitInput[3]),
                                Integer.parseInt(splitInput[4])
                        );
                        aircraft.registerTower(weatherTower);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: line " + lineNumber + ": parameters 3 to 5 must be integers. Please check the simulation.txt file and try again.");
                        return;
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        return;
                    }
                }
                lineNumber++;
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        while (simulations > 0)
        {
            weatherTower.changeWeather();
            simulations--;
        }

        writer.close();
    }
}
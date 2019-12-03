/*
 * Course: CS1021
 * Winter 2019
 * Lab 01 - Wav Files
 * Name: Sam Ferguson
 * Created: 12/2/2019
 */
package msoe.fergusons.lab01;

import msoe.taylor.audio.WavFile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simple class to reverse, and generate simple wav files.
 * @author Sam Ferguson
 */
public class WaveGenerator {

    private static Scanner input;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int command = getUserCommand(input);
        boolean exitProgram = false;
        do {
            switch(command) {
                case 0:
                    exitProgram = true;
                    break;
                case 1:
                    String fileName = getFileName(input);
                    WavFile wavFile = new WavFile(fileName + ".wav");
                    ArrayList<Double> audioSamples = wavFile.getSamples();
                    ArrayList<Double> reversedSamples = reverseWavFile(audioSamples);

                    break;

            }

        }
        while(!exitProgram);
    }

    /**
     * Method to get the user command from the console
     * @param input Scanner to read the console with
     * @return the command specified by the user
     */
    private static int getUserCommand(Scanner input) {
        System.out.println("----------------------------------");
        System.out.println("0: Exit");
        System.out.println("1: Reverse Wave File:");
        System.out.println("2: Generate Single Tone Wave File");
        System.out.println("3: Generate Stereo Wave File");
        System.out.println("----------------------------------");
        System.out.println("Please Choose an Option");
        int command = Integer.valueOf(input.nextLine());
        System.out.println(); //Skips Line in Console for ease of user input and reading
        return command;
    }

    /**
     * Method to get the name of the wave file that the user wants to read or create
     * @param input Scanner to read the console with
     * @return filename to be used in program
     */
    private static String getFileName(Scanner input) {
        System.out.print("Please Enter the Name of the WavFile you wish to create or Reverse: ");
        String fileName = input.nextLine();
        System.out.println(); //Skips Line in Console for ease of user input and reading
        return fileName;
    }

    /**
     * Simple method to reverse the audio samples from a given wav file.
     * @param audioSamples the audio samples to reverse
     * @return an arraylist of the reversed audio samples
     */
    private static ArrayList<Double> reverseWavFile(ArrayList<Double> audioSamples) {
        ArrayList<Double> reversedSamples = new ArrayList<Double>();
        for (int i = audioSamples.size() - 1; i >= 0; i--) {
            reversedSamples.add(audioSamples.get(i));
        }
        return reversedSamples;
    }

}

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

    private static final long NUM_FRAMES = 8000;
    private static final int VALID_BITS = 8;
    private static final long SAMPLE_RATE = 8000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int command = getUserCommand(input);
        boolean exitProgram = false;
        do {
            switch(command) {
                case 0: //Exit the Program
                    exitProgram = true;
                    break;
                case 1: //Reverse Given WavFile
                    String fileName = getFileName(input);
                    WavFile wavFile = new WavFile(fileName + ".wav");
                    ArrayList<Double> audioSamples = wavFile.getSamples();
                    // Reverses Audio Samples from Original Wave File
                    ArrayList<Double> reversedSamples = reverseWavFileSamples(audioSamples);
                    // Close original Wav File
                    if(wavFile.close()) {
                        System.out.println("Original File CLosed Successfully");
                    } else {
                        System.out.println("Original File Not Closed Properly");
                    }

                    // Generate new WavFile object for reversed sample
                    WavFile reversedWavFile = new WavFile(fileName + "Rev.wav",
                            wavFile.getNumChannels(),
                            wavFile.getNumFrames(),
                            wavFile.getValidBits(),
                            wavFile.getSampleRate());

                    // Saves Reversed audio samples to new file.
                    reversedWavFile.setSamples(reversedSamples);
                    // Closes Reversed Wav File and releases resources
                    if(reversedWavFile.close()) {
                        System.out.println("Reversed Wave File Saved Successfully!");
                    } else {
                        System.out.println("Reversed Wave File Not Saved Correctly");
                    }
                    break;
                case 2: // Generate Simple WavFile
                    WavFile simpleWavFile = generateSimpleWavFile();

                    break;
                case 3: // Generate Stereo File
                    WavFile stereoWavFile = generateStereoWavFile();
                    break;
                default: // Continue asking for user input
                    command = getUserCommand(input);
            }

        } while(!exitProgram);
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
    private static ArrayList<Double> reverseWavFileSamples(ArrayList<Double> audioSamples) {
        ArrayList<Double> reversedSamples = new ArrayList<Double>();
        for (int i = audioSamples.size() - 1; i >= 0; i--) {
            reversedSamples.add(audioSamples.get(i));
        }
        return reversedSamples;
    }

    /**
     * Generates a simple wave file from a given frequency
     * @param frequency The frequency to use when creating the wave file.
     * @param fileName The name of the audio file to create
     * @return The Generated WavFile Object
     */
    public static void generateSimpleWavFile(double frequency, String fileName) {
        int numChannels = 1;
        WavFile simpleWavFile = new WavFile(fileName + ".wav",
                numChannels,
                NUM_FRAMES,
                VALID_BITS,
                SAMPLE_RATE);

        ArrayList<Double> audioSamples = new ArrayList<Double>();
        for(int i = 0; i < SAMPLE_RATE; i++) {
            audioSamples.add(generateSineWaveValues(frequency, i));
        }
        simpleWavFile.setSamples(audioSamples);
        // Closes Simple Wave FIle
        simpleWavFile.close();
    }

    /**
     * Generates a simple wave file in stereo
     * @return The Generated WavFile Object
     */
    public static WavFile generateStereoWavFile() {
        //placeholder

        return null;
    }

    /**
     * Simple function to generate each sample from the frequency and sample index
     * @param frequency The frequency of sound to generate
     * @param sample The index of the sample to generate
     * @return returns the requested audio sample
     */
    public static Double generateSineWaveValues(double frequency, double sample) {
        return (2 * Math.PI) * sample * (frequency / SAMPLE_RATE);
    }
}

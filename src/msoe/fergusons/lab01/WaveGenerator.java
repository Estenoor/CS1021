/*
 * Course: CS1021
 * Winter 2019
 * Lab 01 - Wav Files
 * Name: Sam Ferguson
 * Created: 12/2/2019
 */
package msoe.fergusons.lab01;

import us.msoe.taylor.audio.WavFile;

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
        boolean exitProgram = false;
        do {
            int command = getUserCommand(input);
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
                    String simpleFileName = getFileName(input);
                    System.out.print("Please Enter The Frequency you wish to use: ");
                    int frequency = getUserFrequency(input);
                    generateSimpleWavFile(frequency, simpleFileName);
                    break;
                case 3: // Generate Stereo File
                    String stereoFileName = getFileName(input);
                    System.out.print("Please Enter The First Frequency you wish to use: ");
                    int frequencyOne = getUserFrequency(input);
                    System.out.print("Please Enter The Second Frequency you wish to use: ");
                    int frequencyTwo = getUserFrequency(input);
                    generateStereoWavFile(frequencyOne, frequencyTwo, stereoFileName);
                    break;
            }

        } while(!exitProgram);
        // Closes the Scanner and releases the Resources
        input.close();
    }

    /**
     * Simple method to read in a frequency from the console
     * @param input the scanner to read the from the console with
     * @return the frequency specified by the user
     */
    private static int getUserFrequency(Scanner input) {
        return Integer.valueOf(input.nextLine());
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
        System.out.print("Please Choose an Option: ");
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
        // Saves Audio Samples to Simple Wave File
        simpleWavFile.setSamples(audioSamples);
        // Closes Simple Wave FIle
        simpleWavFile.close();
        System.out.println("Simple Wav File Saved Correctly!");
    }

    /**
     * Generates a simple wave file in stereo
     * @param frequencyOne The first frequency to use
     * @param frequencyTwo The second frequency to use
     * @param fileName the filename to save the generated wave file to
     */
    public static void generateStereoWavFile(double frequencyOne,
                                             double frequencyTwo,
                                             String fileName) {
        int numChannels = 2;
        WavFile stereoWavFile = new WavFile(fileName + ".wav",
                numChannels,
                NUM_FRAMES,
                VALID_BITS,
                SAMPLE_RATE);

        ArrayList<Double> audioSamples = new ArrayList<Double>();
        for(int i = 0; i < SAMPLE_RATE * 2; i++) {
            if ((i % 2) == 0) {
                audioSamples.add(generateSineWaveValues(frequencyOne, i));
            } else {
                audioSamples.add(generateSineWaveValues(frequencyTwo, i));
            }
        }
        // Saves Audio Samples to Stereo Wave File
        stereoWavFile.setSamples(audioSamples);
        // Closes Stereo Wave FIle
        stereoWavFile.close();
        System.out.println("Stereo Wav File Saved Correctly!");
    }

    /**
     * Simple function to generate each sample from the frequency and sample index
     * @param frequency The frequency of sound to generate
     * @param sample The index of the sample to generate
     * @return returns the requested audio sample
     */
    public static Double generateSineWaveValues(double frequency, double sample) {
        return Math.sin((2 * Math.PI) * sample * (frequency / SAMPLE_RATE));
    }
}

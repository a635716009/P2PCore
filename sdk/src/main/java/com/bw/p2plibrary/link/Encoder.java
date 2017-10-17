package com.bw.p2plibrary.link;





import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Encoder implements Constants {

    /**
     * encodeStream is the public function of class Encoder.
     * @param input the stream of bytes to encode
     * @param output the stream of audio samples representing the input,
     *               prefixed with an hail signal and a calibration signal
     */
    public static void encodeStream(InputStream input, OutputStream output)
            throws IOException {

        System.out.println("Voice:encodeStream starts");

        byte[] zeros = new byte[kSamplesPerDuration];

        //write out the hail and calibration sequences
        output.write(zeros);
        output.write(getHailSequence());

        //now write the data
        int read = 0;
        byte[] buff = new byte[kBytesPerDuration];
        while ((read = input.read(buff)) == kBytesPerDuration) {
            output.write(Encoder.encodeDuration(buff));
        }
        if (read > 0) {
            for (int i = read; i < kBytesPerDuration; i++) {
                buff[i] = 0;
            }
            output.write(Encoder.encodeDuration(buff));
        }
        output.write(getHailStopSequence());
        System.out.println("Voice:encodeStream ends");

    }

    /**
     * @param output the stream of audio samples representing the SOS hail
     */
    public static void generateSOS(OutputStream output) throws IOException {
        byte[] zeros = new byte[kSamplesPerDuration];
        output.write(zeros);
        output.write(getSOSSequence());
    }

    /**
     * @param bitPosition the position in the kBytesPerDuration wide byte array for which you want a frequency
     * @return the frequency in which to sound to indicate a 1 for this bitPosition
     * NOTE!: This blindly assumes that bitPosition is in the range [0 - 7]
     */

    private static int getFrequency(int bitPosition) {
        return Constants.kFrequencies[bitPosition];
    }


    /**
     * @param input an array of bytes to generate CRC for
     * @return the same array of bytes with its CRC appended at the end
     */
    public static byte[] appendCRC(byte[] input) {
        byte[] output = new byte[input.length + 1];
        //CRC校验
        byte crc8 = CRCGen.crc_8_ccitt(input, input.length);
        System.arraycopy(input,0,output,0,input.length);
        output[input.length] = crc8;

        return output;
    }

    /**
     * @param input a kBytesPerDuration long array of bytes to encode
     * @return an array of audio samples of type AudioUtil.kDefaultFormat
     */
    private static byte[] encodeDuration(byte[] input) {
        double[] signal = new double[kSamplesPerDuration];
        int j = 0;
        int x = input[j] & 0x0f;
        double xx = 0.8d;
        double innerMultiplier = getFrequency(x)
                * (1 / kSamplingFrequency) * 2 * Math.PI;

        for (int l = 0; l < signal.length / 2; l++) {
            if (l < 288) continue;
            if (l > 1312) continue;
            signal[l] = (Math.cos(innerMultiplier * l));
        }

        int xH = (input[j] & 0xf0) >> 4;
        double innerMultiplier1 = getFrequency(xH)
                * (1 / kSamplingFrequency) * 2 * Math.PI;
        for (int l = signal.length / 2; l < signal.length; l++) {
            if (l < signal.length / 2 + 288) continue;
            if (l > signal.length - 288) continue;
            signal[l] = (Math.cos(innerMultiplier1 * l));
        }
        return ArrayUtils.getByteArrayFromDoubleArray(smoothWindow(signal));
    }

    /**
     * @return audio samples for a duration of the hail frequency, Constants.kSOSFrequency
     */
    private static byte[] getSOSSequence() {
        double[] signal = new double[kSamplesPerDuration];
        //add a sinusoid of the hail frequency, amplitude kAmplitude and duration kDuration
        double innerMultiplier = Constants.kSOSFrequency * (1 / kSamplingFrequency) * 2 * Math.PI;
        for (int l = 0; l < signal.length; l++) {
            signal[l] = /*kAmplitude **/ Math.cos(innerMultiplier * l);
        }
        return ArrayUtils.getByteArrayFromDoubleArray(smoothWindow(signal, 0.3));
    }

    /**
     * @return audio samples for a duration of the hail frequency, Constants.kHailFrequency
     */
    private static byte[] getHailSequence() {
        double[] signal = new double[kSamplesPerDuration];
        //add a sinusoid of the hail frequency, amplitude kAmplitude and duration kDuration
        double innerMultiplier = Constants.kHailFrequency * (1 / kSamplingFrequency) * 2 * Math.PI;
        for (int l = 0; l < signal.length; l++) {
            if (l < 576 || l > signal.length - 576) continue;
            signal[l] = /*kAmplitude **/ Math.cos(innerMultiplier * l);
        }
        return ArrayUtils.getByteArrayFromDoubleArray(smoothWindow(signal, 0.3));
    }
    /**
     * @return audio samples for a duration of the hail frequency, Constants.kHailFrequency
     */
    private static byte[] getHailStopSequence() {
        double[] signal = new double[kSamplesPerDuration];
        //add a sinusoid of the hail frequency, amplitude kAmplitude and duration kDuration
        double innerMultiplier = Constants.kHailStopFrequency * (1 / kSamplingFrequency) * 2 * Math.PI;


        for (int l = 0; l < signal.length / 2; l++) {
            if (l < 288) continue;
            if (l > 1312) continue;
            signal[l] = (Math.cos(innerMultiplier * l));
        }


        for (int l = signal.length / 2; l < signal.length; l++) {
            if (l < signal.length / 2 + 288) continue;
            if (l > signal.length - 288) continue;
            signal[l] = (Math.cos(innerMultiplier * l));
        }
        return ArrayUtils.getByteArrayFromDoubleArray(smoothWindow(signal, 0.3));
    }
    /**
     * @return audio samples (of length 2 * kSamplesPerDuration), used to calibrate the decoding
     */
    private static byte[] getCalibrationSequence() {
        byte[] results = new byte[2 * kSamplesPerDuration];
        byte[] inputBytes1 = new byte[kBytesPerDuration];
        byte[] inputBytes2 = new byte[kBytesPerDuration];
        for (int i = 0; i < kBytesPerDuration; i++) {
            inputBytes1[i] = (byte) 0xAA; // 10101010
            inputBytes2[i] = (byte) 0x55; // 01010101
        }

        //encode inputBytes1 and 2 in sequence
        byte[] partialResult = encodeDuration(inputBytes1);
        System.arraycopy(partialResult,0,results,0,kSamplesPerDuration);
        partialResult = encodeDuration(inputBytes2);
        System.arraycopy(partialResult,0,results,kSamplesPerDuration,kSamplesPerDuration);
        return results;
    }

    /** About smoothwindow.
     * This is a data set in with the following form:
     *
     *   |
     * 1 |  +-------------------+
     *   | /                     \
     *   |/                       \
     *   +--|-------------------|--+---
     *     0.01              0.09  0.1  time
     *
     * It is used to smooth the edges of the signal in each duration
     *
     */
    private static double[] smoothWindow(double[] input, double magicScalingNumber) {
        double[] smoothWindow = new double[input.length];
        double minVal = 0;
        double maxVal = 0;
        int peaks = (int) (input.length * 0.1);
        double steppingValue = 1 / (double) peaks;
        for (int i = 0; i < smoothWindow.length; i++) {

            if (i < peaks) {
                smoothWindow[i] = input[i] * (steppingValue * i) /* / magicScalingNumber*/;
            } else if (i > input.length - peaks) {
                smoothWindow[i] = input[i] * (steppingValue * (input.length - i - 1)) /* / magicScalingNumber */;
            } else {
                //don't touch the middle values
                smoothWindow[i] = input[i] /* / magicScalingNumber */;
            }
            if (smoothWindow[i] < minVal) {
                minVal = smoothWindow[i];
            }
            if (smoothWindow[i] > maxVal) {
                maxVal = smoothWindow[i];
            }
        }
        return smoothWindow;
    }

    private static double[] smoothWindowNew(double[] input, double magicScalingNumber) {
        double[] smoothWindow = new double[input.length];
        double minVal = 0;
        double maxVal = 0;
        int peaks = (int) (input.length * 0.1);
        double steppingValue = 1 / (double) peaks;
        for (int i = 0; i < smoothWindow.length; i++) {
            if (i < peaks) {
                smoothWindow[i] = input[i] * (steppingValue * i) /* / magicScalingNumber*/;
            } else if (i > input.length - peaks) {
                smoothWindow[i] = input[i] * (steppingValue * (input.length - i - 1)) /* / magicScalingNumber */;
            } else {
                //don't touch the middle values
                smoothWindow[i] = input[i] /* / magicScalingNumber */;
            }
            if (smoothWindow[i] < minVal) {
                minVal = smoothWindow[i];
            }
            if (smoothWindow[i] > maxVal) {
                maxVal = smoothWindow[i];
            }
        }
        return smoothWindow;
    }

    private static double[] smoothWindow(double[] input) {
        double magicScalingNumber = 0.8;
        return smoothWindow(input, magicScalingNumber);
    }

    /**
     * This isn't used at the moment, but it does sound nice
     */
    private static double[] blackmanSmoothWindow(double[] input) {
        double magicScalingNumber = 3.5;
        double[] smoothWindow = new double[input.length];
        double steppingValue = 2 * Math.PI / (input.length - 1);
        double maxVal = 0;
        double minVal = 0;
        for (int i = 0; i < smoothWindow.length; i++) {
            smoothWindow[i] = (input[i] * (0.42 - 0.5 * Math.cos(steppingValue * i) +
                    0.08 * Math.cos(steppingValue * i))) * 3.5;
            if (smoothWindow[i] < minVal) {
                minVal = smoothWindow[i];
            }
            if (smoothWindow[i] > maxVal) {
                maxVal = smoothWindow[i];
            }
        }
        return smoothWindow;
    }
}
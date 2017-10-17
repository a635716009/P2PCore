package com.bw.p2plibrary.link;


/**
 * Copyright 2002 by the authors. All rights reserved.
 *
 * Author: Cristina V Lopes
 * (Modified by Jonas Michel, 2012)
 */

public interface Constants {
    
  double kLowFrequency = 600; //the lowest frequency used
  double kFrequencyStep = 50; //the distance between frequencies

  int kBytesPerDuration = 1; //how wide is the data stream
  								// (rps - that is, the pre-encoding data stream, not the audio)
  								// (rps - kFrequencies must be this long)
  
  int kBitsPerByte = 8; //unlikely to change, I know

  // Amplitude of each frequency in a frame.
//  public static final double kAmplitude = 0.125d; /* (1/8) */
    // Amplitude of each frequency in a frame.
    double kAmplitude = 0.8d; /* (1/8) */

  // Sampling frequency (number of sample values per second)
  double kSamplingFrequency = 16000; //rps - reduced to 11025 from 22050
  		// to enable the decoder to keep up with the audio on Motorola CLIQ  

  // Sound duration of encoded byte (in seconds)
  double kDuration = 0.2; // rps - increased from 0.1 to improve reliability on Android

  // Number of samples per duration
  int kSamplesPerDuration = (int)(kSamplingFrequency * kDuration);

  //This is used to convert the floats of the encoding to the bytes of the audio
  int kFloatToByteShift = 128;

  // The length, in durations, of the hail sequence
  int kDurationsPerHail = 3;

  // The frequency used in the initial hail of the key
  int kHailFrequency = 1203;

    int kHailStopFrequency = 1203;

  int kBaseFrequency = 1391;


    int[] kFrequencies = {kBaseFrequency,   //1000
            1484, //1125
            1578, //1250
            1656, //1500
            1765, //1666
            1859, //2000
            1906, //2250
            1968,
            2031,
            2094,
            2156,
            2219,
            2281,
            2344,
            1343,
            1266};


  //The length, in durations, of the hail sequence
  int kDurationsPerSOS = 1;
  
  // The frequency used to signal for help
  int kSOSFrequency = (int)(kBaseFrequency * (float)30/24); //1125
  
  // The length, in durations, of some session timing jitters
  int kPlayJitter = 5;
  int kSetupJitter = 10;
  
  // The length, in durations, of the CRC
  int kDurationsPerCRC = 1;
}

package com.jphobby.counterpointgenerator.Objects;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

public class ProbabilityDensityFunctions {

    // Set of possible intervals
    private final int[] numsToGenerate = new int[]    { 1,   2,    3,   4,    5,   6,   7};

    // Interval probability distribution for the bass line (cantus firmus).
    // In order to give the line a Gregorian quality, small intervals have higher probability
    private final double[] discreteProbabilitiesCantusFirmus = new double[] { 0.45, 0.30, 0.10, 0.05, 0.05, 0.05, 0.00 };
    // Interval probability for the non-bass lines
    // Only consonants are allowed (thirds, fifths and sixths)
    private final double[] discreteProbabilitiesConsonants = new double[] { 0.00, 0.33, 0.00, 0.00, 0.33, 0.00, 0.00 };

    // Instantiate the discrete probability distributions
    private final EnumeratedIntegerDistribution defaultDistribution =
            new EnumeratedIntegerDistribution(numsToGenerate, discreteProbabilitiesCantusFirmus);
    private final EnumeratedIntegerDistribution consonantsDistribution =
            new EnumeratedIntegerDistribution(numsToGenerate, discreteProbabilitiesConsonants);

    public int getIntervalFromDefaultDistribution() {
        int[] sample = defaultDistribution.sample(1);
        return sample[0];
    }

    public int getIntervalFromConsonantsDistribution() {
        int[] sample = consonantsDistribution.sample(1);
        return sample[0];
    }
}

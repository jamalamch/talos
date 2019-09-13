package com.rockbite.tools.talos.runtime.modules;

import com.rockbite.tools.talos.runtime.ParticleSystem;
import com.rockbite.tools.talos.runtime.ScopePayload;
import com.rockbite.tools.talos.runtime.values.FloatValue;

import java.util.Random;

public class RandomRangeModule extends Module {

    public static final int ALPHA = 0;

    private float min = 0, max = 100;

    private Random random = new Random();

    @Override
    public void init(ParticleSystem system) {
        super.init(system);
        createInputSlots(1);
        FloatValue output = new FloatValue();
        outputValues.put(0, output);
    }

    @Override
    public void processValues(ScopePayload scopePayload) {
        random.setSeed((long) ((scopePayload.getFloat(ScopePayload.PARTICLE_SEED) * 10000 * index * 1000)));
        // what's worse, keeping thousands of long values, or keeping floats but casting 1000 times to long?
        // I'll leave the answer to the reader

        float startPos = random.nextFloat();

        float res = min + (max - min) * startPos;

        outputValues.get(ALPHA).set(res);

        outputValues.put(0, outputValues.get(ALPHA));
    }

    public void setMinMax(float min, float max) {
        this.min = min;
        this.max = max;

    }
}
package io.skcloud.goblintest.model;

public class ImageGenerationRequest {
    private String input;
    private String model;
    private String action;
    private Parameters parameters;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public static class Parameters {
        private int params_version;
        private int width;
        private int height;
        private int scale;
        private String sampler;
        private int steps;
        private int n_samples;
        private int ucPreset;
        private boolean qualityToggle;
        private boolean sm;
        private boolean sm_dyn;
        private boolean dynamic_thresholding;
        private float controlnet_strength;
        private boolean legacy;
        private boolean add_original_image;
        private float uncond_scale;
        private float cfg_rescale;
        private String noise_schedule;
        private boolean legacy_v3_extend;
        private long seed;
        private String negative_prompt;

        public int getParams_version() {
            return params_version;
        }

        public void setParams_version(int params_version) {
            this.params_version = params_version;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public String getSampler() {
            return sampler;
        }

        public void setSampler(String sampler) {
            this.sampler = sampler;
        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }

        public int getN_samples() {
            return n_samples;
        }

        public void setN_samples(int n_samples) {
            this.n_samples = n_samples;
        }

        public int getUcPreset() {
            return ucPreset;
        }

        public void setUcPreset(int ucPreset) {
            this.ucPreset = ucPreset;
        }

        public boolean isQualityToggle() {
            return qualityToggle;
        }

        public void setQualityToggle(boolean qualityToggle) {
            this.qualityToggle = qualityToggle;
        }

        public boolean isSm() {
            return sm;
        }

        public void setSm(boolean sm) {
            this.sm = sm;
        }

        public boolean isSm_dyn() {
            return sm_dyn;
        }

        public void setSm_dyn(boolean sm_dyn) {
            this.sm_dyn = sm_dyn;
        }

        public boolean isDynamic_thresholding() {
            return dynamic_thresholding;
        }

        public void setDynamic_thresholding(boolean dynamic_thresholding) {
            this.dynamic_thresholding = dynamic_thresholding;
        }

        public float getControlnet_strength() {
            return controlnet_strength;
        }

        public void setControlnet_strength(float controlnet_strength) {
            this.controlnet_strength = controlnet_strength;
        }

        public boolean isLegacy() {
            return legacy;
        }

        public void setLegacy(boolean legacy) {
            this.legacy = legacy;
        }

        public boolean isAdd_original_image() {
            return add_original_image;
        }

        public void setAdd_original_image(boolean add_original_image) {
            this.add_original_image = add_original_image;
        }

        public float getUncond_scale() {
            return uncond_scale;
        }

        public void setUncond_scale(float uncond_scale) {
            this.uncond_scale = uncond_scale;
        }

        public float getCfg_rescale() {
            return cfg_rescale;
        }

        public void setCfg_rescale(float cfg_rescale) {
            this.cfg_rescale = cfg_rescale;
        }

        public String getNoise_schedule() {
            return noise_schedule;
        }

        public void setNoise_schedule(String noise_schedule) {
            this.noise_schedule = noise_schedule;
        }

        public boolean isLegacy_v3_extend() {
            return legacy_v3_extend;
        }

        public void setLegacy_v3_extend(boolean legacy_v3_extend) {
            this.legacy_v3_extend = legacy_v3_extend;
        }

        public long getSeed() {
            return seed;
        }

        public void setSeed(long seed) {
            this.seed = seed;
        }

        public String getNegative_prompt() {
            return negative_prompt;
        }

        public void setNegative_prompt(String negative_prompt) {
            this.negative_prompt = negative_prompt;
        }
    }

    // Getters and setters
    // ...
}

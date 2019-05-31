//https://github.com/vuejs/vue-cli/issues/1162  VH
const vueBabelPreset = require('@vue/babel-preset-app');

module.exports = (context, options = {}) => {
  // Cache the returned value forever and don't call this function again.
  context.cache(true);

  const {presets, plugins} = vueBabelPreset(context, options);

  // Find @babel/preset-stage-2, and update its config to enable `decoratorsLegacy`.
  const presetStage2 = require('@babel/preset-stage-2');
  const preset = presets.find(p => p[0] === presetStage2);
  if (preset) {
    preset[1].decoratorsLegacy = true;
  }

  return {
    presets,
    plugins
  };
}

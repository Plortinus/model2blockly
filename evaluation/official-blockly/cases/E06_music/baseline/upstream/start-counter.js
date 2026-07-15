Music.startCount.value = 0;

/**
 * Getter for the number of start blocks.
 * @returns {number} Integer number of start blocks on workspace.
 */
Music.startCount.get = function() {
  return Music.startCount.value;
};

/**
 * Setter for the number of start blocks.
 * @param {number} value Integer number of start blocks on workspace.
 */
Music.startCount.set = function(value) {
  Music.startCount.value = value;
};

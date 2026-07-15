/**
 * Register the interpreter asynchronous function
 * <code>waitForSeconds()</code>.
 */
function initInterpreterWaitForSeconds(interpreter, globalObject) {
  // Ensure function name does not conflict with variable names.
  javascript.javascriptGenerator.addReservedWords('waitForSeconds');

  const wrapper = interpreter.createAsyncFunction(
    function (timeInSeconds, callback) {
      // Delay the call to the callback.
      setTimeout(callback, timeInSeconds * 1000);
    },
  );
  interpreter.setProperty(globalObject, 'waitForSeconds', wrapper);
}

package com.akuvox.phone.utils;

/**
 * Tools for sending log output.
 *
 * <p>Generally, use the Logger.d() Logger.i() Logger.w() and Logger.e()
 * methods.
 *
 * <p>Use setXXX to set the default configuration of logger, 
 * like debug flag, debug level and debug tag
 *
 * It's a class that just encapsulates the class of Android.util.Log
 * and provides more detail info by prefix
 */
public final class Log {
	/* Const members */
	private static final int STACK_TRACE_LEVEL = 2;

	public static final int LOGGER_LEVEL_DEBUG = 6;
	public static final int LOGGER_LEVEL_INFO = 5;
	public static final int LOGGER_LEVEL_WARN = 4;
	public static final int LOGGER_LEVEL_ERR = 3;

	/* Private members */
	private static boolean mDebug = true;
	private static int mLogLevel = LOGGER_LEVEL_DEBUG;
	private static String mLogTag = "MyApp";

	/* Private methods */
	private Log() {
	}

	private static String getPrefix() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[STACK_TRACE_LEVEL];
		String prefix = traceElement.getFileName() + ": "
				+ traceElement.getMethodName() + "("
				+ traceElement.getLineNumber() + "): ";

		return prefix;
	}

	/* public methods */
	public static boolean getDebugFlag() {
		return mDebug;
	}

	public static void setDebugFlag(boolean debug) {
		mDebug = debug;
	}

	public static int getLogLevel() {
		return mLogLevel;
	}

	public static void setLogLevel(int logLevel) {
		mLogLevel = logLevel;
	}

	public static String getLogTag() {
		return mLogTag;
	}

	public static void setLogTag(String logTag) {
		mLogTag = logTag;
	}

	/**
	 * Debug
	 * 
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void d(String log) {
		if (LOGGER_LEVEL_DEBUG <= mLogLevel) {
			android.util.Log.d(mLogTag, getPrefix() + log);
		}
	}
	
	/**
	 * Debug
	 * 
	 * @param tag
	 *            The string to tag
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void d(String tag, String log) {
		if (LOGGER_LEVEL_DEBUG <= mLogLevel) {
			android.util.Log.d(tag, getPrefix() + log);
		}
	}

	/**
	 * Info
	 * 
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void i(String log) {
		if (LOGGER_LEVEL_INFO <= mLogLevel) {
			android.util.Log.i(mLogTag, getPrefix() + log);
		}
	}

	/**
	 * Info
	 * 
	 * @param tag
	 *            The string to tag
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void i(String tag, String log) {
		if (LOGGER_LEVEL_INFO <= mLogLevel) {
			android.util.Log.i(tag, getPrefix() + log);
		}
	}
	
	/**
	 * Warning
	 * 
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void w(String log) {
		if (LOGGER_LEVEL_WARN <= mLogLevel) {
			android.util.Log.w(mLogTag, getPrefix() + log);
		}
	}
	
	/**
	 * Warning
	 * 
	 * @param tag
	 *            The string to tag
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void w(String tag, String log) {
		if (LOGGER_LEVEL_WARN <= mLogLevel) {
			android.util.Log.w(tag, getPrefix() + log);
		}
	}

	/**
	 * Error
	 * 
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void e(String log) {
		if (LOGGER_LEVEL_ERR <= mLogLevel) {
			android.util.Log.e(mLogTag, getPrefix() + log);
		}
	}
	
	/**
	 * Error
	 * 
	 * @param tag
	 *            The string to tag
	 * @param log
	 *            The string to show
	 * @return void
	 */
	public static void e(String tag, String log) {
		if (LOGGER_LEVEL_ERR <= mLogLevel) {
			android.util.Log.e(tag, getPrefix() + log);
		}
	}
}

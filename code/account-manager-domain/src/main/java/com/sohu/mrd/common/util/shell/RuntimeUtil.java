package com.sohu.mrd.common.util.shell;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chencheng
 * @email chencheng1@yy.com
 * @date Dec 22, 2014 4:08:03 PM
 */

public class RuntimeUtil {

	private static Log LOG = LogFactory.getLog(RuntimeUtil.class);
	private static Runtime runtime = Runtime.getRuntime();

	private RuntimeUtil() {
	}

	/**
	 * 异步执行命令
	 *
	 * @param callback
	 * @param env
	 * @param dir
	 * @param startCommand
	 * @param commands
	 */
	public static void asynCall(final RuntimeCallback callback,
			final String[] env, final File dir, final String[] startCommand,
			final String[]... commands) {
		new Thread() {
			@Override
			public void run() {
				StringReader result = RuntimeUtil.call(env, dir, startCommand,
						commands);
				if (callback != null) {
					callback.callback(result, env, dir, startCommand, commands);
				}
			}
		}.start();
	}

	public static StringReader call(String[] envp, File dir,
			String[] startCommand, String[]... commands) {
		// 唯一的命令id
		String commandId = UUID.randomUUID().toString().replace("-", "");
		LOG.info("command(" + commandId + ") start...");
		StringBuffer result = new StringBuffer();
		Process currentProcess = null;
		Process nextProcess = null;
		try {
			currentProcess = call(commandId, startCommand, envp, dir);
			BufferedOutputStream out = null;
			BufferedReader reader = null;
			BufferedInputStream in = null;
			byte[] buffer = new byte[1024];

			for (String[] command : commands) {
				// 获取当前命令的标准错误流
				try {
					reader = new BufferedReader(new InputStreamReader(
							currentProcess.getErrorStream()));
					String temp = "";
					StringBuilder errorLog = new StringBuilder();
					while ((temp = reader.readLine()) != null) {
						errorLog.append(temp);
						errorLog.append("\r\n");
					}
					if (errorLog.length() > 0) {
						LOG.warn("commandId(" + commandId + ") ERROR: "
								+ errorLog.toString());
					}
					in = new BufferedInputStream(
							currentProcess.getInputStream());
					nextProcess = call(commandId, command, envp, dir);
					out = new BufferedOutputStream(
							nextProcess.getOutputStream());
					// 管道的实现
//					int c=-1;
//					while ((c = in.read(buffer)) != -1) {
//						out.write(buffer, 0, c);
//					}
                    int inc=-1;
                    while((inc=in.read(buffer))!=-1){
                        out.write(buffer,0,inc);
                    }
					currentProcess = nextProcess;
				} finally {
					if (out != null) {
						out.flush();
						out.close();
						out = null;
					}
					if (in != null) {
						in.close();
						in = null;
					}
					if (reader != null) {
						reader.close();
						reader = null;
					}
				}
			}

			try {
				reader = new BufferedReader(new InputStreamReader(
						currentProcess.getErrorStream()));
				String temp = "";
				StringBuilder errorLog = new StringBuilder();
				while ((temp = reader.readLine()) != null) {
					errorLog.append(temp);
					errorLog.append("\r\n");
				}
				if (errorLog.length() > 0) {
					LOG.warn("commandId(" + commandId + ") ERROR: "
							+ errorLog.toString());
				}
			} finally {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			}

			try {
				reader = new BufferedReader(new InputStreamReader(
						currentProcess.getInputStream()));
				String temp = "";
				while ((temp = reader.readLine()) != null) {
					result.append(temp);
					result.append("\r\n");
				}

			} finally {
				if (out != null) {
					out.flush();
					out.close();
					out = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
				if (reader != null) {
					reader.close();
					reader = null;
				}
			}

		} catch (IOException e) {
			LOG.error("", e);
		}
		LOG.info("command(" + commandId + ") end...");
		return new StringReader(result.toString());
	}

	private static Process call(String commandId, String[] command,
			String[] envp, File dir) throws IOException {
		LOG.info("command(" + commandId + ") exec:" + Arrays.toString(command));
		return runtime.exec(command, envp, dir);
	}

	public static void main(String[] args) {
		asynCall(new RuntimeCallback() {

			@Override
			public void callback(StringReader result, String[] env, File dir,
					String[] startCommand, String[]... commands) {
				StringBuilder sb = new StringBuilder();
				String temp = null;
				BufferedReader br = new BufferedReader(result);
				try {
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
						sb.append("\n");
					}
				} catch (IOException e) {
					LOG.error("", e);
				}
				System.out.println("result:\n" + sb.toString());
			}
		}, null, null, new String[] { "jps" }, new String[] {""});
	}
}

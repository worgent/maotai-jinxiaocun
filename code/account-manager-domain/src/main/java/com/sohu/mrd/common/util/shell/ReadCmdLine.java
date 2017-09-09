package com.sohu.mrd.common.util.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by haixingcheng on 2014/6/5.
 */
public class ReadCmdLine {
	public static Log logger = LogFactory.getLog(RemoteShellTool.class);

	public static void main(String args[]) {

		String result = ReadCmdLine.excuteCmd("ps -aux");
		System.out.println(result);

	}

	public static void callShell(String shellString) {
		try {
			Process process = Runtime.getRuntime().exec(shellString);
			int exitValue = process.waitFor();
			if (0 != exitValue) {
				logger.error("call shell failed. error code is :" + exitValue);
			}
		} catch (Throwable e) {
			logger.error("call shell failed. ", e);
		}
	}

	// "ps -aux"
	public static String excuteCmd(String cmd) {
		Process process = null;
		StringBuffer result = new StringBuffer();
		try {
			process = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				result.append(line);
			}
			input.close();
		} catch (IOException e) {
			logger.error("执行CMD命令异常", e);
		}
		return result.toString();
	}

}

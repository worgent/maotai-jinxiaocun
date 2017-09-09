package com.sohu.mrd.common.util.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 此工具类，返回结果，貌似正常
 */
public class RemoteShell {
	private String host;
	private String username;
	private String passwd;
	private static Logger log = Logger.getLogger(RemoteShell.class);

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public RemoteShell(String host, String username, String passwd) {
		this.host = host;
		this.username = username;
		this.passwd = passwd;
	}

	public String exec(String cmds) {
		Connection conn = new Connection(host);
		StringBuilder result = new StringBuilder();
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					passwd);
			if (isAuthenticated == false)
				log.error("Authentication failed.");
			final Session sess = conn.openSession();
			// String result1
			// =remoteShellTool.exec("su mrd -c /opt/sohuhadoop/mayue/script/dailymail_test_remote.sh",0);
			sess.execCommand(cmds);
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			Thread t = new Thread() {// 如果调用异常，就直接退出
				@Override
				public void run() {
					InputStream stdout = new StreamGobbler(sess.getStderr());
					BufferedReader br = new BufferedReader(
							new InputStreamReader(stdout));
					while (true) {
						String line;
						try {
							line = br.readLine();
							if (line == null)
								break;
							log.info(line);
						} catch (IOException e) {
							log.error("RemoteShell.exec 调用异常", e);
						}
					}
				}
			};
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				// System.out.println(line);
				result.append(new String(line.getBytes(), "utf8"));
				result.append("<br/>");
			}
			System.out.println("ExitCode: " + sess.getExitStatus());
			sess.close();
		} catch (Exception e) {
			log.error("RemoteShell.exec outer 调用异常", e);
		}
		conn.close();
		return result.toString();
	}

	public static void main(String[] args) {
		// RemoteShell rm = new RemoteShell("10.10.127.93", "root",
		// "eT4H737Fs7");
		// RemoteShell rm = new RemoteShell("10.13.82.2","root","5qvZt2Xt#");
		RemoteShell rm = new RemoteShell("10.13.85.163", "root", "cOzq@yO07");
		// String
		// cmds="/root/restartkafka.sh > /root/autodeploy_logs/restartkafka.log";
		String cmds = "/root/restartkafka.sh";
		// String cmds="cat -s -n /root/autodeploy_logs/restartkafka.log";

		String res = rm.exec(cmds);
		System.out.println(res);
	}
}

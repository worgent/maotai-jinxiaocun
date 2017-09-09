package com.sohu.mrd.common.util.shell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.mrd.domain.util.common.StringUtils;

/**
 * Created by haixingcheng on 2014/6/5. 远程Shell脚本执行工具
 */
public class RemoteShellTool {

	private String serverDir;

	private Connection conn;
	private String ipAddr;
	private String charset = Charset.defaultCharset().toString();
	private String userName;
	private String password;
	private String dockerSwitch;

	public static Log logger = LogFactory.getLog(RemoteShellTool.class);

	public RemoteShellTool(String ipAddr, String userName, String password,
			String charset, String serverDir) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
		this.serverDir = serverDir;
	}

	public RemoteShellTool(String ipAddr, String userName, String password,
			String charset, String serverDir, String dockerSwitch) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
		this.serverDir = serverDir;
		this.dockerSwitch = dockerSwitch;
	}

	/**
	 * 登录远程Linux主机
	 *
	 * @return
	 * @throws IOException
	 */
	public boolean login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	/**
	 * 执行Shell脚本或命令
	 *
	 * @param cmds
	 *            命令行序列
	 * @return
	 */
	public Map<String, Object> exec(String cmds, long sleep) {
		String in = null;
		String err = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);
				// session.wait(sleep);
				// session.startShell();
				Thread.sleep(sleep);
				in = this.processStdout(session.getStdout(), this.charset);
				result.put("output", in);
				err = this.processStdout(session.getStderr(), this.charset);
				result.put("error", err);

				// InputStream sterror = new StreamGobbler(session.getStderr());
				// BufferedReader bre = new BufferedReader(new
				// InputStreamReader(sterror));
				// StringBuilder sbe = new StringBuilder();
				// while (true)
				// {
				// String line = bre.readLine();
				// if (line == null)
				// break;
				// sbe.append(line);
				// }
				// result.put("error", "");
				// InputStream stdout = new StreamGobbler(session.getStdout());
				// BufferedReader br = new BufferedReader(new
				// InputStreamReader(stdout));
				// StringBuilder sb = new StringBuilder();
				// while (true)
				// {
				// String line = br.readLine();
				// if (line == null)
				// break;
				// sb.append(line);
				// }
				// result.put("output", sb.toString());
				conn.close();
			} else
				result.put("error", "连接服务器失败，请重试");
		} catch (Exception e1) {
			logger.error("执行shell脚本异常：", e1);
			result.put("error", "执行shell脚本异常");
		}
		return result;
	}

	/**
	 * 解析流获取字符串信息
	 *
	 * @param in
	 *            输入流对象
	 * @param charset
	 *            字符集
	 * @return
	 */
	public String processStdout(InputStream in, String charset) {
		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			logger.error("解析shell脚本的返回值异常：", e);
		}
		return sb.toString();
	}

	/**
	 * 解析脚本执行返回的结果集
	 * 
	 * @author Ickes
	 * @param in
	 *            输入流对象
	 * @param charset
	 *            编码
	 * @since V0.1
	 * @return 以纯文本的格式返回
	 */
	private String processStdoutNew(InputStream in, String charset) {
		InputStream stdout = new StreamGobbler(in);
		StringBuffer buffer = new StringBuffer();
		;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					stdout, charset));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * @author Ickes 远程执行shll脚本或者命令
	 * @param cmd
	 *            即将执行的命令
	 * @return 命令执行完后返回的结果值
	 * @since V0.1
	 */
	public Map<String, Object> execNew(String cmd) {
		String in = null;
		String err = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (login()) {
				Session session = conn.openSession();// 打开一个会话
				session.execCommand(cmd);// 执行命令
				in = processStdoutNew(session.getStdout(), charset);
				result.put("output", in);
				// 如果为得到标准输出为空，说明脚本执行出错了
//				if (StringUtils.isBlank(in)) {
				err = processStdoutNew(session.getStderr(), charset);
				result.put("error", err);
//				} else {
//					result.put("error", "");
//				}
				conn.close();
				session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getServerDir() {
		return serverDir;
	}

	public void setServerDir(String serverDir) {
		this.serverDir = serverDir;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDockerSwitch() {
		return dockerSwitch;
	}

	public void setDockerSwitch(String dockerSwitch) {
		this.dockerSwitch = dockerSwitch;
	}
	
	public static void main(String[] args) {
		RemoteShellTool remoteShellTool = new RemoteShellTool("10.13.85.180","root","iqM2ZeU8j)","utf-8","/data/mrd/lab");
//		System.out.println(remoteShellTool.execNew("sh /data/quantize/tomcat/logs/test.log").get("output"));
//		remoteShellTool.execNew("tail -f /data/quantize/tomcat/logs/catalina.out");
		
		remoteShellTool.execNew("python /data/mrd/lab/strategy.py ");
	}


}

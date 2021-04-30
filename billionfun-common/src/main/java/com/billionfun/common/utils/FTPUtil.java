package com.billionfun.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTPUtil {
	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
	private static String hostname = "101.200.91.219";
	private static String username = "files";
	private static String password = "Smartoven";
	private static int port = 21;
	static {
		hostname = "101.200.91.219";
		username = "files";
		password = "Smartoven";
		port = 21;
	}
	
	public static FTPClient getFTPClient() throws SocketException, IOException {
		FTPClient ftpClient = new FTPClient();
		if (ftpClient.isConnected()) {
			logger.info("ftpClient has exist ,get old ftpClient");
		}else{
			ftpClient.connect(hostname, port);
			if (!ftpClient.login(username, password)) {
				throw new RuntimeException("login incorrect.");
			} else {
				logger.info("ftp login success");
			}
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 防止上传压缩包文件损坏
			ftpClient.setDefaultTimeout(3000);
			ftpClient.setDataTimeout(3000);
			ftpClient.setConnectTimeout(3000);
			ftpClient.setKeepAlive(false);
		}
		
		return ftpClient;
	}
	
	public static void closeFTPClient(FTPClient ftpClient)  {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
				logger.info("close ftp sucess");
			} catch (IOException ioe) {
				logger.info("close ftp failed");
			}
		}
	}
	
	
	
	
	public static boolean cutFile(String from, String to) {
		boolean success = false;
		FTPClient ftpClient = null;
		logger.info("start cut file from ftp:" + from);
		try {
			ftpClient = getFTPClient();
			ftpClient.rename(from, to);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.getReplyCode() < 300) {
				success = true;
				logger.info("cut file from ftp sucess:" + to);
			}
			closeFTPClient(ftpClient);
		}
		return success;
	}
	
	/**
	 * Description: 创建文件夹
	 *
	 * @param folderName
	 *            文件夹名称
	 * @return
	 */
	public static boolean createDeepDir(String folderName) {
		boolean success = false;
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			String[] arr = folderName.split("/");
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].isEmpty()) {
					if (!ftpClient.changeWorkingDirectory(arr[i])) {
						ftpClient.makeDirectory(arr[i]);
						if (ftpClient.getReplyCode() > 300) {
							return false;
						}
						ftpClient.changeWorkingDirectory(arr[i]);
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.getReplyCode() < 300) {
				logger.info(" create " + folderName + " dir success!!!");
				success = true;
			}else{
				logger.info(" create " + folderName
						+ " dir failed!!! reason is " + ftpClient.getReplyString());
			}
			closeFTPClient(ftpClient);
		}
		return success;
	}
	
	/**
	 * Description: 向FTP服务器上传文件
	 *
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String path, String filename,
									 InputStream input) {
		boolean success = false;
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			ftpClient.changeWorkingDirectory(path);
			ftpClient.storeFile(filename, input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.getReplyCode() < 300) {
				logger.info("upload file to ftp " + path);
				success = true;
			}else{
				logger.info(" upload file failed !!"
						+ " reason is " + ftpClient.getReplyString()+"----"+ path+filename);
			}
			closeFTPClient(ftpClient);
		}
		return success;
	}
	
	/**
	 * 把配置文件先写到本地的一个文件中取
	 *
	 * @param fileName
	 * @param fileContext
	 * @return
	 */
	public boolean write(String fileName, String fileContext,
						 String writeTempFielPath) {
		try {
			logger.info("开始写配置文件");
			File f = new File(writeTempFielPath + "/" + fileName);
			if (!f.exists()) {
				if (!f.createNewFile()) {
					logger.info("文件不存在，创建失败!");
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(fileContext.replaceAll("\n", "\r\n"));
			bw.flush();
			bw.close();
			return true;
		} catch (Exception e) {
			logger.error("写文件没有成功");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 本地上传文件到FTP服务器
	 *
	 * @param ftpPath
	 *            远程文件路径FTP
	 * @throws IOException
	 */
	public void upload(String ftpPath, String fileContent,
					   String writeTempFielPath) {
		FTPClient ftpClient = null;
		logger.info("开始上传文件到FTP.");
		try {
			ftpClient = FTPUtil.getFTPClient();
			
			// 断点续传,有的ftp server不支持这个命令,但并不报错.
			// long fileLen = 0;
			// ftp.setRestartOffset(fileLen);
			// 对远程目录的处理
			String remoteFileName = ftpPath;
			if (ftpPath.contains("/")) {
				remoteFileName = ftpPath
						.substring(ftpPath.lastIndexOf("/") + 1);
			}
			// FTPFile[] files = ftpClient.listFiles(new
			// String(remoteFileName));
			// 先把文件写在本地。在上传到FTP上最后在删除
			boolean writeResult = write(remoteFileName, fileContent,
					writeTempFielPath);
			if (writeResult) {
				File f = new File(writeTempFielPath + "/" + remoteFileName);
				InputStream in = new FileInputStream(f);
				ftpClient.storeFile(remoteFileName, in);
				in.close();
				logger.info("上传文件" + remoteFileName + "到FTP成功!");
				f.delete();
			} else {
				logger.info("写文件失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeFTPClient(ftpClient);
		}
	}
	
	/**
	 * Description: 从FTP服务器下载文件
	 *
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile(String remotePath, String fileName,
								   String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(hostname, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			ftp.enterLocalPassiveMode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	
	/**
	 * Description: 创建文件夹
	 *
	 * @param folderName
	 *            文件夹名称
	 * @return
	 */
	public static boolean createDir(String folderName) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(hostname, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			ftp.enterLocalPassiveMode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			String[] arr = folderName.split("/");
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].isEmpty()) {
					if (!ftp.changeWorkingDirectory(arr[i])) {
						ftp.makeDirectory(arr[i]);
						if (ftp.getReplyCode() > 300) {
							return false;
						}
						ftp.changeWorkingDirectory(arr[i]);
					}
				}
			}
			
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (success) {
				logger.info(" create " + folderName + " dir success!!!");
			} else {
				logger.info(" create " + folderName
						+ " dir failed!!! reason is " + ftp.getReplyString());
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = getFTPClient();
		FTPUtil.createDir("2016");
		// ftpClient.sen
		// boolean code = ftpClient
		// .rename("/alidata/servers/resources/webdav/files/8aad11b451b4f2e70151c3686aec0067/temp/platforms.zip",
		// "/alidata/servers/resources/webdav/files/8aad11b451b4f2e70151c3686aec0067/8a2bf8035340b56c01535e9e3aaa04da.dir/platforms.zip");
//		InputStream input = new FileInputStream(new File(
//				"/Users/zhuyi/Downloads/8a2bf803538422e00153842c5e2a0020.zip"));
//		FTPUtil.uploadFile("8aad11b451b4f2e70151c3686aec0067",
//				"8a2bf803538422e00153842c5e2a0020", input);
		// FTPUtil.downFile("test", "image4444.JPG", "/Users/zhuyi/Downloads");

		// String cmd = "mv /alidata/servers/resources/webdav/files/"
		// + cr.getOwnerId() + "/temp/" + fileName
		// + " /alidata/servers/resources/webdav/files/"
		// + recipeResource.getResId() + "/"
		// + (type.equals("VOICE") ? ".mp3" : "");
		// CmdUtil.exec(cmd);
		// WebDAVClient client = new WebDAVClient();
		// client.init("101.200.91.219", 81, "user1", "user1", 1);
		// client.move(
		// "http://user1:user1@101.200.91.219:81/files" + "/"
		// + cr.getOwnerId() + "/temp/" + fileName,
		// "http://user1:user1@101.200.91.219:81/files" + "/"
		// + cr.getUri() + "/" + recipeResource.getResId()
		// + (type.equals("VOICE") ? ".mp3" : ""));
		// FTPUtil.downFile(cr.getOwnerId() + "/temp", fileName,
		// "/Users/zhuyi/Downloads/baks/");
		// try {
		// FTPUtil.uploadFile(cr.getUri(),
		// recipeResource.getResId()
		// + (type.equals("VOICE") ? ".mp3" : ""),
		// new FileInputStream(new File("/Users/zhuyi/Downloads/baks/"
		// + fileName)));
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}

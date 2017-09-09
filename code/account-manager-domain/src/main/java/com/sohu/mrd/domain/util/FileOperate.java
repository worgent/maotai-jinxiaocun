package com.sohu.mrd.domain.util;

import java.io.*;

/**
 * @author LYG
 */
public class FileOperate {

    private FileOperate(){

    }

    /**
     * 按字符读
     *
     * @param filePath
     *            文件路径
     * @param length
     *            缓冲区大小
     * @return 读到的文本
     */
    public static String readFile(String filePath, int length) {
        File file = new File(filePath);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            if (length < 1) {
                length = 2048;
            }
            StringBuffer text = new StringBuffer();
            char[] tmp = new char[length];
            int byteRead = 0;
            while ((byteRead = reader.read(tmp)) != -1) {
                text.append(tmp, 0, byteRead);
            }
            return text.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
                reader = null;
                file = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void appendToFile(String filePath, String text) {
        File file = new File(filePath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 按字符写
     *
     * @param filePath
     *            文件路径
     * @param text
     *            文本字符
     */
    public static void writeFile(String filePath, String text) {
        File file = new File(filePath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
                writer = null;
                file = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文本内容时，按行读取，使用用此方法读取
     *
     * @param filePath
     *            文件路径
     * @return 读到的文本
     */
    public static String readFileBuffered(String filePath) {
        File file = new File(filePath);
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(file));
            StringBuffer text = new StringBuffer();
            String readLine = "";
            while (null != (readLine = buffer.readLine())) {
                text.append(readLine);
                text.append("\n");
            }
            return text.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != buffer) {
                    buffer.close();
                }
                buffer = null;
                file = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param filePaht
     *            文件路径
     * @param text
     *            文本内容
     */
    public static void writeFileBuffered(String filePaht, String text) {
        File file = new File(filePaht);
        BufferedWriter buffer = null;
        try {
            buffer = new BufferedWriter(new FileWriter(file));
            buffer.write(text);
            buffer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != buffer) {
                    buffer.close();
                }
                buffer = null;
                file = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 新建文件夹
     *
     * @param floderPaht
     *            文件夹路径
     */
    public static void newFloder(String floderPaht) {
        File file = new File(floderPaht);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 新建文件
     *
     * @param filePaht
     *            文件路径
     */
    public static void newFile(String filePath, String text) {
        File file = new File(filePath);
        PrintWriter print = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            print = new PrintWriter(new FileWriter(filePath));
            print.println(text);
            print.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (print != null) {
                print.close();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePath
     *            文件路径
     */
    public static void delFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * 删除文件夹，包括文件夹里的文件
     *
     * @param filePath
     *            文件夹路径
     */
    public static void delFloder(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            String[] paths = file.list();
            File temp = null;
            for (int i = 0; i < paths.length; i++) {
                if (filePath.endsWith(File.separator)) {
                    filePath = filePath + paths[i];
                } else {
                    filePath = filePath + File.separator + paths[i];
                }
                temp = new File(filePath);
                if (temp.isFile()) {
                    temp.delete();
                }
                if (temp.isDirectory()) {
                    delFloder(filePath);
                }
            }
        }
    }

    /**
     * 复制单个文件
     *
     * @param oldPath
     *            源文件路径
     * @param newPath
     *            目标文件路径 文件路径
     */
    public static void copyFile(String oldPath, String newPath) {
        File file = new File(oldPath);
        File newFile = new File(newPath);
        if (file.exists() && file.isFile()) {
            InputStream input = null;
            OutputStream output = null;
            try {
                // newFile.createNewFile();
                input = new FileInputStream(oldPath);
                output = new FileOutputStream(newFile);
                int byteRead = 0;
                byte[] buffer = new byte[2048];
                while ((byteRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, byteRead);
                }
                output.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != output) {
                        output.close();
                    }
                    if (null != input) {
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file = null;
                newFile = null;
            }
        }
    }

    /**
     * 复制整个文件夹
     *
     * @param oldPath
     *            源文件夹的地址
     * @param newPath
     *            目标文件夹的地址
     */
    public void copyFloder(String oldPath, String newPath) {
        File file = new File(oldPath);
        File newFile = new File(newPath);
        if (!file.exists()) {
            return;
        }
        if (!newFile.isDirectory()) {
            newFile.mkdir();
        }
        String[] paths = file.list();
        File temp = null;
        for (int i = 0; i < paths.length; i++) {
            if (oldPath.endsWith(File.separator)) {
                oldPath = oldPath + paths[i];
            } else {
                oldPath = oldPath + File.separator + paths[i];
            }
            if (newPath.endsWith(File.separator)) {
                newPath = newPath + paths[i];
            } else {
                newPath = newPath + File.separator + paths[i];
            }
            temp = new File(oldPath);
            if (temp.isFile()) {
                copyFile(oldPath, newPath);
            }
            if (temp.isDirectory()) {
                copyFloder(oldPath, newPath);
            }
        }

    }

    /**
     * 移动单个文件到指定目录
     *
     * @param oldPath
     *            源文件的地址
     * @param newPath
     *            目标文件的地址
     */
    public void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * 移动整个文件夹及文件夹内容到指定目录
     *
     * @param oldPath
     *            源文件夹的地址
     * @param newPath
     *            目标文件夹的地址
     */
    public void moveFloder(String oldPath, String newPath) {
        copyFloder(oldPath, newPath);
        delFloder(oldPath);
    }

    /**
     * 修改文件的名字
     *
     * @param filePath
     * @param newPath
     */
    public void renameFile(String filePath, String newPath) {
        File file = new File(filePath);
        File newFile = new File(newPath);
        file.renameTo(newFile);
    }

}

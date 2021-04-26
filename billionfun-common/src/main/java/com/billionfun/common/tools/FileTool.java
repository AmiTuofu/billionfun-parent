package com.billionfun.common.tools;

import com.billionfun.common.utils.StaticThreadPool;
import com.billionfun.common.utils.superutils.j2se.FileUtils;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class FileTool {
    public static void main(String[] args) throws Exception {
//            String phone ="419055X";
//            System.out.println(phone);
//            System.out.println(phone.replaceAll("(?<=\\w{14})\\w", "*"));


/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/5/4 13:01
 */
        String sourceFold = "E:\\iCloud Photos\\Downloads";
    //    String sourceFold = "E:\\照片（全）";
        String rootFold = "G:\\整理图片\\";
   //     String sourceFold = "E:\\照片（全）";
  //      String rootFold = "G:\\整理图片\\";

        long startDate = System.currentTimeMillis();
        System.out.println("整理文件开始....."+startDate);
        FileTool fileTool = new FileTool();
        fileTool.exec(sourceFold,rootFold);
        System.out.println("整理文件结束....."+(System.currentTimeMillis()-startDate));
    //    System.out.println(readPic(new File("E:\\iCloud Photos\\Downloads\\IMG_8385.PNG")));
    }

    public void exec(String sourceFold ,String rootFold) throws IOException {
        File file = new File(sourceFold);
        String[] filePath = file.list();

        for (int i = 0; i < filePath.length; i++) {
            String sourcePath = sourceFold+file.separator  + filePath[i];
            File sourceFile = new File(sourcePath);
            if (sourceFile.isDirectory()) {
        //        exec(sourcePath,rootFold);
                StaticThreadPool.threadPool().execute(new PutInOrderThread(sourcePath,rootFold));
                System.out.println("线程池中线程数目："+StaticThreadPool.threadPool().getPoolSize()+"，队列中等待执行的任务数目："+
                        StaticThreadPool.threadPool().getQueue().size()+"，已执行玩别的任务数目："+StaticThreadPool.threadPool().getCompletedTaskCount());
                continue;
            }
            String targetFold = readPic(sourceFile);
            System.out.println("源文件:"+sourcePath+"复制至目标文件夹:"+targetFold);
            FileUtils.copyFileToFold(sourcePath,rootFold+targetFold);
        }
    }


    /**
     * 处理 单张 图片
     *
     * @return void
     * @date 2015-7-25 下午7:30:47
     */
    private static String readPic(File jpegFile) {
        String result = "其他";
        Metadata metadata;
        try {
            metadata = Mp4MetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = (Tag) tags.next();
                    if(tag.getTagName().equals("File Modified Date")) {
                        System.out.println("sourceFile:"+jpegFile.getName()+",tagName:"+tag.getTagName()+",date:"+tag.getDescription());
                        String[] strArr = tag.getDescription().split(" ");
                        result = strArr[5]+"年"+solve(strArr[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String solve(String s) {
        int result = 0;
        switch (s) {
            case "十二月":
                result = 12;
                break;
            case "十一月":
                result = 11;
                break;
            case "十月":
                result = 10;
                break;
            case "九月":
                result = 9;
                break;
            case "八月":
                result = 8;
                break;
            case "七月":
                result = 7;
                break;
            case "六月":
                result = 6;
                break;
            case "五月":
                result = 5;
                break;
            case "四月":
                result = 4;
                break;
            case "三月":
                result = 3;
                break;
            case "二月":
                result = 2;
                break;
            case "一月":
                result = 1;
        }
        return result+"月";
    }
    class PutInOrderThread implements Runnable{
        private String sourcePath;
        private String rootFold;
        public PutInOrderThread(String sourcePath,String rootFold){
            this.sourcePath = sourcePath;
            this.rootFold = rootFold;
        }
        @Override
        public void run() {
            try {
                long startDate = System.currentTimeMillis();
                System.out.println("正在执行task: "+sourcePath);
                exec(sourcePath,rootFold);
                System.out.println("task: "+sourcePath+"执行完毕:"+new Date()+" 花费:"+(System.currentTimeMillis()-startDate));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
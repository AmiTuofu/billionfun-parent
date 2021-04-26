package com.billionfun.common.utils;

public class ImageCompressUtil {

	// /**
	// * 按照宽度还是高度进行压缩 原图的宽图和高度比例比压缩比例大，以宽为标准，高度按照原比例自适应
	// * 原图的宽图和高度比例比压缩比例小，以高为标准，宽度按照原比例自适应
	// *
	// * @param w
	// * 指定压缩宽度
	// * @param h
	// * 指定压缩高度
	// * @throws ImageFormatException
	// * @throws IOException
	// */
	// public static void compressFix(String sourceName, String destName, int w,
	// int h) throws ImageFormatException, IOException {
	// File file = new File(sourceName);
	// Image sourceImg = ImageIO.read(file);
	// int width = sourceImg.getWidth(null);
	// int height = sourceImg.getHeight(null);
	// if (width / height > w / h) {
	// compressImg(sourceImg, destName, w, height * w / width);
	// } else {
	// compressImg(sourceImg, destName, width * h / height, h);
	// }
	// }
	//
	// public static void compressImg(Image sourceImg, String destName, int w,
	// int h) throws ImageFormatException, IOException {
	// /**
	// * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
	// *
	// */
	// BufferedImage image = new BufferedImage(w, h,
	// BufferedImage.TYPE_INT_RGB);
	//
	// // 绘制新图时，使用Image.SCALE_SMOOTH算法，压缩后的图片质量相对比较光滑，没有明显的锯齿形
	// // image.getGraphics().drawImage(img, 0, 0, w, h, null); <span
	// // style="color:#ff6666;">//---------压缩图片如图1</span>
	// image.getGraphics().drawImage(
	// sourceImg.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0,
	// null);
	//
	// File destFile = new File(destName);
	// FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
	// // 可以正常实现bmp、png、gif转jpg
	// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	// encoder.encode(image); // JPEG编码
	// out.close();
	// }
	//
	// @SuppressWarnings("deprecation")
	// public static void main(String[] args) throws IOException {
	// System.out.println("图片压缩开始：" + new Date().toLocaleString());
	// // compress.compressImg(150, 150); 不根据宽度或高度等比例压缩
	// ImageCompressUtil.compressFix("/Users/zhuyi/Downloads/11111.jpg",
	// "/Users/zhuyi/Downloads/222.jpg", 150, 150);// 等比例以宽度或高度为基准进行压缩
	// System.out.println("图片压缩结束：" + new Date().toLocaleString());
	// }
}

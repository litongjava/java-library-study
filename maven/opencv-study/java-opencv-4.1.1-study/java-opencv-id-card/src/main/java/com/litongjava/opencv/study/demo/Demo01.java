package com.litongjava.opencv.study.demo;
/**
 * @author create by ping-e-lee on 2021年7月2日 下午2:24:25 
 * @version 1.0 
 * @desc
 */

public class Demo01 {
  /**
  	 * canny算法，边缘检测
  	 * 
  	 * @param src
  	 * @return
  	 */
  	public static Mat canny(Mat src) {
  		Mat mat = src.clone();
  		Imgproc.Canny(src, mat, 60, 200);
  		HandleImgUtils.saveImg(mat , "C:/Users/admin/Desktop/opencv/open/x/canny.jpg");
  		return mat;
  	}
}

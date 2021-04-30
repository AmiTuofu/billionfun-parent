package com.billionfun.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回值
 * 
 * @author yulei
 * @date 2020-10-22
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

	private static final long serialVersionUID = 5884609523521008245L;

	public enum Code {
		SUCCESS(0, "成功"), FAIL(2000, "失败"), ERROR(9999, "异常");
		private Integer code;
		private String msg;

		private Code(Integer code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public Integer getCode() {
			return this.code;
		}

		public String getMsg() {
			return this.msg;
		}
	}

	private Integer code;
	private String msg;
	private Object data;

	public static Response SUCCESS() {
		Response response = new Response(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), null);
		return response;
	}

	public static Response SUCCESS(String msg) {
		Response response = new Response(Code.SUCCESS.getCode(), msg, null);
		return response;
	}

	public static Response SUCCESS(Object data) {
		Response response = new Response(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), data);
		return response;
	}

	public static Response SUCCESS(String msg, Object data) {
		Response response = new Response(Code.SUCCESS.getCode(), msg, data);
		return response;
	}

	public static Response FAIL(String msg) {
		Response response = new Response(Code.FAIL.getCode(), msg, null);
		return response;
	}
	
	public static Response ERROR() {
		Response response = new Response(Code.ERROR.getCode(), Code.ERROR.getMsg(), null);
		return response;
	}

	public static Response ERROR(String msg) {
		Response response = new Response(Code.ERROR.getCode(), msg, null);
		return response;
	}

	public boolean isSuccess() {
		return Code.SUCCESS.getCode().equals(this.getCode());
	}
}

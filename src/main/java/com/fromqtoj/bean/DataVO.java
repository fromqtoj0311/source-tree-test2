package com.fromqtoj.bean;

/**
 * 数据封装 
 * 
 * @author kou
 *
 * @param <T>
 */
public class DataVO<T> {
	
	private String flag;
	private String msg = "";
	private T data ;

	public static DataVO<Object> errorMsg(String msg){
		return new DataVO<Object>(msg,"error", "");
	}

	public static DataVO<Object> errorMsg(String flag, String msg){
		return new DataVO<Object>(msg,flag, "");
	}

	@Deprecated
	public static DataVO<Object> errorData(String msg){
        return new DataVO<Object>(msg,"error", "");
    }

    public static DataVO<Object> successData(Object data){
        return new DataVO<>("", "success", data);
    }

	private DataVO(String msg, String flag, T data){
		super();
		this.msg=msg;
		this.flag=flag;
		this.data = data;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}

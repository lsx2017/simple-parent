package cn.simple.common.em;

public enum Status {
	
	DEPLOY("2", "发布"), // 新闻公告
	NORMAL("1", "正常"),
	DELETE("0", "删除");
	
	private String index;
	private String name;

	// 构造方法
	private Status(String index, String name) {
		this.name = name;
		this.index = index;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public String getIndex() {
		return index;
	}
}

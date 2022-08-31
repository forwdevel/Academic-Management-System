package daovo;

public class StaffVo {
	private String id;
	private String name;
	private int enroll;
	
	public StaffVo() {
		
	}
	
	public StaffVo(String id, String name, int enroll) {
		this.id = id;
		this.name = name;
		this.enroll = enroll;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getEnroll() {
		return enroll;
	}
}

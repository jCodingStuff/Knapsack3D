public class Parcel() {

  private String name;

	/**
	* Default Parcel constructor
	*/
	public Parcel(String name) {
		this.name = name;
	}

	/**
	* Returns value of name
	* @return
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Sets new value of name
	* @param name the new name
	*/
	public void setName(String name) {
		this.name = name;
	}
}

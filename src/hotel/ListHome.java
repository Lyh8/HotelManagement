package hotel;

public class ListHome {
	
	IData id;
	public ListHome(IData iData){
		this.id=iData;
	}
	public ListHome(){
		
	}
	public String search(int roomNo) {
		return id.getStation(roomNo);
}
}
package testing;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import hotel.IData;
import hotel.MainRun;

public class MainRunMockTest {
	    private Mockery context=new Mockery();
	    private IData iData=null;
	    private MainRun mr=null;
		
		//用构造出来的Mockery实例来构造一个模拟的iData对象
	    @Before
	    public void setUp() throws Exception{
	   	 iData=context.mock(IData.class);
	   	 mr=new MainRun(iData);
	   	context.checking(new Expectations(){{
	   		allowing(iData).in_Out_Room(901, "Marty");  
	   		will(onConsecutiveCalls(returnValue("Marty成功入住901房间！"),returnValue("该房间已经有人入住")));
  		 }});
	   	context.checking(new Expectations(){{
	   		allowing(iData).in_Out_Room(901, "EMPTY");  
	   		will(onConsecutiveCalls(returnValue("901退房成功！"),returnValue("该房间没有客人入住，退房失败！")));
	   		 }}); 
	   	context.checking(new Expectations(){{
	   		oneOf(iData).getStation(801);  
	   		will(returnValue("EMPTY"));
	   		 }});
	}
	
	@Test
	public void testCommandIn() {
		assertEquals("Marty成功入住901房间！",mr.CommandIn("in",901,"Marty"));
		assertEquals("该房间已经有人入住",mr.CommandIn("in",901,"Marty"));
		assertEquals("房间号错!",mr.CommandIn("in",999,"Thomas"));
	}
	@Test
	public void testCommandOut() {
		assertEquals("901退房成功！",mr.CommandOut("out",901));
		assertEquals("该房间没有客人入住，退房失败！",mr.CommandOut("out",901));
		assertEquals("房间号错!",mr.CommandOut("out",999));
	}
	@Test
	public void testCommandsearch() {
		assertEquals("EMPTY",mr.CommandSearch("search", 801));
		assertEquals("房间号错!",mr.CommandSearch("search", 999));
	}
	@Test
	public void testCommandExit() {
		assertEquals("程序退出",mr.CommandExit("exit"));
		assertEquals("命令输入错误，请重新输入",mr.CommandExit("close"));
	}
}

package testing;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import hotel.IData;
import hotel.ListHome;

public class ListHomeMockTest {

	//构造一个Mockery对象
    private Mockery context=new Mockery();
    private IData iData=null;
    private ListHome lh=null;
	
	//用构造出来的Mockery实例来构造一个模拟的iData对象
    @Before
    public void setUp() throws Exception{
   	 iData=context.mock(IData.class);
   	 lh=new ListHome(iData);
   	 context.checking(new Expectations(){{
   		oneOf(iData).getStation(901);  //调用getStation查询有人住的房间
   		will(returnValue("Marty"));
   		 }});
   	context.checking(new Expectations(){{
   		oneOf(iData).getStation(801);  //调用getStation查询空房间
   		will(returnValue("EMPTY"));
   		 }});
     }
    @Test
	public void testSearch() {
    	assertEquals("Marty",lh.search(901));
		assertEquals("EMPTY",lh.search(801));
	}

}

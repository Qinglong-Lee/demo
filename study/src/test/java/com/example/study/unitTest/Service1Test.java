package com.example.study.unitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class Service1Test {

    @Mock
    private Service2 mockService2;

    @InjectMocks
    private Service1 service1UnderTest;

    @Test
    public void callService2() {
        final String MOCK_DATA = "MOCK";
        Mockito.when(mockService2.sout(anyString())).thenReturn(MOCK_DATA);
        String msg = service1UnderTest.callService2("");
        Assert.assertEquals(MOCK_DATA, msg);
    }

    @Test(expected = RuntimeException.class)
    public void mannalMock() {
        Service2 mockObj = Mockito.mock(Service2.class);
        Mockito.when(mockObj.sout(anyString())).thenThrow(RuntimeException.class);
        service1UnderTest.setService2(mockObj);
        service1UnderTest.callService2("");
    }
}
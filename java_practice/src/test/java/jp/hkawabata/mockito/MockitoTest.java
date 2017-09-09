package jp.hkawabata.mockito;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by kawabatahiroto on 2017/08/25.
 */
public class MockitoTest {
    @Test
    public void simpleMockitoTest() {
        // 通常のオブジェクトとモックオブジェクトを生成
        MyDBConnector con = new MyDBConnector();
        MyDBConnector conMock = mock(MyDBConnector.class);
        String sqlDefault = "SELECT * FROM test_table";
        String sql1 = "SELECT name FROM test_table";
        String sql2 = "SELECT age FROM test_table";
        // 通常オブジェクトの挙動
        assertThat(con.getMySQLRecord(sql1), is("not implemented"));
        assertThat(con.getMySQLRecord(sql2), is("not implemented"));
        assertThat(con.getMySQLRecord(sqlDefault), is("not implemented"));
        // ハリボテには挙動を定義していないのでメソッドの返り値は null
        assertThat(conMock.getMySQLRecord(sql1), nullValue());
        assertThat(conMock.getMySQLRecord(sql2), nullValue());
        assertThat(conMock.getMySQLRecord(sqlDefault), nullValue());
        // 引数ごとに戻り値を分ける.
        // ただし、any〜系のメソッドを使う場合は個別引数を指定するよりも先に使う（順番が逆だと上書きされてしまう）
        when(conMock.getMySQLRecord(anyString())).thenReturn("mock_value_default");
        when(conMock.getMySQLRecord(sql1)).thenReturn("mock_value1");
        when(conMock.getMySQLRecord(sql2)).thenReturn("mock_value2");
        assertThat(conMock.getMySQLRecord(sql1), is("mock_value1"));
        assertThat(conMock.getMySQLRecord(sql2), is("mock_value2"));
        assertThat(conMock.getMySQLRecord(sqlDefault), is("mock_value_default"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionMockitoTest() {
        MyDBConnector conMock = mock(MyDBConnector.class);
        when(conMock.getLogs()).thenThrow(new IllegalArgumentException());
        System.out.println(conMock.getLogs());
    }

    @Test
    public void nestMockitoTest() {
        MyDBConnector conMock = mock(MyDBConnector.class, RETURNS_DEEP_STUBS);
        when(conMock.getLogs().indexOf(anyString())).thenReturn(0);
        when(conMock.getLogs().indexOf("A")).thenReturn(1);
        when(conMock.getLogs().indexOf("B")).thenReturn(2);
        assertThat(conMock.getLogs().indexOf("A"), is(1));
        assertThat(conMock.getLogs().indexOf("B"), is(2));
        assertThat(conMock.getLogs().indexOf("C"), is(0));
        when(conMock.getLogs().get(anyInt())).thenReturn("log-default");
        when(conMock.getLogs().get(1)).thenReturn("log-1");
        when(conMock.getLogs().get(2)).thenReturn("log-2");
        assertThat(conMock.getLogs().get(0), is("log-default"));
        assertThat(conMock.getLogs().get(1), is("log-1"));
        assertThat(conMock.getLogs().get(2), is("log-2"));
    }

    @Test
    public void spyMockitoTest() {
        MyDBConnector con = spy(MyDBConnector.class);
        assertThat(con.hogeFuga(), is("hoge-fuga"));
        doReturn("hoge-spy").when(con).hoge();
        assertThat(con.hogeFuga(), is("hoge-spy-fuga"));
    }

    @Test
    public void callbackAnswerMockitoTest() {
        MyDBConnector conMock = mock(MyDBConnector.class);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object args[] = invocation.getArguments();
                return "record-" + args[0];
            }
        }).when(conMock).getMySQLRecord(anyString());
        assertThat(conMock.getMySQLRecord("hoge"), is("record-hoge"));
        assertThat(conMock.getMySQLRecord("fuga"), is("record-fuga"));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorMockitoTest() {
        MyDBConnector conMock = mock(MyDBConnector.class);
        when(conMock.getMySQLRecord(anyString()))
                .thenReturn("1")
                .thenReturn("2")
                .thenReturn("3")
                .thenThrow(new NoSuchElementException());
        assertThat(conMock.getMySQLRecord("hoge"), is("1"));
        assertThat(conMock.getMySQLRecord("hoge"), is("2"));
        assertThat(conMock.getMySQLRecord("hoge"), is("3"));
        conMock.getMySQLRecord("hoge");
    }

    @Test
    public void callRealMethodMockitoTest() {
        MyDBConnector conMock = mock(MyDBConnector.class);
        assertThat(conMock.hoge(), nullValue());
        // 一部のメソッドのみ実際の実装を適用
        when(conMock.hoge()).thenCallRealMethod();
        assertThat(conMock.hoge(), is("hoge"));
    }

    /**
     * 呼び出し回数の検証
     */
    @Test
    public void verifyMockitoTest() {
        MyDBConnector conMock = mock(MyDBConnector.class);
        when(conMock.getMySQLRecord(anyString())).thenReturn("record-any");
        when(conMock.getMySQLRecord("sql")).thenReturn("record-sql");
        System.out.println(conMock.getMySQLRecord("hoge"));
        System.out.println(conMock.getMySQLRecord("sql"));
        System.out.println(conMock.getMySQLRecord("fuga"));
        System.out.println(conMock.getMySQLRecord("fuga"));
        System.out.println(conMock.getMySQLRecord("fuga"));
        verify(conMock, times(5)).getMySQLRecord(anyString());
        verify(conMock, times(1)).getMySQLRecord("sql");
        verify(conMock, times(3)).getMySQLRecord("fuga");
        verify(conMock, atLeast(2)).getMySQLRecord("fuga");
        verify(conMock, atMost(4)).getMySQLRecord("fuga");
        verify(conMock, never()).getMySQLRecord("never");
    }

    /**
     * 呼び出し順序の検証
     */
    @Test
    public void inOrderMockitoTest() {
        MyDBConnector conMock1 = mock(MyDBConnector.class);
        MyDBConnector conMock2 = spy(MyDBConnector.class);
        System.out.println(conMock1.getMySQLRecord("sql1"));
        System.out.println(conMock2.hoge());
        System.out.println(conMock1.hoge());
        System.out.println(conMock2.getMySQLRecord("sql2"));
        System.out.println(conMock1.hoge());

        // 順序を検証したいメソッド以外は記述しなくて良い
        InOrder io = inOrder(conMock1, conMock2);
        io.verify(conMock1).getMySQLRecord("sql1");
        io.verify(conMock1).hoge();
        io.verify(conMock2).getMySQLRecord("sql2");
    }
}

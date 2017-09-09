package jp.hkawabata.mockito;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
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
}

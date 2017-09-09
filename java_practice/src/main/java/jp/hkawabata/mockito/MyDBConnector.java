package jp.hkawabata.mockito;

import java.util.List;

/**
 * Created by kawabatahiroto on 2017/08/25.
 */
public class MyDBConnector {
    public String getMySQLRecord(String sql) {
        // TODO: 実装
        return "not implemented";
    }

    private List<String> logs;

    public List<String> getLogs() { return logs; }

    public String hoge() { return "hoge"; }
    public String hogeFuga() {
        return hoge() + "-fuga";
    }
}

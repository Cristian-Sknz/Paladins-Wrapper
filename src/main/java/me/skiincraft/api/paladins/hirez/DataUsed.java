package me.skiincraft.api.paladins.hirez;

public class DataUsed {

    private int ActiveSessions;
    private int ConcurrentSessions;
    private int RequestLimitDaily;
    private int SessionCap;
    private int SessionTimeLimit;
    private int TotalRequestsToday;
    private int TotalSessionsToday;
    private String retmsg;

    public DataUsed(int activeSessions, int concurrentSessions, int requestLimitDaily, int sessionCap, int sessionTimeLimit, int totalRequestToday, int totalSessionsToday, String retmsg) {
        ActiveSessions = activeSessions;
        ConcurrentSessions = concurrentSessions;
        RequestLimitDaily = requestLimitDaily;
        SessionCap = sessionCap;
        SessionTimeLimit = sessionTimeLimit;
        TotalRequestsToday = totalRequestToday;
        TotalSessionsToday = totalSessionsToday;
        this.retmsg = retmsg;
    }

    public int getActiveSessions() {
        return ActiveSessions;
    }

    public int getConcurrentSessions() {
        return ConcurrentSessions;
    }

    public int getRequestLimitDaily() {
        return RequestLimitDaily;
    }

    public int getSessionCap() {
        return SessionCap;
    }

    public int getSessionTimeLimit() {
        return SessionTimeLimit;
    }

    public int getTotalRequestToday() {
        return TotalRequestsToday;
    }

    public int getTotalSessionsToday() {
        return TotalSessionsToday;
    }

    public String getRetmsg() {
        return retmsg;
    }

    @Override
    public String toString() {
        return "DataUsed{" +
                "ActiveSessions=" + ActiveSessions +
                ", ConcurrentSessions=" + ConcurrentSessions +
                ", RequestLimitDaily=" + RequestLimitDaily +
                ", SessionCap=" + SessionCap +
                ", SessionTimeLimit=" + SessionTimeLimit +
                ", TotalRequestToday=" + TotalRequestsToday +
                ", TotalSessionsToday=" + TotalSessionsToday +
                ", retmsg='" + retmsg + '\'' +
                '}';
    }
}

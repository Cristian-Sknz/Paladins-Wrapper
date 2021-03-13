package me.skiincraft.api.paladins.objects.miscellany;

import com.google.gson.annotations.SerializedName;

public class DataUsed {

    @SerializedName(value = "Active_Sessions")
    private final int activeSessions;
    @SerializedName(value = "Concurrent_Sessions")
    private final int concurrentSessions;
    @SerializedName(value = "Request_Limit_Daily")
    private final int requestLimitDaily;
    @SerializedName(value = "Session_Cap")
    private final int sessionCap;
    @SerializedName(value = "Session_Time_Limit")
    private final int sessionTimeLimit;
    @SerializedName(value = "Total_Requests_Today")
    private final int totalRequestsToday;
    @SerializedName(value = "Total_Sessions_Today")
    private final int totalSessionsToday;
    @SerializedName(value = "ret_msg")
    private final String retmsg;

    public DataUsed(int activeSessions, int concurrentSessions, int requestLimitDaily, int sessionCap, int sessionTimeLimit, int totalRequestToday, int totalSessionsToday, String retmsg) {
        this.activeSessions = activeSessions;
        this.concurrentSessions = concurrentSessions;
        this.requestLimitDaily = requestLimitDaily;
        this.sessionCap = sessionCap;
        this.sessionTimeLimit = sessionTimeLimit;
        this.totalRequestsToday = totalRequestToday;
        this.totalSessionsToday = totalSessionsToday;
        this.retmsg = retmsg;
    }

    public int getActiveSessions() {
        return activeSessions;
    }

    public int getConcurrentSessions() {
        return concurrentSessions;
    }

    public int getRequestLimitDaily() {
        return requestLimitDaily;
    }

    public int getSessionCap() {
        return sessionCap;
    }

    public int getSessionTimeLimit() {
        return sessionTimeLimit;
    }

    public int getTotalRequestToday() {
        return totalRequestsToday;
    }

    public int getTotalSessionsToday() {
        return totalSessionsToday;
    }

    public String getRetmsg() {
        return retmsg;
    }

    @Override
    public String toString() {
        return "DataUsed{" +
                "ActiveSessions=" + activeSessions +
                ", ConcurrentSessions=" + concurrentSessions +
                ", RequestLimitDaily=" + requestLimitDaily +
                ", SessionCap=" + sessionCap +
                ", SessionTimeLimit=" + sessionTimeLimit +
                ", TotalRequestToday=" + totalRequestsToday +
                ", TotalSessionsToday=" + totalSessionsToday +
                ", retmsg='" + retmsg + '\'' +
                '}';
    }
}

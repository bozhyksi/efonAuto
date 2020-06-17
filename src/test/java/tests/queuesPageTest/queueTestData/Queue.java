package tests.queuesPageTest.queueTestData;

import flow.BaseTestMethods;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Queue extends BaseTestMethods {
    //<editor-fold desc="enums">
    public enum Report{
        Overall_Call_Statistics("Overall call statistics"),
        Daily_Call_Statistics("Daily call statistics"),
        Hourly_Call_Statistics("Hourly call statistics"),
        Agent_Call_Statistics("Agent call statistics"),
        Waiting_Time_Statistics("Waiting time statistics");

        private String reportType;

        Report(String reportType){
            this.reportType = reportType;
        }

        public String getReportType() {
            return reportType;
        }
    }

    public enum RuleForFindingAgent{
        RingAll("Ring all"),
        Random("Random"),
        RandomWeighting("Random weighting"),
        Linear("Linear"),
        FewestСalls("Fewest calls"),
        RoundRobinWithMemory("Round-robin with memory");

        private String rule;

        RuleForFindingAgent(String rule){
            this.rule = rule;
        }

        public String getRule() {
            return rule;
        }

        public static RuleForFindingAgent getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum MaxWaitTime{
        _10("10"),
        _20("20"),
        _25("25"),
        _30("30"),
        _40("40"),
        _45("45"),
        _50("50"),
        _55("55");

        private String waitTime;

        MaxWaitTime(String waitTime){
            this.waitTime = waitTime;
        }

        public String getWaitTime() {
            return waitTime;
        }

        public static MaxWaitTime getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum Priority{
        veryLow("Very low"),
        low("Low"),
        medium("Medium"),
        high("High"),
        veryHigh("Very high");

        private String prior;

        Priority(String prior){
            this.prior = prior;
        }

        public String getPrior() {
            return prior;
        }

        public static Priority getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    enum TimeoutForCallingAnAgent{
        _5("5"),
        _7("7"),
        _10("10"),
        _12("12"),
        _15("15"),
        _17("17"),
        _20("20");

        private String timeOut;

        TimeoutForCallingAnAgent(String timeOut){
            this.timeOut = timeOut;
        }

        public static TimeoutForCallingAnAgent getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

        public String getTimeOut() {
            return timeOut;
        }
    }

    enum WaitingTimeBeforeNextAttempt{
        _1("1"),
        _2("2"),
        _3("3"),
        _4("4"),
        _5("5"),
        _8("8"),
        _10("10"),
        _12("12"),
        _15("15"),
        _18("18"),
        _20("20"),
        _25("25"),
        _30("30"),
        _35("35"),
        _45("45"),
        _50("50"),
        _60("60"),
        _90("90"),
        _120("120"),
        _180("180");

        private String wait;

        WaitingTimeBeforeNextAttempt(String wait){
            this.wait = wait;
        }

        public String getWait() {
            return wait;
        }

        public static WaitingTimeBeforeNextAttempt getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

    }

    enum WaitingTimeBeforeNextCall{
        _0("0"),
        _5("5"),
        _10("10"),
        _15("15"),
        _20("20"),
        _25("25"),
        _30("30"),
        _45("45"),
        _60("60");

        private String wait;

        WaitingTimeBeforeNextCall(String wait){
            this.wait = wait;
        }

        public static WaitingTimeBeforeNextCall getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

        public String getWait() {
            return wait;
        }
    }

    enum RecordCalls{
        Yes,
        No;

        public static String getRandomVal(){
            Random random = new Random();
            return String.valueOf(values()[random.nextInt(values().length)]);
        }
    }
    //</editor-fold>

    //<editor-fold desc="properties">

    private String day = getDate("yyyy-MM-dd");
    private String month = getDate("yyyy-MM", "MONTH",-3);
    private String fromDate = getDate("yyyy-MM-dd","MONTH",-2);
    private String toDate = getDate("yyyy-MM-dd");
    private String name;
    private String maxWaitTime;
    private String subscription;
    private String manager;
    private String reporter;
    private String logInOut;
    private String priority;
    private String waitingMusic;
    private String filenameAnnouncement;
    private String announcementFrequency;
    private String ruleForFindingAgent;
    private String timeoutForCalling;
    private String waitingTimeBeforeNextAttempt;
    private String waitingTimeBeforeNextCall;
    private String recordCalls;
    private String fromDateQueueRecordings;
    private String toDateQueueRecordings;
    //</editor-fold>

    public Queue(){
        this.name = getRandomString(10);
        this.maxWaitTime = MaxWaitTime.getRandomVal().getWaitTime();
        this.priority = Priority.getRandomVal().getPrior();
        this.announcementFrequency = MaxWaitTime.getRandomVal().getWaitTime();
        this.ruleForFindingAgent = RuleForFindingAgent.getRandomVal().getRule();
        this.timeoutForCalling = TimeoutForCallingAnAgent.getRandomVal().getTimeOut();
        this.waitingTimeBeforeNextAttempt = WaitingTimeBeforeNextAttempt.getRandomVal().getWait();
        this.waitingTimeBeforeNextCall = WaitingTimeBeforeNextCall.getRandomVal().getWait();
        this.recordCalls = RecordCalls.getRandomVal();
        this.fromDateQueueRecordings = "2015-04-23 13:22";
        this.toDateQueueRecordings = getDate("HOUR",1);
    }

    public Queue(String queueName){
        this.name = queueName;
        this.maxWaitTime = MaxWaitTime.getRandomVal().getWaitTime();
        this.priority = Priority.getRandomVal().getPrior();
        this.announcementFrequency = MaxWaitTime.getRandomVal().getWaitTime();
        this.ruleForFindingAgent = RuleForFindingAgent.getRandomVal().getRule();
        this.timeoutForCalling = TimeoutForCallingAnAgent.getRandomVal().getTimeOut();
        this.waitingTimeBeforeNextAttempt = WaitingTimeBeforeNextAttempt.getRandomVal().getWait();
        this.waitingTimeBeforeNextCall = WaitingTimeBeforeNextCall.getRandomVal().getWait();
        this.recordCalls = RecordCalls.getRandomVal();
        this.fromDateQueueRecordings = "2015-04-23 13:22";
        this.toDateQueueRecordings = getDate("HOUR",1);
    }

    //<editor-fold desc="get\set">


    public String getMonth() {
        return month;
    }

    public String getToDate() {
        return toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getDay() {
        return day;
    }


    public String getToDateQueueRecordings() {
        return toDateQueueRecordings;
    }

    public String getFromDateQueueRecordings() {
        return fromDateQueueRecordings;
    }

    public void setMaxWaitTime(String maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public void setRuleForFindingAgent(String ruleForFindingAgent) {
        this.ruleForFindingAgent = ruleForFindingAgent;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecordCalls() {
        return recordCalls;
    }

    public String getWaitingTimeBeforeNextCall() {
        return waitingTimeBeforeNextCall;
    }

    public String getWaitingTimeBeforeNextAttempt() {
        return waitingTimeBeforeNextAttempt;
    }

    public String getTimeoutForCalling() {
        return timeoutForCalling;
    }

    public String getRuleForFindingAgent() {
        return ruleForFindingAgent;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription.replaceAll("\\s","");
    }

    public String getSubscription() {
        return subscription;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReporter() {
        return reporter;
    }

    public void setLogInOut(String logInOut) {
        this.logInOut = logInOut.replaceAll("\\s","");
    }

    public String getLogInOut() {
        return logInOut;
    }

    public String getName() {
        return name;
    }

    public String getMaxWaitTime() {
        return maxWaitTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setWaitingMusic(String waitingMusic) {
        this.waitingMusic = waitingMusic.replaceAll("\\s","");
    }

    public String getWaitingMusic() {
        return waitingMusic;
    }

    public String getFilenameAnnouncement() {
        return filenameAnnouncement;
    }

    public void setFilenameAnnouncement(String filenameAnnouncement) {
        this.filenameAnnouncement = filenameAnnouncement.replaceAll("\\s","");
    }

    public String getAnnouncementFrequency() {
        return announcementFrequency;
    }
    //</editor-fold>

    public String changeName(){
        return this.name = getRandomString(15);
    }

    public String getJson(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("globalDetail", factory.createObjectBuilder()
                        .add("queueName", getName())
                        .add("subscriptionId", getSubscriptionId())
                        .add("mohId",0)
                        .add("loginLogout", JsonValue.NULL)
                        .add("uploadType", "")
                        .add("reporterIds",factory.createArrayBuilder())
                        .add("managerIds",factory.createArrayBuilder())
                )
                .add("specificDetail",factory.createObjectBuilder()
                        .add("maxHoldTime", 30)
                        .add("priority", 100)
                        .add("announcementFilename","")
                        .add("announcementFrequency", 30)
                        .add("strategy", "fewestcalls")
                        .add("timeout", 10)
                        .add("retry", 10)
                        .add("wrapUpTime", 10)
                        .add("recordCalls", true)
                        .add("announcementId",0)
                )
                .add("name", generateNewId())
                .build().toString();
    }

    private String getSubscriptionId(){
        String query ="SELECT abo_id FROM webadmin_20170426.abo \n" +
                "where customer_fk = 906144 and display_name=\"Queue/ACD\" and abo_id not in \n" +
                "(\n" +
                "\tSELECT abo_fk FROM webadmin_20170426.call_center_queue\n" +
                "\twhere name like\"%906144%\" and is_active=1\n" +
                ")";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> idList = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                idList.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idList.get(0);
    }

    private String generateNewId(){
        String query ="SELECT * FROM webadmin_20170426.call_center_queue where name like \"%906144%\" order by name desc limit 1";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        String nameId = "";
        while (true){
            try {
                if (!resultSet.next()) break;
                nameId= resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return incrementId(nameId);
    }

    private String incrementId(String nameId){
        String str = nameId.substring(nameId.indexOf("_")+1);
        str = String.valueOf(Integer.parseInt(str)+1);
        if (str.length()<5){
            for (int i = 0; i <= 5-str.length(); i++){
                str = "0"+str;
            }
        }
        return  "906144_"+str;
    }

    public String getId(){
        String query ="SELECT * FROM webadmin_20170426.call_center_queue where queue_name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getName()));
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}





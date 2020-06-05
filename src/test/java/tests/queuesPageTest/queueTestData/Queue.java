package tests.queuesPageTest.queueTestData;

import flow.BaseTestMethods;

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

}





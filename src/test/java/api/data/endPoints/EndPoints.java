package api.data.endPoints;

import io.restassured.RestAssured;

public abstract class EndPoints {

    private static final String baseApi = "http://192.168.102.162:9090/portal";
    private static final String basePath = "/portal/api";

    //Authorization
    public final static String getLoginToken = baseApi+"/api";
    public final static String postLogin = baseApi+"/j_spring_security_check";

    //User
    public final static String postCreateUser = "/users/create";
    public final static String deleteDeleteUser = "/users/{id}";
    public final static String getUsersList = "/users/search";
    public final static String postUploadAnnouncementForUser = "uploader/upload/voicemail/{id}";
    public final static String putInterfaceLanguageEn = "/user-login/change-locale/en";
    public final static String putItemsPerPage = "/users/items-per-page";

    //File management
    public final static String postAnnouncementUpload = "/uploader/upload/announcement";
    public final static String deleteAnnouncementDelete = "/announcements/{id}";
    public final static String postMohUpload = "/uploader/upload/moh";
    public final static String deleteMohDelete = "/moh/{id}";
    public final static String deleteAnnouncementLowLevelUser = "/voicemail/announcements/{id}";

    //Hunt group
    public final static String postHuntGroupCreate = "/hunt-groups";
    public final static String deleteHuntGroup = "/hunt-groups/{id}";
    public final static String putHuntGroupEdit = "/hunt-groups/{id}";

    //Queues
    public final static String postCreateQueue = "/queues/details/";
    public final static String deleteDeleteQueue = "/queues/{id}";
    public final static String postAddQueueAgent = "/queues/agents/{id}";

    //IVRs
    public final static String postIvrCreate = "ivrs/";
    public final static String deleteIvrDelete = "ivrs/{id}";

    //Conference calls
    public final static String postConfCallCreate = "/conference-calls";
    public final static String deleteConfCallDelete = "/conference-calls/{id}";

    //Abbreviated numbers
    public final static String postCreateAbbreviatedNumber = "/internal-numbers/{number}";
    public final static String deleteAbbreviatedNumberDelete = "/internal-numbers/{id}";
    public final static String putUpdateShortNum = "/internal-numbers/details/update";

    //Call PickUps
    public final static String postCreateCallPickUp = "/call-intercept-groups/create";
    public final static String deleteCreateCallPickUp = "call-intercept-groups/{id}";

    //Provisioning
    public final static String putPhoneModelSettingUpdate = "/provisioning/settings/CUSTOMER/{id}";

    //Numbers
    public final static String getCustomerNumbers = "/phone-numbers/search";

    //Send SMS
    public final static String postCreateAddressBookEntry = "/sms-addressbook";
    public final static String getAddressBookEntries = "/sms-addressbook";
    public final static String deleteAddressBookEntryDelete = "/sms-addressbook/{id}";
    public final static String putAddAuthorizedNumber = "/sms-authorized-number";
    public final static String deleteAddAuthorizedNumberDelete = "/sms-authorized-number/{id}";
    public final static String postEnterAuthorizationCode = "/sms-authorized-number/register-code";
}

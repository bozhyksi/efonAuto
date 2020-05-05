package core.workers.menuNavigator;


public class MenuNavigator {

    public enum MainMenuTabs {
        USER,
        NUMBERS,
        PROVISIONING,
        SUBSCRIPTIONS,
        LAST_CALLS,
        FAX,
        IVR,
        ABBREVIATED_DIALING,
        CALL_PICKUP,
        FILE_MANAGEMENT,
        CALL_FORWARDING,
        HUNT_GROUPS,
        CONFERENCE_CALLS,
        QUEUES,
        END_DEVICES,
        RECORDED_CALLS,
        PHONEBOOK,
        CONTACT_DATA,
    }

    public enum ProvisioningSubTabs{
        END_DEVICE,
        PHONE_MODELS,
        PROVISIONING_MANAGER
    }

    public enum LastCallsSubTabs{
        MISSED_CALLS,
        INCOMING_CALLS,
        OUTGOING_CALLS,
    }

    public enum AbbreviatedDiallingSubTabs{
        ABBREVIATED_NUMBERS,
        MANAGE_ABBREVIATED_NUMBERS
    }

}

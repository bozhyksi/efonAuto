package core.workers.menuNavigator;

public interface IMenuNavigator {

    default <T extends IMenuNavigator> T gotoMenuTab(MenuNavigator.MainMenuTabs tab){return null;}

    default void gotoSubMenuTab(MenuNavigator.ProvisioningSubTabs tab){}

    default void gotoSubMenuTab(MenuNavigator.LastCallsSubTabs tab){}

    default void gotoSubMenuTab(MenuNavigator.AbbreviatedDiallingSubTabs tab){}

}

package core.workers.menuNavigator;

public interface IMenuNavigator {

    <T extends Enum<T>>void gotoSubMenuTab(T tab);
}

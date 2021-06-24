package manred.topo.server.framework.loadable;


import manred.topo.server.framework.loadable.enums.ReloadType;

public interface IReload {

	boolean reload();

	ReloadType type();
}

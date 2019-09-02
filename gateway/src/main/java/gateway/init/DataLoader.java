package gateway.init;

import gateway.implementations.UsersImpl;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class DataLoader  implements ApplicationEventListener<ServerStartupEvent> {

	@Inject
	private UsersImpl userImpl;

	private DemoUsersFactory demoUsersFactory;
	public DataLoader(DemoUsersFactory demoUsersFactory) {
		this.demoUsersFactory=demoUsersFactory;
	}
	@Transactional
	@Override
	public void onApplicationEvent(ServerStartupEvent event) {
		if (!userImpl.findByUsername("admin").isPresent()) {
			userImpl.add(demoUsersFactory.defaultUsers(), "ROLE_ADMIN");
        }
	}
}

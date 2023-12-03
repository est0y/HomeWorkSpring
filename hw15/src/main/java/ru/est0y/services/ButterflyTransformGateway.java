package ru.est0y.services;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.est0y.domain.Butterfly;
import ru.est0y.domain.Caterpillar;

import java.util.Collection;

@MessagingGateway
public interface ButterflyTransformGateway {

	@Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
	Collection<Butterfly> process(Collection<Caterpillar> caterpillars);
}

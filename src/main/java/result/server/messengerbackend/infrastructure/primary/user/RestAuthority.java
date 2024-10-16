package result.server.messengerbackend.infrastructure.primary.user;


import java.util.Set;
import java.util.stream.Collectors;

import org.jilt.Builder;

import result.server.messengerbackend.messaging.domain.user.aggregate.Authority;


@Builder
public record RestAuthority(String name) {

    public static Set<RestAuthority> fromSet(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> RestAuthorityBuilder.restAuthority().name(authority.getName().name()).build())
                .collect(Collectors.toSet());
    }

}
package com.example.usermanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class SiteUserId extends DomainObjectId {
    private SiteUserId() {
        super(SiteUserId.randomId(SiteUserId.class).getId());
    }

    public SiteUserId(@NonNull String uuid) {
        super(uuid);
    }

    public static SiteUserId of(String uuid) {
        SiteUserId p = new SiteUserId(uuid);
        return p;
    }
}

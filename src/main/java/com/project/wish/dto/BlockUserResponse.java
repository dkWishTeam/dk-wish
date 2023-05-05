package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockUserResponse {
   private boolean updated;
   private boolean userBlocked;

    public BlockUserResponse(boolean updated, boolean userBlocked) {
        this.updated = updated;
        this.userBlocked = userBlocked;
    }
}

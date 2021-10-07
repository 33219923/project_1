package za.ac.nwu.as.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.Account;

import java.io.Serializable;
import java.util.UUID;

@ApiModel(value = "Account", description = "A DTO that represents the member account.")
public class AccountDto implements Serializable {

    private UUID accountId;

    public AccountDto() {
    }

    public AccountDto(Account account) {
        this.setAccountId(account.getId());
    }

    @ApiModelProperty(
            position = 1,
            name = "",
            notes = "",
            dataType = "",
            example = "",
            required = false
    )
    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}

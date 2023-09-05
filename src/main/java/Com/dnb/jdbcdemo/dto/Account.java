package Com.dnb.jdbcdemo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Account {
    public String accountId;
    private String accountHolderName;
    private String accountType;
    private float balance;
    private String contactNumber;
    private String address;
    private LocalDate accountCreatedDate = LocalDate.now();
    private LocalDate dob;
    private boolean accountStatus;

}
